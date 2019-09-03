package com.sdt.kid.aio;

import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.AppMessage;
import com.sdt.kid.repo.AppMessageRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class HandleOutlineMessageListHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AppMessageRepo appMessageRepo;

    public HandleOutlineMessageListHandler() {
        appMessageRepo = ApplicationContextProvider.getApplicationContext().getBean(AppMessageRepo.class, "appMessageRepo");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage message = (TransMessageProtobuf.TransMessage) msg;
        if (message == null || message.getHeader() == null) {
            return;
        }

        int msgType = message.getHeader().getMsgType();
        if (msgType == MessageType.GET_OUTLINE_MESSAGE_LIST.getMsgType()) {
            logger.debug("请求离线消息List：" + message);
            if (message.getHeader().getFromId() != null) {
                Optional<List<AppMessage>> optional = appMessageRepo.findByToIdEqualsAndStatusReportEqualsAndEndTimeGreaterThan(
                        message.getHeader().getFromId(), 0, System.currentTimeMillis());
                optional.ifPresent(new Consumer<List<AppMessage>>() {
                    @Override
                    public void accept(List<AppMessage> appMessageList) {
                        ctx.channel().writeAndFlush(MessageHelper.getOutLineList(appMessageList));
                    }
                });
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }
}