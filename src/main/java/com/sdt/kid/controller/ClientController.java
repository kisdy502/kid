package com.sdt.kid.controller;

import com.sdt.kid.aio.ServerHandler;
import com.sdt.kid.bean.ClientUser;
import com.sdt.kid.bean.User;
import com.sdt.kid.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/client")
public class ClientController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/list")
    @GetMapping
    public String clientList(HttpServletRequest request) {

        List<ClientUser> clientUserList = new ArrayList<>();

        Iterator<User> userIterator = userRepo.findAll().iterator();
        while (userIterator.hasNext()) {
            ClientUser clientUser = new ClientUser();
            clientUser.setId(userIterator.next().getId());
            clientUser.setUserName(userIterator.next().getName());
            ServerHandler.NettyChannel nettyChannel = ServerHandler.ChannelContainer.getInstance()
                    .getActiveChannelByUserId(clientUser.getId());
            clientUser.setOnlined(nettyChannel != null);
            clientUser.setNettyChannel(nettyChannel);
            clientUserList.add(clientUser);
        }

        request.setAttribute("clientUserList", clientUserList);
        return "clientList";
    }
}
