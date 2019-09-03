package com.sdt.kid.aio;

import com.sdt.im.protobuf.TransMessageProtobuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeatResponseHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public HeatResponseHandler() {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage heatMsg = (TransMessageProtobuf.TransMessage) msg;
        if (heatMsg == null || heatMsg.getHeader() == null) {
            return;
        }
        int msgType = heatMsg.getHeader().getMsgType();
        if (msgType == MessageType.HEARTBEAT.getMsgType()) {
            logger.debug("客户端心跳消息：" + heatMsg);
            String fromId = heatMsg.getHeader().getFromId();
            MessageHelper.forwardMessage(fromId, heatMsg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }
}
