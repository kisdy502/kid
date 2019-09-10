package com.sdt.kid.aio;

import com.alibaba.fastjson.JSON;
import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

public class MessageHelper {

    private static Logger logger = LoggerFactory.getLogger(MessageHelper.class);

    public static TransMessageProtobuf.TransMessage.Builder buildReportStatusMessageBuild(TransMessageProtobuf.TransMessage message) {
        // 收到2001或3001消息，返回给客户端消息发送状态报告
        return buildAppMessageBuild(message.getMsgId(), 0L, 0L,
                MessageType.SERVER_MSG_SENT_STATUS_REPORT.getMsgType(),
                "",
                "",
                System.currentTimeMillis(),
                1
        );
    }


    public static TransMessageProtobuf.TransMessage.Builder buildGroupCreatedMessage(UserGroup userGroup) {
        return buildAppMessageBuild(UUID.randomUUID().toString(), 0L, 0L,
                MessageType.MESSAGE_REQUEST_CREATE_GROUP_RESULT.getMsgType(),
                JSON.toJSONString(userGroup),
                "",
                System.currentTimeMillis(),
                0
        );
    }

    public static TransMessageProtobuf.TransMessage.Builder buildOutLineList(List<AppMessage> appMessageList) {
        return buildAppMessageBuild(UUID.randomUUID().toString(), 0L, 0L,
                MessageType.GET_OUTLINE_MESSAGE_LIST.getMsgType(),
                "",
                JSON.toJSONString(appMessageList),
                System.currentTimeMillis(),
                0
        );
    }

    public static TransMessageProtobuf.TransMessage.Builder buildRealtionListMessage(List<Friend> friendList) {
        return buildAppMessageBuild(UUID.randomUUID().toString(), 0L, 0L,
                MessageType.GET_USER_FRIEND_LIST.getMsgType(),
                "",
                JSON.toJSONString(friendList),
                System.currentTimeMillis(),
                0
        );

    }

    public static TransMessageProtobuf.TransMessage.Builder buildForceLogoutMessage() {
        return buildAppMessageBuild(UUID.randomUUID().toString(), 0L, 0L,
                MessageType.FORCE_CLIENT_LOGOUT.getMsgType(),
                "",
                "账号在其它地方登录,你已被强制下线",
                System.currentTimeMillis(),
                0
        );
    }


    public static TransMessageProtobuf.TransMessage.Builder buildRefuseAddFriendMessage(Long fromId, Long toId) {
        return buildAppMessageBuild(UUID.randomUUID().toString(), fromId, toId,
                MessageType.MESSAGE_REFUSE_ADD_FRIEND_RESULT.getMsgType(),
                "",
                "",
                System.currentTimeMillis(),
                0
        );
    }

    public static TransMessageProtobuf.TransMessage.Builder buildAgreeAddFriendMessage(Friend friend, Long fromId, Long toId) {
        return buildAppMessageBuild(UUID.randomUUID().toString(), fromId, toId,
                MessageType.MESSAGE_AGREE_ADD_FRIEND_RESULT.getMsgType(),
                "",
                JSON.toJSONString(friend),
                System.currentTimeMillis(),
                0
        );
    }

    private static TransMessageProtobuf.TransMessage.Builder buildAppMessageBuild(String messageId, Long fromId, Long toId, int type, String extend, String content, long sendTime, int reportStatus) {
        TransMessageProtobuf.TransMessage.Builder builder = TransMessageProtobuf.TransMessage.newBuilder();
        builder.setMsgId(messageId);
        builder.setMsgType(type);
        builder.setSendTime(sendTime);
        builder.setFromId(fromId);
        builder.setToId(toId);
        builder.setExtend(extend);
        builder.setStatusReport(reportStatus);
        builder.setContent(content);
        return builder;
    }


    public static TransMessageProtobuf.TransMessage.Builder getProtoBufMessageBuilderByAppMessage(AppMessage message) {
        TransMessageProtobuf.TransMessage.Builder builder = TransMessageProtobuf.TransMessage.newBuilder();
        builder.setMsgType(message.getMessageType());
        builder.setStatusReport(message.getStatusReport());
        builder.setMsgContentType(message.getMessageContentType());
        if (!StringUtils.isEmpty(message.getMessageId()))
            builder.setMsgId(message.getMessageId());
        if (!StringUtils.isEmpty(message.getFromId()))
            builder.setFromId(message.getFromId());
        if (!StringUtils.isEmpty(message.getToId()))
            builder.setToId(message.getToId());
        if (message.getSendTime() != 0)
            builder.setSendTime(message.getSendTime());
        if (!StringUtils.isEmpty(message.getExtend()))
            builder.setExtend(message.getExtend());
        if (!StringUtils.isEmpty(message.getContent()))
            builder.setContent(message.getContent());
        return builder;
    }

    public static AppMessage ProtobufMsgToAppMessage(TransMessageProtobuf.TransMessage message) {
        AppMessage appMessage = new AppMessage();
        appMessage.setFromId(message.getFromId());
        appMessage.setSendTime(System.currentTimeMillis());
        appMessage.setToId(message.getToId());
        appMessage.setMessageId(message.getMsgId());
        appMessage.setEndTime(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
        appMessage.setMessageType(message.getMsgType());
        appMessage.setStatusReport(0);
        appMessage.setMessageContentType(message.getMsgContentType());
        appMessage.setContent(message.getContent());
        return appMessage;
    }

    public static void forwardMessage(Long userId, TransMessageProtobuf.TransMessage message) {
        if (0L == userId) {
            logger.info("userId ignore...");
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
