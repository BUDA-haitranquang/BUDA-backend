package com.higroup.Buda.services;

import com.higroup.Buda.repositories.RoleRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    
    @Autowired RoleRepository roleRepository;
    
}
