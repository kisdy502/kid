package com.sdt.kid.aio;

import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.repo.UserRelationRepo;
import com.sdt.kid.repo.UserRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandleInvitationAddGroupHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private UserRelationRepo userRelationRepo;
    private UserRepo userRepo;

    public HandleInvitationAddGroupHandler() {
        userRelationRepo = ApplicationContextProvider.getApplicationContext().getBean(UserRelationRepo.class, "userRelationRepo");
        userRepo = ApplicationContextProvider.getApplicationContext().getBean(UserRepo.class, "userRepo");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage message = (TransMessageProtobuf.TransMessage) msg;
        if (message == null) {
            return;
        }

        int msgType = message.getMsgType();
        if (msgType == MessageType.MESSAGE_INVITATION_ADD_GROUP.getMsgType()) {
            logger.debug("邀请加群：" + message);
            Long fromId = message.getFromId();
            Long fId = ServerHandler.ChannelContainer.getInstance().getUserIdByChannel(ctx.channel());
            logger.info("fromId:" + fromId);
            logger.info("fId:" + fId);

            TransMessageProtobuf.TransMessage reportStatusMessage = MessageHelper.buildReportStatusMessageBuild(message).build();
            MessageHelper.forwardMessage(fromId, reportStatusMessage);

            String result = message.getContent();
            long[] userIdArray = string2LongArray(result);
            for (long userId : userIdArray) {
                MessageHelper.forwardMessage(fromId, MessageHelper.buildInvitationAddGroupMessageBuild(fromId, userId).build());
            }

        } else {
            ctx.fireChannelRead(msg);
        }
    }


    private long[] string2LongArray(String content) {
        String[] strings = content.split(",");
        long[] userIdArray = new long[strings.length];
        for (int i = 0, len = strings.length; i < len; i++) {
            userIdArray[i] = Long.parseLong(strings[i]);
        }
        return userIdArray;
    }


}