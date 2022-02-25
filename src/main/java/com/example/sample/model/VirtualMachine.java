package com.example.sample.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class VirtualMachine {

    private Long vmId;
    private String os;
    private int ram;
    private int hardDisk;
    private int cpuCores;
    private String vmName;

    private UserDetail user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getVmId() {
        return vmId;
    }

    public void setVmId(Long vmId) {
        this.vmId = vmId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(int hardDisk) {
        this.hardDisk = hardDisk;
    }

    public int getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(int cpuCores) {
        this.cpuCores = cpuCores;
    }

    public String getVmName() {
        return vmName;
    }

    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    @ManyToOne
    public UserDetail getUser() {
        return user;
    }

    public void setUser(UserDetail user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        return getVmId() != null ? getVmId().hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || !(o instanceof VirtualMachine))
            return false;

        VirtualMachine that = (VirtualMachine) o;

        if (getVmId() != null ? !getVmId().equals(that.getVmId()) : that.getVmId() != null)
            return false;

        return true;
    }
}
