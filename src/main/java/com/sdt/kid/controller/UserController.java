package com.sdt.kid.controller;


import com.sdt.im.protobuf.TransMessageProtobuf;
import com.sdt.kid.aio.MessageType;
import com.sdt.kid.aio.ServerHandler;
import com.sdt.kid.bean.*;
import com.sdt.kid.repo.AppMessageRepo;
import com.sdt.kid.repo.UserRepo;
import com.sdt.kid.repo.UserTokenRepo;
import com.sdt.kid.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserTokenRepo userTokenRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AppMessageRepo appMessageRepo;

    @RequestMapping("/register")
    @PostMapping
    public RestResp register(HttpServletRequest request, @RequestBody User user) {
        logger.info("RequestBody {}", user);
        return userRepo.findByName(user.getName()).map(new Function<User, RestResp>() {
            @Override
            public RestResp apply(User user) {
                return RestResp.fail("用户已存在:" + user.getName());
            }
        }).orElseGet(new Supplier<RestResp>() {
            @Override
            public RestResp get() {
                User u = userRepo.save(user);
                logger.info("{} registered", u);
                return RestResp.success(u);
            }
        });
    }

    @RequestMapping("/login")
    @GetMapping
    public RestResp login(NameAndPass user) {
        return userRepo.findByNameAndPassword(user.getUsername(), user.getPassword()).map(new Function<User, RestResp>() {
            @Override
            public RestResp apply(User user) {
                String token = jwtService.generate(user);
                Optional<UserToken> userTokenOptional = userTokenRepo.findByToken(token);
                if (userTokenOptional.isPresent()) {
                } else {
                    userTokenRepo.save(new UserToken(token, new Date()));
                }
                user.setToken(token);
                return RestResp.success("login success", user);
            }
        }).orElse(RestResp.fail("登录失败，用户名或密码错误"));
    }

    @RequestMapping("/search")
    @GetMapping
    public RestResp search(HttpServletRequest request, String keyword) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return RestResp.fail("no token");
        }
        return userRepo.findByName(keyword).map(new Function<User, RestResp>() {
            @Override
            public RestResp apply(User user) {
                user.setPassword("");
                return RestResp.success("搜索成功", user);
            }
        }).orElse(RestResp.fail("未搜索到匹配的用户"));
    }


    @GetMapping("/push")
    public RestResp push(String message, long endDate) throws UnsupportedEncodingException {
        logger.info("push message {}", message);
        TransMessageProtobuf.TransMessage.Builder msgBuilder = TransMessageProtobuf.TransMessage.newBuilder();

        Iterator<User> userIterator = userRepo.findAll().iterator();
        String decodeMessage = URLDecoder.decode(message, "UTF-8");
        logger.info("decodeMessage {}", decodeMessage);
        while (userIterator.hasNext()) {
            ClientUser clientUser = new ClientUser();
            clientUser.setId(userIterator.next().getId());
            clientUser.setUserName(userIterator.next().getName());
            ServerHandler.NettyChannel nettyChannel = ServerHandler.ChannelContainer.getInstance()
                    .getActiveChannelByUserId(clientUser.getId());
            clientUser.setOnlined(nettyChannel != null);
            clientUser.setNettyChannel(nettyChannel);

            msgBuilder.setMsgId(UUID.randomUUID().toString());
            msgBuilder.setFromId(0);
            msgBuilder.setToId(clientUser.getId());
            msgBuilder.setMsgType(1000);
            msgBuilder.setSendTime(System.currentTimeMillis());
            msgBuilder.setStatusReport(0);
            msgBuilder.setContent(decodeMessage);

            AppMessage appMessage = new AppMessage();
            appMessage.setFromId(0L);
            appMessage.setSendTime(msgBuilder.getSendTime());
            appMessage.setToId(clientUser.getId());
            appMessage.setMessageId(msgBuilder.getMsgId());
            appMessage.setEndTime(endDate);
            appMessage.setMessageType(MessageType.SYSTEMMESSAGE.getMsgType());
            appMessage.setStatusReport(0);
            appMessage.setMessageContentType(0);
            appMessage.setContent(decodeMessage);
            appMessageRepo.save(appMessage);
            if (clientUser.isOnlined()) {
                clientUser.getNettyChannel().getChannel().writeAndFlush(msgBuilder.build());
            } else {
                //do nothing
            }
        }

        return RestResp.success("push success");
    }

}
