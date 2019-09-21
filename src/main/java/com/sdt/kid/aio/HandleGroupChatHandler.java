package com.sdt.kid.aio;

import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.GroupMember;
import com.sdt.kid.bean.UserGroup;
import com.sdt.kid.repo.AppMessageRepo;
import com.sdt.kid.repo.GroupMemberRepo;
import com.sdt.kid.repo.UserGroupRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Consumer;

public class HandleGroupChatHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AppMessageRepo appMessageRepo;
    private GroupMemberRepo groupMemberRepo;
    private UserGroupRepo userGroupRepo;

    public HandleGroupChatHandler() {
        appMessageRepo = ApplicationContextProvider.getApplicationContext().getBean(AppMessageRepo.class, "appMessageRepo");
        groupMemberRepo = ApplicationContextProvider.getApplicationContext().getBean(GroupMemberRepo.class, "groupMemberRepo");
        userGroupRepo = ApplicationContextProvider.getApplicationContext().getBean(UserGroupRepo.class, "userGroupRepo");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage message = (TransMessageProtobuf.TransMessage) msg;
        if (message == null) {
            return;
        }

        int msgType = message.getMsgType();
        if (msgType == MessageType.GROUP_CHAT.getMsgType()) {
            logger.info("群聊消息：" + message);

            Long fromId = message.getFromId();
            Long fId = ServerHandler.ChannelContainer.getInstance().getUserIdByChannel(ctx.channel());
            logger.info("fromId:" + fromId);
            logger.info("fId:" + fId);

            TransMessageProtobuf.TransMessage reportStatusMessage = MessageHelper.buildReportStatusMessageBuild(message).build();
            MessageHelper.forwardMessage(fromId, reportStatusMessage);

            Long groupId = message.getToId();
            if (groupId == null) {
                return;
            }

            Optional<UserGroup> userGroupOptional = userGroupRepo.findById(groupId);

            if (userGroupOptional.isPresent()) {
                UserGroup userGroup = userGroupOptional.get();
                Iterable<GroupMember> groupMemberIterable = groupMemberRepo.findByUserGroup(userGroup);
                groupMemberIterable.forEach(new Consumer<GroupMember>() {
                    @Override
                    public void accept(GroupMember groupMember) {
                        if (!groupMember.getCreatorId().equals(fromId)) {
                            MessageHelper.forwardMessage(groupMember.getCreatorId(), message);
                        }
                    }
                });
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }


}
