package com.example.sample.VO;

import com.example.sample.exception.BadRequestException;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TokenVO implements Validatable{

    private String username;
    private String password;

    @Override
    public void validate() throws BadRequestException {
        if (username == null || username.isEmpty()){
            throw new BadRequestException("Please Provide username!");
        }

        if(password == null || password.isEmpty()){
            throw new BadRequestException("Please Provide password!");
        }
    }
}
