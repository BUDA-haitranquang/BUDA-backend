package com.higroup.Buda.api.product.group.removeproduct;

import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.ProductRepository;

import com.higroup.Buda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RemoveProductFromGroupService {
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    @Autowired
    public RemoveProductFromGroupService(ProductRepository productRepository, ProductGroupRepository productGroupRepository){
        this.productGroupRepository = productGroupRepository;
        this.productRepository = productRepository;
    }
    @Transactional
    public void removeProductFromProductGroup(Long userID, Long productGroupID, Long productID)
    {
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        if (!product.getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not belong to user");
        }
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if (productGroup.isPresent() && Objects.equals(productGroup.get().getUserID(), userID))
        {
            Set<ProductGroup> productGroups = product.getProductGroups();
            if (productGroups.removeIf(productGroup1 -> productGroup1.getProductGroupID().equals(productGroupID)))
            {
                product.setProductGroups(productGroups);
                this.productRepository.save(product);
                Set<Product> products = productGroup.get().getProducts();
                products.removeIf(product1 -> product1.getProductID().equals(productID));
                productGroup.get().setProducts(products);
                this.productGroupRepository.save(productGroup.get());
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

}
