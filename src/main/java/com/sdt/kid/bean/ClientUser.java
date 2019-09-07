package com.sdt.kid.bean;

import com.sdt.kid.aio.ServerHandler;

public class ClientUser {

    private Long id;
    private String userName;

    private boolean onlined;

    private ServerHandler.NettyChannel nettyChannel;

    public ClientUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isOnlined() {
        return onlined;
    }

    public void setOnlined(boolean onlined) {
        this.onlined = onlined;
    }

    public ServerHandler.NettyChannel getNettyChannel() {
        return nettyChannel;
    }

    public void setNettyChannel(ServerHandler.NettyChannel nettyChannel) {
        this.nettyChannel = nettyChannel;
    }
}
