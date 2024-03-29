package com.higroup.Buda.api.product.packaging.component.view;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductComponent;
import com.higroup.Buda.repositories.ProductComponentRepository;
import com.higroup.Buda.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ViewProductComponentService {
    private final ProductComponentRepository productComponentRepository;
    private final ProductRepository productRepository;
    @Autowired
    public ViewProductComponentService(ProductComponentRepository productComponentRepository,
    ProductRepository productRepository){
        this.productRepository = productRepository;
        this.productComponentRepository = productComponentRepository;
    }
    public List<ProductComponent> findAllProductComponentByProductID(Long userID, Long productID){
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        if ((product != null) && (product.getUserID().equals(userID))){
            return this.productComponentRepository.findAllByProductID(productID);
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
    }
}
