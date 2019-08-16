package com.sdt.kid.repo;

import com.sdt.kid.bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Luo_xuri on 2017/9/29.
 */
@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> findByName(String userName);
    Optional<User> findByNameAndPassword(String userName, String password);
}
