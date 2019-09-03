package com.sdt.kid.aio;

import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.UserRelation;
import com.sdt.kid.repo.UserRelationRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HandleFriendListHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private UserRelationRepo userRelationRepo;

    public HandleFriendListHandler() {
        userRelationRepo = ApplicationContextProvider.getApplicationContext().getBean(UserRelationRepo.class, "userRelationRepo");
        logger.info("userRelationRepo is null:" + (userRelationRepo == null));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage message = (TransMessageProtobuf.TransMessage) msg;
        if (message == null || message.getHeader() == null) {
            return;
        }

        int msgType = message.getHeader().getMsgType();
        if (msgType == MessageType.GET_USER_FRIEND_LIST.getMsgType()) {
            logger.debug("请求好友列表：" + message);
            if (message.getHeader().getFromId() != null) {
                if (userRelationRepo.findByMyName(message.getHeader().getFromId()).isPresent()) {
                    List<UserRelation> userRelations = userRelationRepo.findByMyName(message.getHeader().getFromId()).get();
                    ctx.channel().writeAndFlush(MessageHelper.getRealtionListMessage(userRelations));
                } else {
                    ctx.channel().writeAndFlush(MessageHelper.getRealtionListMessage(new ArrayList<UserRelation>()));
                }
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }


}