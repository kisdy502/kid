package com.sdt.kid.aio;

import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.ApplicationContextProvider;
import com.sdt.kid.bean.AppMessage;
import com.sdt.kid.repo.AppMessageRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Optional;
import java.util.function.Consumer;


/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       ServerReportMessageHandler.java</p>
 * <p>@PackageName:     com.freddy.chat.im.handler</p>
 * <b>
 * <p>@Description:     服务端返回的消息发送状态报告</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/22 19:16</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class ClientReportMessageHandler extends ChannelInboundHandlerAdapter {

    private AppMessageRepo appMessageRepo;

    public ClientReportMessageHandler() {
        appMessageRepo = ApplicationContextProvider.getApplicationContext().getBean(AppMessageRepo.class, "appMessageRepo");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageProtobuf.TransMessage message = (TransMessageProtobuf.TransMessage) msg;
        if (message == null || message.getHeader() == null) {
            return;
        }

        int msgType = message.getHeader().getMsgType();
        if (msgType == 1009) {
            Optional<AppMessage> appMessageOptional = appMessageRepo.findByMessageId(message.getHeader().getMsgId());
            appMessageOptional.ifPresent(new Consumer<AppMessage>() {
                @Override
                public void accept(AppMessage appMessage) {
                    appMessage.setMessageReportStatus(1);
                    appMessageRepo.save(appMessage);
                    System.out.println(",消息已经成功发送至客户端:" + appMessage.getMessageId());
                }
            });
        } else {
            ctx.fireChannelRead(msg);
        }
    }
}
