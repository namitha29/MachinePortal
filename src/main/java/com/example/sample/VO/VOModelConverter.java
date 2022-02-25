package com.example.sample.VO;

import com.example.sample.model.UserDetail;
import com.example.sample.model.VirtualMachine;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VOModelConverter {

    public UserDetail convertUserVOToModel(UserVO userVO) {
        UserDetail user = new UserDetail();
        user.setUserName(userVO.getName());
        user.setPassword(userVO.getPassword());
        user.setEmailId(userVO.getEmail());
        user.setRole(userVO.getRole());
        user.setPhoneNumber(userVO.getPhoneNumber());
        return user;
    }

    public VirtualMachine convertVirtualMachineVOToModel(VirtualMachineVO virtualMachineVO) {
        VirtualMachine virtualMachine = new VirtualMachine();
        virtualMachine.setHardDisk(virtualMachineVO.getHardDisk());
        virtualMachine.setCpuCores(virtualMachineVO.getCpuCores());
        virtualMachine.setOs(virtualMachineVO.getOs());
        virtualMachine.setRam(virtualMachineVO.getRam());
        virtualMachine.setVmName(virtualMachineVO.getName());
        return virtualMachine;
    }

    public VirtualMachineVO convertVirtualMachineModelToVO(VirtualMachine vm){
        VirtualMachineVO virtualMachineVO= new VirtualMachineVO();
        virtualMachineVO.setCpuCores(vm.getCpuCores());
        virtualMachineVO.setOs(vm.getOs());
        virtualMachineVO.setRam(vm.getRam());
        virtualMachineVO.setHardDisk(vm.getHardDisk());
        virtualMachineVO.setName(vm.getVmName());
        virtualMachineVO.setOwner(vm.getUser().getUserName());
        return virtualMachineVO;
    }

    public VirtualMachineListResponseVO convertVMListToVO(List<VirtualMachine> virtualMachines) {
        List<VirtualMachineVO> virtualMachineVOList = new ArrayList<>();
        VirtualMachineListResponseVO virtualMachineListResponseVO = new VirtualMachineListResponseVO();
        virtualMachines.forEach(vm -> {
            VirtualMachineVO vmVO = convertVirtualMachineModelToVO(vm);
            virtualMachineVOList.add(vmVO);
        });
        virtualMachineListResponseVO.setMachines(virtualMachineVOList);
        return virtualMachineListResponseVO;
    }

    public List<UserVO> convertUserModelListToVO(List<UserDetail> users){
        List<UserVO> userVOList = new ArrayList<>();
        users.forEach(user ->{
            UserVO userVO = new UserVO();
            userVO.setName(user.getUserName());
            userVO.setId(user.getUserId());
            userVOList.add(userVO);
        });
        return userVOList;
    }
}
