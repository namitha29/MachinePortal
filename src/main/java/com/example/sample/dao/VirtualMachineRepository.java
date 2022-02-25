package com.example.sample.dao;

import com.example.sample.model.UserDetail;
import com.example.sample.model.VirtualMachine;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VirtualMachineRepository extends JpaRepository<VirtualMachine,Long> {

    List<VirtualMachine> findByUser(UserDetail user, Pageable pageable);
}
