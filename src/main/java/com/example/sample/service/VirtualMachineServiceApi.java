package com.example.sample.service;

import com.example.sample.VO.VirtualMachineListResponseVO;
import com.example.sample.VO.VirtualMachineVO;
import com.example.sample.exception.BadRequestException;

public interface VirtualMachineServiceApi {

    void createVirtualMachine(VirtualMachineVO virtualMachineVO) throws BadRequestException;

    VirtualMachineListResponseVO listTopNVirtualMachinesByMemoryAcrossUsers(int count) throws BadRequestException;

    VirtualMachineListResponseVO listTopNVirtualMachinesByMemory(int n) throws BadRequestException;

    VirtualMachineListResponseVO listVirtualMachines() throws BadRequestException;
}
