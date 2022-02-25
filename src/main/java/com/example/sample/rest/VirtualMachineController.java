package com.example.sample.rest;


import com.example.sample.VO.VirtualMachineListResponseVO;
import com.example.sample.VO.VirtualMachineVO;
import com.example.sample.service.VirtualMachineServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/virtualmachine")
public class VirtualMachineController{

    Logger logger = LoggerFactory.getLogger(VirtualMachineController.class);

    @Autowired
    VirtualMachineServiceApi virtualMachineServiceApi;

    @PostMapping("/provision")
    public ResponseEntity<String> createMachine(@RequestBody VirtualMachineVO virtualMachineVO) {
        logger.info("Creating new Virtual Machine");
        virtualMachineServiceApi.createVirtualMachine(virtualMachineVO);
        return new ResponseEntity<>("virtualMachine created successfully!", HttpStatus.CREATED);

    }

    @GetMapping("")
    public ResponseEntity<VirtualMachineListResponseVO> listVirtualMachines() {
        logger.info("Getting list of virtual machine for a user");
        VirtualMachineListResponseVO virtualMachineListResponseVO = virtualMachineServiceApi.listVirtualMachines();
        return new ResponseEntity<>(virtualMachineListResponseVO, HttpStatus.OK);
    }

    @GetMapping("/master/filter")
    @PreAuthorize("hasAuthority('MASTER')")
    public ResponseEntity<VirtualMachineListResponseVO> listTopNVirtualMachinesByMemoryAcrossUsers(@RequestParam("topMemoryCount") int count) {
        logger.info("Getting list of Top N virtual machine details ordered by memory across users");
        VirtualMachineListResponseVO virtualMachineListResponseVO = virtualMachineServiceApi.listTopNVirtualMachinesByMemoryAcrossUsers(count);
        return new ResponseEntity<>(virtualMachineListResponseVO, HttpStatus.OK);
    }

    @GetMapping("filter")
    public ResponseEntity<VirtualMachineListResponseVO> listTopNVirtualMachinesByMemory(@RequestParam("topMemoryCount") int count) {
        logger.info("Getting list of top n virtual machine ordered by memory for logged in user");
        VirtualMachineListResponseVO virtualMachineListResponseVO = virtualMachineServiceApi.listTopNVirtualMachinesByMemory(count);
        return new ResponseEntity<>(virtualMachineListResponseVO, HttpStatus.OK);

    }

}
