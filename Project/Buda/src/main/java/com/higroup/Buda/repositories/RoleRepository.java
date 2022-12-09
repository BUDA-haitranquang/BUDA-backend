package com.higroup.Buda.repositories;
import java.util.Optional;

import com.higroup.Buda.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findRoleByRoleID(@Param("roleID") Long RoleID);
    Optional<Role> findRoleByName(@Param("name") String name); 
}
