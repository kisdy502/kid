package com.sdt.kid.aio;

import com.alibaba.fastjson.JSON;
import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.bean.AppMessage;
import com.sdt.kid.bean.UserRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

public class MessageHelper {

    private static Logger logger = LoggerFactory.getLogger(MessageHelper.class);

    public static TransMessageProtobuf.TransMessage getReportStatusMessage(TransMessageProtobuf.TransMessage message) {
        // 收到2001或3001消息，返回给客户端消息发送状态报告
        return buildMessage(message.getHeader().getMsgId(),
                MessageType.SERVER_MSG_SENT_STATUS_REPORT.getMsgType(),
                "",
                System.currentTimeMillis(),
                1
        );


    }

    public static AppMessage ProtobufMsgToAppMessage(TransMessageProtobuf.TransMessage message) {
        AppMessage appMessage = new AppMessage();
        appMessage.setFromId(message.getHeader().getFromId());
        appMessage.setSendTime(System.currentTimeMillis());
        appMessage.setToId(message.getHeader().getToId());
        appMessage.setMessageId(message.getHeader().getMsgId());
        appMessage.setEndTime(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
        appMessage.setMessageType(message.getHeader().getMsgType());
        appMessage.setStatusReport(0);
        appMessage.setMessageContentType(message.getHeader().getMsgContentType());
        appMessage.setContent(message.getBody());
        return appMessage;
    }

    public static TransMessageProtobuf.TransMessage getOutLineList(List<AppMessage> appMessageList) {
        return buildMessage(UUID.randomUUID().toString(),
                MessageType.GET_OUTLINE_MESSAGE_LIST.getMsgType(),
                JSON.toJSONString(appMessageList),
                System.currentTimeMillis(),
                0
        );
    }

    public static TransMessageProtobuf.TransMessage getRealtionListMessage(List<UserRelation> userRelations) {
        return buildMessage(UUID.randomUUID().toString(),
                MessageType.GET_USER_FRIEND_LIST.getMsgType(),
                JSON.toJSONString(userRelations),
                System.currentTimeMillis(),
                0
        );

    }

    public static TransMessageProtobuf.TransMessage getForceLogoutMessage() {
        return buildMessage(UUID.randomUUID().toString(),
                MessageType.FORCE_CLIENT_LOGOUT.getMsgType(),
                "账号在其它地方登录,你已被强制下线",
                System.currentTimeMillis(),
                0
        );
    }

    private static TransMessageProtobuf.TransMessage buildMessage(String messageId, int type, String content, long sendTime, int reportStatus) {
        TransMessageProtobuf.TransMessage.Builder builder = TransMessageProtobuf.TransMessage.newBuilder();
        TransMessageProtobuf.MessageHeader.Builder headerBuilder = TransMessageProtobuf.MessageHeader.newBuilder();
        headerBuilder.setMsgId(messageId);
        headerBuilder.setMsgType(type);
        headerBuilder.setTimestamp(sendTime);
        headerBuilder.setStatusReport(reportStatus);
        builder.setHeader(headerBuilder.build());
        builder.setBody(content);
        return builder.build();
    }


    public static TransMessageProtobuf.TransMessage getProtoBufMessageBuilderByAppMessage(AppMessage message) {
        TransMessageProtobuf.TransMessage.Builder builder = TransMessageProtobuf.TransMessage.newBuilder();
        TransMessageProtobuf.MessageHeader.Builder headBuilder = TransMessageProtobuf.MessageHeader.newBuilder();
        headBuilder.setMsgType(message.getMessageType());
        headBuilder.setStatusReport(message.getStatusReport());
        headBuilder.setMsgContentType(message.getMessageContentType());
        if (!StringUtils.isEmpty(message.getMessageId()))
            headBuilder.setMsgId(message.getMessageId());
        if (!StringUtils.isEmpty(message.getFromId()))
            headBuilder.setFromId(message.getFromId());
        if (!StringUtils.isEmpty(message.getToId()))
            headBuilder.setToId(message.getToId());
        if (message.getSendTime() != 0)
            headBuilder.setTimestamp(message.getSendTime());
        if (!StringUtils.isEmpty(message.getExtend()))
            headBuilder.setExtend(message.getExtend());
        if (!StringUtils.isEmpty(message.getContent()))
            builder.setBody(message.getContent());
        builder.setHeader(headBuilder);
        return builder.build();
    }

    public static void forwardMessage(String userId, TransMessageProtobuf.TransMessage message) {
        if (StringUtils.isEmpty(userId)) {
            logger.info("userId is null or empty...");
            return;
        }
        ServerHandler.NettyChannel toNettyChannel = ServerHandler.ChannelContainer.getInstance().getActiveChannelByUserId(userId);
        if (toNettyChannel != null) {
            toNettyChannel.getChannel().writeAndFlush(message);
        } else {
            logger.info("用户{}不在线...", userId);
        }
    }


}
