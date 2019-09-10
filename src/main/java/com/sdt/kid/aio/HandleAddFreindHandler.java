package com.sdt.kid.aio;

import com.alibaba.fastjson.JSONObject;
import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.AppMessage;
import com.sdt.kid.bean.User;
import com.sdt.kid.repo.AppMessageRepo;
import com.sdt.kid.repo.UserRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class HandleAddFreindHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AppMessageRepo appMessageRepo;
    private UserRepo userRepo;

    public HandleAddFreindHandler() {
        appMessageRepo = ApplicationContextProvider.getApplicationContext().getBean(AppMessageRepo.class, "appMessageRepo");
        userRepo = ApplicationContextProvider.getApplicationContext().getBean(UserRepo.class, "userRepo");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage message = (TransMessageProtobuf.TransMessage) msg;
        if (message == null) {
            return;
        }

        int msgType = message.getMsgType();
        if (msgType == MessageType.MESSAGE_REQUEST_ADD_FRIEND.getMsgType()) {

            Long fromId = message.getFromId();
            Long fId = ServerHandler.ChannelContainer.getInstance().getUserIdByChannel(ctx.channel());
            logger.info("fromId:" + fromId);
            logger.info("fId:" + fId);

            TransMessageProtobuf.TransMessage reportStatusMessage = MessageHelper.buildReportStatusMessageBuild(message).build();
            MessageHelper.forwardMessage(fromId, reportStatusMessage);

            AppMessage appMessage = MessageHelper.ProtobufMsgToAppMessage(message);
            appMessageRepo.save(appMessage);

            Optional<User> userOptional = userRepo.findById(message.getToId());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", user.getId());
                jsonObject.put("name", user.getName());
                jsonObject.put("mobile", user.getMobile());
                jsonObject.put("tip", message.getContent());
                message = message.toBuilder().setContent(jsonObject.toJSONString()).build();
                MessageHelper.forwardMessage(message.getToId(), message);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }


}