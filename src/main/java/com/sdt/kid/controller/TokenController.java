package com.sdt.kid.controller;


import com.sdt.kid.bean.RestResp;
import com.sdt.kid.repo.UserRepo;
import com.sdt.kid.repo.UserTokenRepo;
import com.sdt.kid.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/token")
public class TokenController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserTokenRepo userTokenRepo;

    @Autowired
    private JwtService jwtService;


    @RequestMapping("/login")
    @GetMapping
    public RestResp login(@RequestParam(value = "token", required = false) String token) {
        logger.info("token:" + token);
        return RestResp.success("aaa", token);
    }

}
