package com.sdt.kid.repo;

import com.sdt.kid.bean.UserToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepo extends CrudRepository<UserToken, String> {
    Optional<UserToken> findByToken(String token);
}
