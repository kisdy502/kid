package com.sdt.kid.aio;

import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.AppMessage;
import com.sdt.kid.bean.Friend;
import com.sdt.kid.bean.User;
import com.sdt.kid.bean.UserRelation;
import com.sdt.kid.repo.AppMessageRepo;
import com.sdt.kid.repo.UserRelationRepo;
import com.sdt.kid.repo.UserRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class HandleAddFriendResultHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AppMessageRepo appMessageRepo;
    private UserRelationRepo userRelationRepo;
    private UserRepo userRepo;

    public HandleAddFriendResultHandler() {
        appMessageRepo = ApplicationContextProvider.getApplicationContext().getBean(AppMessageRepo.class, "appMessageRepo");
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
        if (msgType == MessageType.MESSAGE_AGREE_OR_REFUSE_ADD_FRIEND.getMsgType()) {

            Long fromId = message.getFromId();
            Long fId = ServerHandler.ChannelContainer.getInstance().getUserIdByChannel(ctx.channel());
            logger.info("fromId:" + fromId);
            logger.info("fId:" + fId);

            TransMessageProtobuf.TransMessage reportStatusMessage = MessageHelper.buildReportStatusMessageBuild(message).build();
            Long toId = message.getToId();
            MessageHelper.forwardMessage(fromId, reportStatusMessage);

            AppMessage appMessage = MessageHelper.ProtobufMsgToAppMessage(message);
            appMessageRepo.save(appMessage);

            if ("true".equalsIgnoreCase(message.getContent())) {
                UserRelation userMyRelation = new UserRelation(fromId, toId);
                UserRelation userFriendRelation = new UserRelation(toId, fromId);

                userRelationRepo.save(userMyRelation);
                userRelationRepo.save(userFriendRelation);

                Optional<User> userMyOptional = userRepo.findById(fromId);
                if (userMyOptional.isPresent()) {
                    User userMy = userMyOptional.get();
                    Friend friend = new Friend();
                    friend.setMyId(toId);
                    friend.setFriendId(fromId);
                    friend.setName(userMy.getName());
                    friend.setMobile(userMy.getMobile());
                    TransMessageProtobuf.TransMessage fromMsg = MessageHelper.buildAgreeAddFriendMessage(friend, 0L,
                            toId).build();
                    MessageHelper.forwardMessage(toId, fromMsg);
                }

                Optional<User> userFriendOptional = userRepo.findById(toId);
                if (userFriendOptional.isPresent()) {
                    User userFriend = userFriendOptional.get();
                    Friend friend = new Friend();
                    friend.setMyId(fromId);
                    friend.setFriendId(toId);
                    friend.setName(userFriend.getName());
                    friend.setMobile(userFriend.getMobile());
                    TransMessageProtobuf.TransMessage toMsg = MessageHelper.buildAgreeAddFriendMessage(friend, 0L,
                            fromId).build();
                    MessageHelper.forwardMessage(fromId, toMsg);
                }
            } else if ("false".equalsIgnoreCase(message.getContent())) {
                TransMessageProtobuf.TransMessage fromMsg = MessageHelper.buildRefuseAddFriendMessage(0L, fromId).build();
                TransMessageProtobuf.TransMessage toMsg = MessageHelper.buildRefuseAddFriendMessage(0L, message.getToId()).build();
                MessageHelper.forwardMessage(fromId, fromMsg);
                MessageHelper.forwardMessage(message.getToId(), toMsg);
            }

            //logger.info("{}请求添加{}为好友：", message.getFromId(), message.getToId());
            //MessageHelper.forwardMessage(message.getToId(), message);
        } else {
            ctx.fireChannelRead(msg);
        }
    }


}