package com.sdt.kid.aio;

import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.AppMessage;
import com.sdt.kid.repo.AppMessageRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class HandleChatHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AppMessageRepo appMessageRepo;

    public HandleChatHandler() {
        appMessageRepo = ApplicationContextProvider.getApplicationContext().getBean(AppMessageRepo.class, "appMessageRepo");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage message = (TransMessageProtobuf.TransMessage) msg;
        if (message == null) {
            return;
        }

        int msgType = message.getMsgType();
        if (msgType == MessageType.SINGLE_CHAT.getMsgType()) {
            System.out.println("聊天消息：" + message);

            Long fromId = message.getFromId();
            TransMessageProtobuf.TransMessage reportStatusMessage = MessageHelper.getReportStatusMessage(message);
            MessageHelper.forwardMessage(fromId, reportStatusMessage);

            //save message to db
            AppMessage appMessage = MessageHelper.ProtobufMsgToAppMessage(message);

            Optional<AppMessage> optional = appMessageRepo.findByMessageId(appMessage.getMessageId());
            if (optional.isPresent()) {
                logger.info("消息已经接收,无需再发了...", appMessage.getMessageId());
                return;
            }
            appMessageRepo.save(appMessage);

            // 同时转发消息到接收方
            Long toId = message.getToId();
            MessageHelper.forwardMessage(toId, message);
        } else {
            logger.debug("未知类型消息:" + message);
            ctx.fireChannelRead(msg);
        }
    }



}
