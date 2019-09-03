package com.sdt.kid.aio;

import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.AppMessage;
import com.sdt.kid.repo.AppMessageRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandleAddFreindHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AppMessageRepo appMessageRepo;

    public HandleAddFreindHandler() {
        appMessageRepo = ApplicationContextProvider.getApplicationContext().getBean(AppMessageRepo.class, "appMessageRepo");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage message = (TransMessageProtobuf.TransMessage) msg;
        if (message == null || message.getHeader() == null) {
            return;
        }

        int msgType = message.getHeader().getMsgType();
        if (msgType == MessageType.MESSAGE_REQUEST_ADD_FRIEND.getMsgType()) {

            TransMessageProtobuf.TransMessage reportStatusMessage = MessageHelper.getReportStatusMessage(message);
            String fromId = message.getHeader().getFromId();
            MessageHelper.forwardMessage(fromId, reportStatusMessage);

            AppMessage appMessage = MessageHelper.ProtobufMsgToAppMessage(message);
            appMessageRepo.save(appMessage);

            logger.info("{}请求添加{}为好友：", message.getHeader().getFromId(), message.getHeader().getToId());
            MessageHelper.forwardMessage(message.getHeader().getToId(), message);
        } else {
            ctx.fireChannelRead(msg);
        }
    }


}