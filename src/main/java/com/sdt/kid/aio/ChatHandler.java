package com.sdt.kid.aio;

import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.AppMessage;
import com.sdt.kid.repo.AppMessageRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatHandler extends ChannelInboundHandlerAdapter {

    private AppMessageRepo appMessageRepo;

    public ChatHandler() {
        appMessageRepo = ApplicationContextProvider.getApplicationContext().getBean(AppMessageRepo.class, "appMessageRepo");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage message = (TransMessageProtobuf.TransMessage) msg;
        if (message == null || message.getHeader() == null) {
            return;
        }

        int msgType = message.getHeader().getMsgType();
        if (msgType == 2001) {
            System.out.println("聊天消息：" + message);
            // 收到2001或3001消息，返回给客户端消息发送状态报告
            String fromId = message.getHeader().getFromId();
            TransMessageProtobuf.TransMessage.Builder sentReportMsgBuilder = TransMessageProtobuf.TransMessage.newBuilder();
            TransMessageProtobuf.MessageHeader.Builder sentReportHeadBuilder = TransMessageProtobuf.MessageHeader.newBuilder();
            sentReportHeadBuilder.setMsgId(message.getHeader().getMsgId());
            sentReportHeadBuilder.setMsgType(1010);
            sentReportHeadBuilder.setTimestamp(System.currentTimeMillis());
            sentReportHeadBuilder.setStatusReport(1);
            sentReportMsgBuilder.setHeader(sentReportHeadBuilder.build());

            ServerHandler.NettyChannel fromNettyChannel = ServerHandler.ChannelContainer.getInstance().getActiveChannelByUserId(fromId);
            if (fromNettyChannel != null) {
                fromNettyChannel.getChannel().writeAndFlush(sentReportMsgBuilder.build());
            } else {
                System.out.println("没有发送者..." + fromId);
            }

            //save message to db
            AppMessage appMessage = new AppMessage();
            appMessage.setFromId(fromId);
            appMessage.setSendTime(System.currentTimeMillis());
            appMessage.setToId(message.getHeader().getToId());
            appMessage.setMessageId(message.getHeader().getMsgId());
            appMessage.setEndTime(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
            appMessage.setMessageType(message.getHeader().getMsgType());
            appMessage.setMessageReportStatus(0);
            appMessage.setMessageContentType(message.getHeader().getMsgContentType());
            appMessage.setContent(message.getBody());
            appMessageRepo.save(appMessage);

            // 同时转发消息到接收方
            String toId = message.getHeader().getToId();
            ServerHandler.NettyChannel toNettyChannel = ServerHandler.ChannelContainer.getInstance().getActiveChannelByUserId(toId);
            if (toNettyChannel != null) {
                toNettyChannel.getChannel().writeAndFlush(message);
            } else {
                System.out.println("没有接收者..." + toId);
            }
        } else {
            System.out.println("未知类型消息:" + message);
            ctx.fireChannelRead(msg);
        }
    }
}
