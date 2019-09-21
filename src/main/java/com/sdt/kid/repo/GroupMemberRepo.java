package com.sdt.kid.repo;

import com.sdt.kid.bean.GroupMember;
import com.sdt.kid.bean.UserGroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Luo_xuri on 2017/9/29.
 */
@Repository
public interface GroupMemberRepo extends CrudRepository<GroupMember, Long> {
    Iterable<GroupMember> findByUserGroup(UserGroup userGroup);
}
