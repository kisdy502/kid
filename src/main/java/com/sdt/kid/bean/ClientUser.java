package com.sdt.kid.bean;

import com.sdt.kid.aio.ServerHandler;

public class ClientUser {

    private String uId;
    private boolean onlined;
    private ServerHandler.NettyChannel nettyChannel;

    public ClientUser() {
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
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
