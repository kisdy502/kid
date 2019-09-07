package com.sdt.kid.aio;

import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.Friend;
import com.sdt.kid.bean.User;
import com.sdt.kid.bean.UserRelation;
import com.sdt.kid.repo.UserRelationRepo;
import com.sdt.kid.repo.UserRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HandleFriendListHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private UserRelationRepo userRelationRepo;
    private UserRepo userRepo;

    public HandleFriendListHandler() {
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
        if (msgType == MessageType.GET_USER_FRIEND_LIST.getMsgType()) {
            logger.debug("请求好友列表：" + message);

            //TODO 优化代码，提高效率
            if (userRelationRepo.findByMyId(message.getFromId()).isPresent()) {
                List<UserRelation> userRelations = userRelationRepo.findByMyId(message.getFromId()).get();
                List<Friend> friendList = new ArrayList<>();
                User user;
                for (UserRelation userRelation : userRelations) {
                    Optional<User> userOptional = userRepo.findById(userRelation.getFriendId());
                    if (userOptional.isPresent()) {
                        user = userOptional.get();
                        Friend friend = new Friend();
                        friend.setMyId(message.getFromId());
                        friend.setFriendId(user.getId());
                        friend.setName(user.getName());
                        friend.setMobile(user.getMobile());
                        friendList.add(friend);
                    }
                }
                ctx.channel().writeAndFlush(MessageHelper.getRealtionListMessage(friendList));
            } else {
                ctx.channel().writeAndFlush(MessageHelper.getRealtionListMessage(new ArrayList<Friend>()));
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }


}