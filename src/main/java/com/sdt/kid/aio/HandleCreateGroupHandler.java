package com.sdt.kid.aio;

import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.AppMessage;
import com.sdt.kid.bean.GroupMember;
import com.sdt.kid.bean.User;
import com.sdt.kid.bean.UserGroup;
import com.sdt.kid.repo.AppMessageRepo;
import com.sdt.kid.repo.UserGroupRepo;
import com.sdt.kid.repo.UserRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class HandleCreateGroupHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AppMessageRepo appMessageRepo;
    private UserGroupRepo userGroupRepo;
    private UserRepo userRepo;

    public HandleCreateGroupHandler() {
        appMessageRepo = ApplicationContextProvider.getApplicationContext().getBean(AppMessageRepo.class, "appMessageRepo");
        userGroupRepo = ApplicationContextProvider.getApplicationContext().getBean(UserGroupRepo.class, "userGroupRepo");
        userRepo = ApplicationContextProvider.getApplicationContext().getBean(UserRepo.class, "userRepo");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage message = (TransMessageProtobuf.TransMessage) msg;
        if (message == null) {
            return;
        }

        int msgType = message.getMsgType();
        if (msgType == MessageType.MESSAGE_REQUEST_CREATE_GROUP.getMsgType()) {

            Long fromId = message.getFromId();
            Optional<User> senderOption = userRepo.findById(fromId);
            if (senderOption.isPresent()) {
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

                UserGroup userGroup = new UserGroup();
                userGroup.setCreateTime(System.currentTimeMillis());
                userGroup.setCreatorId(senderOption.get().getId());
                GroupMember groupMember = new GroupMember();

                groupMember.setCreatorId(senderOption.get().getId());
                userGroup.addGroupMember(groupMember);

                UserGroup savedGroup = userGroupRepo.save(userGroup);

                TransMessageProtobuf.TransMessage transMessage = MessageHelper.getGroupCreatedMessage(savedGroup);

                AppMessage groupCreatedMessage = MessageHelper.ProtobufMsgToAppMessage(transMessage);
                groupCreatedMessage.setFromId(fromId);
                groupCreatedMessage.setToId(fromId);
                appMessageRepo.save(groupCreatedMessage);

                ctx.channel().writeAndFlush(transMessage);
            }


        } else {
            ctx.fireChannelRead(msg);
        }
    }


}