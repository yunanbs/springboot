package com.spring.yu.entity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by yunan on 2017/3/27.
 */
public interface UserRepository extends JpaRepository<User,Long>
{
    String QUERY_LIKE_NAME="select id,username from users where username like ?1";

    User findById(Long id);

    @Query(value = QUERY_LIKE_NAME,nativeQuery = true)
    List<SubUserInfo> findByUsername(String username);
}
