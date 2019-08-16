package com.sdt.kid.bean;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "AppMessage") // 唯一约束name
public class AppMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String fromId;         //发送人
    @NotBlank
    private String toId;           //接收人
    private long sendTime;         //消息发送时间
    private long endTime;          //消息过期时间，过期了，就可以删除了
    @NotBlank
    private String messageId;
    private int messageType;
    private int messageContentType;    //文本 ，语音 ，图片，视频  0，1，2，3
    private int messageReportStatus;   //0 默认状态，已发送，未收到消息回执,1已发送给接收人，收到消息回执
    @NotBlank
    private String content;            //消息内容

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getMessageContentType() {
        return messageContentType;
    }

    public void setMessageContentType(int messageContentType) {
        this.messageContentType = messageContentType;
    }

    public int getMessageReportStatus() {
        return messageReportStatus;
    }

    public void setMessageReportStatus(int messageReportStatus) {
        this.messageReportStatus = messageReportStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
