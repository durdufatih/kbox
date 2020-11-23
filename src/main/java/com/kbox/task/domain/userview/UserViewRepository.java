package com.kbox.task.domain.userview;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface UserViewRepository extends JpaRepository<UserViewEntity, Integer> {

    Page<UserViewEntity> findAllByActiveUserAndShowStatusIsTrueAndViewTimeAfter(String activeUser, Date limitDate, Pageable pageable);
}
