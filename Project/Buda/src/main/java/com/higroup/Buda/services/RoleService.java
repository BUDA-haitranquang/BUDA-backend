package com.higroup.Buda.services;

import java.util.Optional;

import com.higroup.Buda.entities.Role;
import com.higroup.Buda.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    
    @Autowired 
    private RoleRepository roleRepository;
    
    // add new role
    public ResponseEntity<?> addNewRole(String name){
        if(roleRepository.findRoleByName(name).isPresent()){
            return ResponseEntity.badRequest().body("Role already exits");
        }
        Role role = new Role(name);
        roleRepository.save(role);
        
        return ResponseEntity.ok().body(role.toString());

    }

    // find role by id
    public ResponseEntity<?> findRoleByRoleID(Long RoleID){
        Optional<Role> role = roleRepository.findRoleByRoleID(RoleID);
        if(!role.isPresent()){
            return ResponseEntity.badRequest().body("Cannot find role by roleID: " + String.valueOf(RoleID));
        }
        return ResponseEntity.ok().body(role.get().toString());
    }

    // find role by name
    public ResponseEntity<?> findRoleByName(String name){
        Optional<Role> role = roleRepository.findRoleByName(name);

        if(!role.isPresent()){
            return ResponseEntity.badRequest().body("Cannot find role by name: " + name);
        }
        return ResponseEntity.ok().body(role.get().toString());
    }
}
