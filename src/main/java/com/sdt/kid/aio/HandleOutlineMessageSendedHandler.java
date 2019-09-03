package com.sdt.kid.aio;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.AppMessage;
import com.sdt.kid.repo.AppMessageRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Consumer;

public class HandleOutlineMessageSendedHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AppMessageRepo appMessageRepo;

    public HandleOutlineMessageSendedHandler() {
        appMessageRepo = ApplicationContextProvider.getApplicationContext().getBean(AppMessageRepo.class, "appMessageRepo");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage message = (TransMessageProtobuf.TransMessage) msg;
        if (message == null || message.getHeader() == null) {
            return;
        }

        int msgType = message.getHeader().getMsgType();
        if (msgType == MessageType.REPORT_RECEIVED_OUTLINE_MESSAGE_LIST.getMsgType()) {
            logger.debug("离线消息已被客户端接收：" + message);
            if (message.getBody() != null) {
                message.getBody();
                JSONObject jsonObj = JSON.parseObject(message.getBody());
                String received_messageId_list = jsonObj.getString("received_messageId_list");
                String[] idArray = received_messageId_list.split(",");
                for (String msgId : idArray) {
                    Optional<AppMessage> optional = appMessageRepo.findByMessageId(msgId);
                    optional.ifPresent(new Consumer<AppMessage>() {
                        @Override
                        public void accept(AppMessage appMessage) {
                            appMessage.setStatusReport(1);
                            appMessageRepo.save(appMessage);
                        }
                    });
                }
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }


}