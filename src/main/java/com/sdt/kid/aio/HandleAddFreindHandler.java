package com.sdt.kid.aio;

import com.alibaba.fastjson.JSON;
import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.UserRelation;
import com.sdt.kid.repo.UserRelationRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HandleAddFreindHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private UserRelationRepo userRelationRepo;

    public HandleAddFreindHandler() {
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
        if (msgType == 1011) {
            System.out.println("请求添加你为好友：" + message);
            if (!StringUtils.isEmpty(message.getHeader().getToId())) {
                String toId = message.getHeader().getToId();
                ServerHandler.NettyChannel toNettyChannel = ServerHandler.ChannelContainer.getInstance().getActiveChannelByUserId(toId);
                if (toNettyChannel != null) {
                    toNettyChannel.getChannel().writeAndFlush(message);
                } else {
                    System.out.println("不在线,接收者..." + toId);
                    //TODO save 2 db
                }
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

}