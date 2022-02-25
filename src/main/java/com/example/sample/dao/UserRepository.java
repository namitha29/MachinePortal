package com.example.sample.dao;

import com.example.sample.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetail, Long> {

    UserDetail findUserByUserName(String userName);

}
