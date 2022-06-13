package com.higroup.Buda.api.product.create;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.RandomID.RandomIDGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
@Service
public class ProductCreateService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductCreateService(ProductRepository productRepository, UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
    @Transactional
    public Product createNewProduct(Long userID, Product product)
    {
        if (product.getProductSKU()==null){
            Long count = this.productRepository.findNumberOfProductByUserID(userID);
            product.setProductSKU("PRO-" + count);
        } 

        Product productBySKU = this.productRepository.findProductByUserIDAndProductSKU(userID, product.getProductSKU());
        if (productBySKU!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another product with this SKU has already existed: " + productBySKU.getName());
        }
        if ((product.getPicture() == null) || (product.getPicture().getPictureID() == null)) 
        {
            product.setPicture(null);
        }
        product.setUserID(userID);
        this.productRepository.save(product);
        return product;
    }
}
