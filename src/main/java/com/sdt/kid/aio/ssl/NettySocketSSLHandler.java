package com.sdt.kid.aio.ssl;

import com.sdt.im.protobuf.TransMessageProtobuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.UUID;

public class NettySocketSSLHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("read:" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("ServerHandler channelActive()" + ctx.channel().remoteAddress());

        ctx.pipeline().get(SslHandler.class).handshakeFuture().addListener(
                new GenericFutureListener<Future<Channel>>() {
                    @Override
                    public void operationComplete(Future<Channel> future) throws Exception {
                        if (future.isSuccess()) {
                            logger.info("握手成功");
                        } else {
                            logger.info("握手失败");
                        }

                        String content = "Welcome to " + InetAddress.getLocalHost().getHostName() +
                                " secure chat service!\n";
                        TransMessageProtobuf.TransMessage transMessage = buildMessage(content);

                        String content1 = "Your session is protected by " +
                                ctx.pipeline().get(SslHandler.class).engine().getSession().getCipherSuite() +
                                " cipher suite.\n";
                        TransMessageProtobuf.TransMessage transMessage2 = buildMessage(content1);

                        ctx.channel().writeAndFlush(transMessage);
                        ctx.channel().writeAndFlush(transMessage2);

                    }
                });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        logger.info("ServerHandler channelInactive()");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        logger.info("ServerHandler exceptionCaught()");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        logger.info("ServerHandler userEventTriggered()");
    }

    private static TransMessageProtobuf.TransMessage buildMessage(String content) {
        TransMessageProtobuf.TransMessage.Builder builder = TransMessageProtobuf.TransMessage.newBuilder();
        builder.setMsgId(UUID.randomUUID().toString());
        builder.setMsgType(1000);
        builder.setSendTime(System.currentTimeMillis());
        builder.setFromId(1);
        builder.setToId(2);
        builder.setStatusReport(0);
        builder.setContent(content);
        return builder.build();
    }
}
