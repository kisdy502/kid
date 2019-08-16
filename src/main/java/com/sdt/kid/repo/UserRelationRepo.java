package com.sdt.kid.repo;

import com.sdt.kid.bean.UserRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Luo_xuri on 2017/9/29.
 */
@Repository
public interface UserRelationRepo extends CrudRepository<UserRelation, Long> {

    Optional<List<UserRelation>> findByMyName(String myName);
}
