package com.sdt.kid.aio;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.auth.JwtAuthentication;
import com.sdt.kid.bean.User;
import com.sdt.kid.service.JwtService;
import com.sdt.kid.service.UserService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class HandshakeHandler extends ChannelInboundHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    JwtService jwtService;
    UserService userService;

    public HandshakeHandler() {
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
            System.out.println("处理握手验证消息：" + handshakeRespMsg);
            String fromId = handshakeRespMsg.getHeader().getFromId();
            JSONObject jsonObj = JSON.parseObject(handshakeRespMsg.getHeader().getExtend());
            String token = jsonObj.getString("token");
            logger.info("fromId:" + fromId);
            logger.info("token:" + token);

            JSONObject resp = new JSONObject();

            if (validUserToken(fromId, token)) {
                resp.put("status", 1);
                ServerHandler.ChannelContainer.getInstance().saveChannel(new ServerHandler.NettyChannel(fromId, ctx.channel()));
                handshakeRespMsg = handshakeRespMsg.toBuilder().setHeader(handshakeRespMsg.getHeader().toBuilder().setExtend(resp.toString()).build()).build();
                ctx.channel().writeAndFlush(handshakeRespMsg);
            } else {
                resp.put("status", -1);
                ServerHandler.ChannelContainer.getInstance().removeChannelIfConnectNoActive(ctx.channel());
                handshakeRespMsg = handshakeRespMsg.toBuilder().setHeader(handshakeRespMsg.getHeader().toBuilder().setExtend(resp.toString()).build()).build();
                ctx.channel().writeAndFlush(handshakeRespMsg);
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

}
