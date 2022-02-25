package com.example.sample.service;

import com.example.sample.VO.TokenVO;
import com.example.sample.VO.UserVO;
import com.example.sample.exception.BadRequestException;
import com.example.sample.exception.ResourceNotFoundException;

import java.util.List;

public interface UserServiceApi {

    void registerUser(UserVO userVO) throws BadRequestException;

    String getToken(TokenVO tokenVO) throws BadRequestException;

    void deleteUser(long userId) throws BadRequestException, ResourceNotFoundException;

    List<UserVO> listUsers();
}
