package com.sdt.kid.aio;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       MessageType.java</p>
 * <p>@PackageName:     com.freddy.chat.im</p>
 * <b>
 * <p>@Description:     消息类型</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/08 00:04</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public enum MessageType {

    /*
     * 系统推送消息
     */
    SYSTEMMESSAGE(1000),

    /*
     * 握手消息
     */
    HANDSHAKE(1001),

    /*
     * 心跳消息
     */
    HEARTBEAT(1003),


    /*
     * 客户端强制下线，在其它地方登录，被踢下线
     */
    FORCE_CLIENT_LOGOUT(1005),

    /*
     * 客户端提交的消息接收状态报告
     */
    CLIENT_MSG_RECEIVED_STATUS_REPORT(1009),

    /*
     * 服务端返回的消息发送状态报告
     */
    SERVER_MSG_SENT_STATUS_REPORT(1010),


    /*
     * 添加好友
     */
    MESSAGE_REQUEST_ADD_FRIEND(1011),

    /*
     * 同意/拒绝添加好友
     */
    MESSAGE_AGREE_OR_REFUSE_ADD_FRIEND(1012),

    /*
     * 同意好友请求通知
     */
    MESSAGE_AGREE_ADD_FRIEND_RESULT(1013),

    /*
     * 拒绝好友请求通知
     */
    MESSAGE_REFUSE_ADD_FRIEND_RESULT(1015),


    /*
     * 返回好友列表
     */
    GET_USER_FRIEND_LIST(1101),

    /*
     * 离线消息
     */
    GET_OUTLINE_MESSAGE_LIST(1103),

    /*
     * 上报收到的离线消息列表
     */
    REPORT_RECEIVED_OUTLINE_MESSAGE_LIST(1105),

    /*
     * 发送失败的消息重发
     */
    RESEND_FAILED_MESSAGE_LIST(1107),

    /*
     * 创建群
     */
    MESSAGE_REQUEST_CREATE_GROUP(1109),


    /*
     * 创建群结果
     */
    MESSAGE_REQUEST_CREATE_GROUP_RESULT(1120),


    /*
     * 申请加群
     */
    MESSAGE_ASK_FOR_ADD_GROUP(1121),

    /*
     * 邀请加群
     */
    MESSAGE_INVITATION_ADD_GROUP(1123),

    /**
     * 单聊消息
     */
    SINGLE_CHAT(2001),

    /**
     * 群聊消息
     */
    GROUP_CHAT(3001);

    private int msgType;

    MessageType(int msgType) {
        this.msgType = msgType;
    }

    public int getMsgType() {
        return this.msgType;
    }

    public enum MessageContentType {

        /**
         * 文本消息
         */
        TEXT(101),

        /**
         * 图片消息
         */
        IMAGE(102),

        /**
         * 语音消息
         */
        VOICE(103);


        private int msgContentType;


        MessageContentType(int msgContentType) {
            this.msgContentType = msgContentType;
        }


        public int getMsgContentType() {
            return this.msgContentType;
        }
    }
}
