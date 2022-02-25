package com.example.sample.common;

import com.example.sample.dao.UserRepository;
import com.example.sample.exception.BadRequestException;
import com.example.sample.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class Utility {

    @Autowired
    UserRepository userRepository;

    public UserDetail getLoggedInUser() throws BadRequestException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetail user = userRepository.findUserByUserName(((UserDetails) principal).getUsername());
                return user;
            }
        }
        throw new BadRequestException("Failed to get logged in user!");
    }
}
