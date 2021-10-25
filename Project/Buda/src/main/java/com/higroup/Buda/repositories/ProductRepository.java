package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductGroup;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Product findProductByProductID(Long productID);
    List<Product> findAllProductByUserID(Long userID);
    List<Product> findAllProductByProductGroup(ProductGroup productGroup);
}
