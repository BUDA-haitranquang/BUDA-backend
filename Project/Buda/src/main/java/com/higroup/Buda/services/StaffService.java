package com.higroup.Buda.services;

import com.higroup.Buda.repositories.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    private StaffRepository staffRepository;
    @Autowired
    public StaffService(StaffRepository staffRepository)
    {
        this.staffRepository = staffRepository;
    }
}
