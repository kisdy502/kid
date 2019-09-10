package com.sdt.kid.bean;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "AppMessage", uniqueConstraints = @UniqueConstraint(columnNames = {"messageId"}))
public class AppMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fromId;                     //发送人
    private Long toId;                       //接收人
    private Long sendTime;                   //消息发送时间
    private Long endTime;                    //消息过期时间，过期了，就可以删除了
    @NotBlank
    private String messageId;
    private int messageType;
    private int messageContentType;           //文本 ，语音 ，图片，视频  0，1，2，3
    private int statusReport = 0;             //0 默认状态，已发送，未收到消息回执,1已发送给接收人，收到消息回执
    private String extend;                    //头扩展内容
    @Column(length = 65536)
    private String content;                   //消息内容

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
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

    public int getStatusReport() {
        return statusReport;
    }

    public void setStatusReport(int statusReport) {
        this.statusReport = statusReport;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
