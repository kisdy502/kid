package com.sdt.kid.service;

import com.sdt.kid.repo.UserRepo;
import com.sdt.kid.repo.UserTokenRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private UserRepo userRepo;

    private UserTokenRepo userTokenRepo;

    public UserService(@Autowired UserRepo userRepo, @Autowired UserTokenRepo userTokenRepo) {
        this.userRepo = userRepo;
        this.userTokenRepo = userTokenRepo;
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }
}
