package com.example.sample.service;

import com.example.sample.VO.VOModelConverter;
import com.example.sample.VO.VirtualMachineListResponseVO;
import com.example.sample.VO.VirtualMachineVO;
import com.example.sample.common.Utility;
import com.example.sample.dao.VirtualMachineRepository;
import com.example.sample.exception.BadRequestException;
import com.example.sample.model.UserDetail;
import com.example.sample.model.VirtualMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VirtualMachineServiceImpl implements VirtualMachineServiceApi{

    @Autowired
    VOModelConverter voModelConverter;

    @Autowired
    VirtualMachineRepository virtualMachineRepository;

    @Autowired
    Utility utility;

    @Override
    public void createVirtualMachine(VirtualMachineVO virtualMachineVO) throws BadRequestException {
        virtualMachineVO.validate();
        UserDetail user = utility.getLoggedInUser();
        VirtualMachine vm = voModelConverter.convertVirtualMachineVOToModel(virtualMachineVO);
        vm.setUser(user);
        virtualMachineRepository.save(vm);
    }

    @Override
    public VirtualMachineListResponseVO listTopNVirtualMachinesByMemoryAcrossUsers(int count) throws BadRequestException {
        VirtualMachineListResponseVO virtualMachineListResponseVO = new VirtualMachineListResponseVO();

        Pageable paging = PageRequest.of(0, count, Sort.by("ram").descending());
        Page<VirtualMachine> result = virtualMachineRepository.findAll(paging);
        if (result.hasContent()) {
            List<VirtualMachine> virtualMachines = virtualMachineRepository.findAll(paging).getContent();
            virtualMachineListResponseVO = voModelConverter.convertVMListToVO(virtualMachines);
        }

        return virtualMachineListResponseVO;
    }

    @Override
    public VirtualMachineListResponseVO listTopNVirtualMachinesByMemory(int count) throws BadRequestException {
        UserDetail loggedInUser = utility.getLoggedInUser();
        Pageable paging = PageRequest.of(0, count, Sort.by("ram").descending());
        List<VirtualMachine> virtualMachines = virtualMachineRepository.findByUser(loggedInUser,paging);
        return voModelConverter.convertVMListToVO(virtualMachines);
    }

    @Override
    public VirtualMachineListResponseVO listVirtualMachines() throws BadRequestException {
        UserDetail loggedInUser = utility.getLoggedInUser();
        List<VirtualMachine> virtualMachines = loggedInUser.getVirtualMachines();
        return voModelConverter.convertVMListToVO(virtualMachines);
    }
}

