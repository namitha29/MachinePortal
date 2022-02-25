package com.example.sample.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class VirtualMachineListResponseVO {
    private String errMsg;
    private List<VirtualMachineVO> machines;
}
