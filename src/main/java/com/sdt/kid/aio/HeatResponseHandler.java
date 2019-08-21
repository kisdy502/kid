package com.sdt.kid.aio;

import com.sdt.im.protobuf.TransMessageProtobuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HeatResponseHandler extends ChannelInboundHandlerAdapter {

    public HeatResponseHandler() {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage heatMsg = (TransMessageProtobuf.TransMessage) msg;
        if (heatMsg == null || heatMsg.getHeader() == null) {
            return;
        }
        int msgType = heatMsg.getHeader().getMsgType();
        if (msgType == 1002) {
            System.out.println("客户端心跳消息：" + heatMsg);
            String fromId = heatMsg.getHeader().getFromId();

            ServerHandler.ChannelContainer.getInstance().getActiveChannelByUserId(fromId).getChannel().writeAndFlush(heatMsg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }
}
