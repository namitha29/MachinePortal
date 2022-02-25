package com.example.sample.service;

import com.example.sample.VO.TokenVO;
import com.example.sample.VO.UserVO;
import com.example.sample.VO.VOModelConverter;
import com.example.sample.dao.UserRepository;
import com.example.sample.exception.BadRequestException;
import com.example.sample.exception.ResourceNotFoundException;
import com.example.sample.model.UserDetail;
import com.example.sample.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserServiceApi{

    @Autowired
    UserRepository userRepository;

    @Autowired
    VOModelConverter voModelConverter;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void registerUser(UserVO userVO) throws BadRequestException {
        userVO.validate();
        String encodedPassword = encodePassword(userVO.getPassword());
        userVO.setPassword(encodedPassword);
        UserDetail user = voModelConverter.convertUserVOToModel(userVO);
        userRepository.save(user);
    }

    private String encodePassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(10, new SecureRandom());
        return bCryptPasswordEncoder.encode(password);
    }

    private UserDetail getUserById(Long userId) throws BadRequestException, ResourceNotFoundException {
        if (userId == null) {
            throw new BadRequestException("Please provide valid user Id");
        }

        Optional<UserDetail> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new ResourceNotFoundException("User not found!");
        }
        return user.get();
    }

    @Override
    public void deleteUser(long userId) throws BadRequestException, ResourceNotFoundException {
        UserDetail user = getUserById(userId);
        userRepository.delete(user);
        logger.info("User deleted successfully!");
    }

    @Override
    public List<UserVO> listUsers() {
        List<UserDetail> users = userRepository.findAll();
        return voModelConverter.convertUserModelListToVO(users);
    }

    @Override
    public String getToken(TokenVO tokenVO) throws BadRequestException {
        tokenVO.validate();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(tokenVO.getUsername(), tokenVO.getPassword()));
        }catch (Exception e){
            throw new BadRequestException("Authentication Failed!");
        }
        return jwtUtil.generateToken(tokenVO.getUsername());
    }

}
