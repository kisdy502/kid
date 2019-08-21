package com.sdt.kid.aio;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.auth.JwtAuthentication;
import com.sdt.kid.bean.User;
import com.sdt.kid.service.JwtService;
import com.sdt.kid.service.UserService;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

public class HandhakeHandler extends ChannelInboundHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private JwtService jwtService;
    private UserService userService;

    public HandhakeHandler() {
        jwtService = ApplicationContextProvider.getApplicationContext().getBean(JwtService.class, "jwtService");
        userService = ApplicationContextProvider.getApplicationContext().getBean(UserService.class, "userService");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage handshakeRespMsg = (TransMessageProtobuf.TransMessage) msg;
        if (handshakeRespMsg == null || handshakeRespMsg.getHeader() == null) {
            return;
        }

        int msgType = handshakeRespMsg.getHeader().getMsgType();
        if (msgType == 1001) {
            logger.debug("处理握手验证消息：" + handshakeRespMsg);
            String fromId = handshakeRespMsg.getHeader().getFromId();
            JSONObject jsonObj = JSON.parseObject(handshakeRespMsg.getHeader().getExtend());
            String token = jsonObj.getString("token");
            logger.info("fromId:" + fromId);
            logger.info("token:" + token);

            JSONObject resp = new JSONObject();

            if (validUserToken(fromId, token)) {
                //原来已经登录的被踢下线
                ServerHandler.NettyChannel prevChannel = ServerHandler.ChannelContainer.getInstance().getActiveChannelByUserId(fromId);
                if (prevChannel != null) {
                    sendForceLogoutMessage(prevChannel);
                }

                resp.put("status", 1);
                ServerHandler.ChannelContainer.getInstance().saveChannel(new ServerHandler.NettyChannel(fromId, ctx.channel()));
                handshakeRespMsg = handshakeRespMsg.toBuilder().setHeader(handshakeRespMsg.getHeader().toBuilder().setExtend(resp.toString()).build()).build();
                ctx.channel().writeAndFlush(handshakeRespMsg);
            } else {
                resp.put("status", -1);
                ServerHandler.ChannelContainer.getInstance().removeChannelIfConnectNoActive(ctx.channel());
                handshakeRespMsg = handshakeRespMsg.toBuilder().setHeader(handshakeRespMsg.getHeader().toBuilder().setExtend(resp.toString()).build()).build();
                ChannelFuture future = ctx.channel().writeAndFlush(handshakeRespMsg);
                future.addListener(ChannelFutureListener.CLOSE);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private boolean validUserToken(String userName, String token) {
        logger.info("userService:" + (userService == null));
        logger.info("jwtService:" + (jwtService == null));
        Optional<User> userOptional = userService.getUserRepo().findByName(userName);
        if (userOptional.isPresent()) {
            Optional<JwtAuthentication> authentication = jwtService.parse(token);
            if (authentication.isPresent()) {
                JwtAuthentication jwtAuthentication = authentication.get();
                String name = jwtAuthentication.getName();
                logger.info("name:" + name);
                Object detail = jwtAuthentication.getDetails();
                logger.info("detail:" + detail.getClass().getName());
                logger.info("detail:" + detail.toString());
                return true;
            }
        }
        return false;
    }

    private void sendForceLogoutMessage(ServerHandler.NettyChannel prevChannel) {
        TransMessageProtobuf.TransMessage.Builder messageBuilder = TransMessageProtobuf.TransMessage.newBuilder();
        TransMessageProtobuf.MessageHeader.Builder headerBuilder = TransMessageProtobuf.MessageHeader.newBuilder();
        headerBuilder.setMsgId(UUID.randomUUID().toString());
        headerBuilder.setMsgType(1003);
        headerBuilder.setTimestamp(System.currentTimeMillis());
        messageBuilder.setHeader(headerBuilder.build());
        messageBuilder.setBody("账号在其它地方登录,你已被强制下线");
        ChannelFuture future = prevChannel.getChannel().writeAndFlush(messageBuilder.build());
        future.addListener(ChannelFutureListener.CLOSE);
        ServerHandler.ChannelContainer.getInstance().removeChannelIfConnectNoActive(prevChannel.getChannel());
    }

}
