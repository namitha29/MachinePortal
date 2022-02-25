package com.example.sample.VO;

import com.example.sample.exception.BadRequestException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter @Setter
public class VirtualMachineVO implements Validatable {

    private String os;
    private Integer ram;
    private Integer hardDisk;
    private Integer cpuCores;
    private String name;
    private String owner;


    @Override
    public void validate() throws BadRequestException {
       //Can add any validation if required for request object
    }
}
