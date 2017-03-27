package com.spring.yu.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yunan on 2017/3/27.
 */
public interface UserRepository extends JpaRepository<User,Long>
{
    User findById(Long id);
}
