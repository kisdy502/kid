package com.sdt.kid.controller;


import com.sdt.kid.bean.RestResp;
import com.sdt.kid.bean.UserToken;
import com.sdt.kid.repo.UserRepo;
import com.sdt.kid.repo.UserTokenRepo;
import com.sdt.kid.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.Function;

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
    public RestResp login(@RequestParam(value = "token", required = true) String token) {
        logger.info("token:" + token);
        Optional<UserToken> userTokenOptional = userTokenRepo.findByToken(token);
        userTokenOptional.map(new Function<UserToken, RestResp>() {
            @Override
            public RestResp apply(UserToken userToken) {
                return RestResp.success("token验证通过,登录成功!");
            }
        }).orElse(RestResp.fail("token不正确"));
        return RestResp.success("aaa", token);
    }

}
