package com.sdt.kid.aio;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    public ServerHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("ServerHandler channelActive()" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("ServerHandler channelInactive()");
        // 用户断开连接后，移除channel
        ChannelContainer.getInstance().removeChannelIfConnectNoActive(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("ServerHandler exceptionCaught()");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        System.out.println("ServerHandler userEventTriggered()");
    }

    public static class ChannelContainer {

        private ChannelContainer() {

        }

        private static final ChannelContainer INSTANCE = new ChannelContainer();

        public static ChannelContainer getInstance() {
            return INSTANCE;
        }

        private final Map<String, NettyChannel> CHANNELS = new ConcurrentHashMap<>();

        public void saveChannel(NettyChannel channel) {
            if (channel == null) {
                return;
            }
            CHANNELS.put(channel.getChannelId(), channel);
        }

        public NettyChannel removeChannelIfConnectNoActive(Channel channel) {
            if (channel == null) {
                return null;
            }

            String channelId = channel.id().toString();

            return removeChannelIfConnectNoActive(channelId);
        }

        public NettyChannel removeChannelIfConnectNoActive(String channelId) {
            if (CHANNELS.containsKey(channelId) && !CHANNELS.get(channelId).isActive()) {
                return CHANNELS.remove(channelId);
            }

            return null;
        }

        public Long getUserIdByChannel(Channel channel) {
            return getUserIdByChannel(channel.id().toString());
        }

        public Long getUserIdByChannel(String channelId) {
            if (CHANNELS.containsKey(channelId)) {
                return CHANNELS.get(channelId).getUserId();
            }

            return null;
        }

        public NettyChannel getActiveChannelByUserId(Long userId) {
            for (Map.Entry<String, NettyChannel> entry : CHANNELS.entrySet()) {
                if (entry.getValue().getUserId().equals(userId) && entry.getValue().isActive()) {
                    return entry.getValue();
                }
            }
            return null;
        }

        public Map<String, NettyChannel> getChannels() {
            return CHANNELS;
        }
    }

    public static class NettyChannel {

        private Long userId;
        private Channel channel;
        private String address;

        public NettyChannel(Long userId, Channel channel) {
            this.userId = userId;
            this.channel = channel;
            address = channel.remoteAddress().toString();
        }

        public String getChannelId() {
            return channel.id().toString();
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Channel getChannel() {
            return channel;
        }

        public void setChannel(Channel channel) {
            this.channel = channel;
        }

        public boolean isActive() {
            return channel.isActive();
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
