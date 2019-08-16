package com.sdt.kid.controller;


import com.sdt.kid.bean.RestResp;
import com.sdt.kid.repo.UserRelationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/userRelation")
public class UserRelationController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private UserRelationRepo userRelationRepo;

    @RequestMapping("/listFriend")
    @GetMapping
    public RestResp getFriendList(String myName) {
        return RestResp.success("ok");
    }


}
