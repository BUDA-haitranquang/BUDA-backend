package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long>{
    Optional<ProductGroup> findProductGroupByProductGroupID(Long productGroupID);
    List<ProductGroup> findAllByUserID(Long userID);

}
