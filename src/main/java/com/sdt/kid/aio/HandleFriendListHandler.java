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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        if (msgType == 1101) {
            System.out.println("请求好友列表：" + message);
            if (message.getHeader().getFromId() != null) {
                if (userRelationRepo.findByMyName(message.getHeader().getFromId()).isPresent()) {
                    List<UserRelation> userRelations = userRelationRepo.findByMyName(message.getHeader().getFromId()).get();
                    sendList(userRelations, ctx);
                } else {
                    sendList(new ArrayList<UserRelation>(), ctx);
                }
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private void sendList(List<UserRelation> userRelations, ChannelHandlerContext ctx) {
        TransMessageProtobuf.TransMessage.Builder sentReportMsgBuilder = TransMessageProtobuf.TransMessage.newBuilder();
        TransMessageProtobuf.MessageHeader.Builder sentReportHeadBuilder = TransMessageProtobuf.MessageHeader.newBuilder();
        sentReportHeadBuilder.setMsgId(UUID.randomUUID().toString());
        sentReportHeadBuilder.setMsgType(1101);
        sentReportHeadBuilder.setTimestamp(System.currentTimeMillis());
        sentReportMsgBuilder.setHeader(sentReportHeadBuilder.build());
        sentReportMsgBuilder.setBody(JSON.toJSONString(userRelations));
        ctx.writeAndFlush(sentReportMsgBuilder);
    }

}