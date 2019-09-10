package com.sdt.kid.aio;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.AppMessage;
import com.sdt.kid.repo.AppMessageRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

public class HandleResendMessageListHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AppMessageRepo appMessageRepo;

    public HandleResendMessageListHandler() {
        appMessageRepo = ApplicationContextProvider.getApplicationContext().getBean(AppMessageRepo.class, "appMessageRepo");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage message = (TransMessageProtobuf.TransMessage) msg;

        int msgType = message.getMsgType();
        if (msgType == MessageType.RESEND_FAILED_MESSAGE_LIST.getMsgType()) {
            String messageListString = message.getContent();
            logger.info("重发消息列表:" + messageListString);
            Long fromId = message.getFromId();
            Long fId = ServerHandler.ChannelContainer.getInstance().getUserIdByChannel(ctx.channel());
            logger.info("fromId:" + fromId);
            logger.info("fId:" + fId);

            TransMessageProtobuf.TransMessage reportStatusMessage = MessageHelper.buildReportStatusMessageBuild(message).build();
            MessageHelper.forwardMessage(fromId, reportStatusMessage);


            if (StringUtils.isEmpty(messageListString)) {
                return;
            }
            List<AppMessage> appMessageList = JSON.parseObject(messageListString, new TypeReference<List<AppMessage>>() {
            });

            if (appMessageList.size() == 0) {
                return;
            }
            logger.info("appMessageList:" + appMessageList.toString());
            for (AppMessage appMessage : appMessageList) {

                TransMessageProtobuf.TransMessage transMessage = MessageHelper.getProtoBufMessageBuilderByAppMessage(appMessage).build();

                reportStatusMessage = MessageHelper.buildReportStatusMessageBuild(message).build();
                MessageHelper.forwardMessage(fromId, reportStatusMessage);

                Optional<AppMessage> optional = appMessageRepo.findByMessageId(appMessage.getMessageId());
                if (optional.isPresent()) {
                    logger.info("消息已经接收,无需再发了...", appMessage.getMessageId());
                    return;
                }

                appMessage.setStatusReport(0);
                appMessage.setEndTime(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
                appMessageRepo.save(appMessage);

                Long toId = transMessage.getToId();
                ServerHandler.NettyChannel toNettyChannel = ServerHandler.ChannelContainer.getInstance().getActiveChannelByUserId(toId);
                if (toNettyChannel != null) {
                    toNettyChannel.getChannel().writeAndFlush(transMessage);
                } else {
                    logger.info("接收者{}不在线...", toId);
                }
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }


}