package com.sdt.kid.repo;

import com.sdt.kid.bean.AppMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Luo_xuri on 2017/9/29.
 */
@Repository
public interface AppMessageRepo extends CrudRepository<AppMessage, Long> {

    Optional<List<AppMessage>> findByFromId(String fromId);

    Optional<List<AppMessage>> findByToId(String toId);

    Optional<AppMessage> findByMessageId(String messageId);

    Optional<List<AppMessage>> findByToIdEqualsAndStatusReportEqualsAndEndTimeGreaterThan(String fromId, int reportStatus, long endTime);

    @Modifying
    @Transactional
    int deleteByMessageId(String messageId);
}
