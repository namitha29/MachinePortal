package com.example.sample.VO;


import com.example.sample.exception.BadRequestException;
import com.example.sample.model.RoleEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter @Getter
public class UserVO implements Validatable {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private RoleEnum role;
    private long id;

    @Override
    public void validate() throws BadRequestException {

        if(name == null || name.isEmpty()){
            throw new BadRequestException("Please specify username!");
        }

        if(phoneNumber == null || phoneNumber.isEmpty()){
            throw new BadRequestException("Please specify Phone number!");
        }

        if(email == null || email.isEmpty()){
            throw new BadRequestException("Please specify EmailID!");
        }
    }

}
