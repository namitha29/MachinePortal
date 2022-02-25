package com.example.sample.rest;

import com.example.sample.VO.TokenVO;
import com.example.sample.VO.UserVO;
import com.example.sample.service.UserServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServiceApi userServiceApi;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserVO userVO) {
        logger.info("New User signing up");
        userServiceApi.registerUser(userVO);
        return new ResponseEntity<>("User created successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody TokenVO tokenVO){
        logger.info("Signing In and getting token");
        String token = userServiceApi.getToken(tokenVO);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('MASTER')")
    public ResponseEntity<List<UserVO>> listUsers(){
        logger.info("Getting list of Users");
        List<UserVO> userList = userServiceApi.listUsers();
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('MASTER')")
    public ResponseEntity<String> deleteUser(@PathVariable long userId) {
        logger.info("Deleting user");
        userServiceApi.deleteUser(userId);
        return new ResponseEntity<>("User Deleted Successfully!", HttpStatus.OK);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value= HttpStatus.CONFLICT)
    public String conflict(Exception ex) {
        logger.error("Data integrity Exception! " + ex);
        return "Username, email and phoneNumber has to be unique!";
    }

}
