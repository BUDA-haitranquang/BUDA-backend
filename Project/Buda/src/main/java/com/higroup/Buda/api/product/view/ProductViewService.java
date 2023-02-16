package com.higroup.Buda.api.product.view;


import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.ProductRepository.ViewProductInfo;

@Service
public class ProductViewService {
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    @Autowired
    public ProductViewService(ProductRepository productRepository, ProductGroupRepository productGroupRepository)
    {
        this.productGroupRepository = productGroupRepository;
        this.productRepository = productRepository;
    }

    public List<Product> findAllProductByUserID(Long userID)
    {
        return this.productRepository.findAllProductByUserID(userID);
    }
    public ProductViewDTO findAllFilterProductByUserID(Long userID, ViewProductInfo viewProductInfo, Pageable pageable)
    {
        Page<ViewProductInfo> products = this.productRepository.findAllFilterProductByUserID(
        userID,
        viewProductInfo.getProductSKU(), 
        viewProductInfo.getName(),
        viewProductInfo.getSellingPrice(),
        viewProductInfo.getAmountLeft(),
        viewProductInfo.getAlertAmount(),
        viewProductInfo.getCostPerUnit(),
        viewProductInfo.getDescription(), 
        pageable);
        return new ProductViewDTO(products.getTotalElements(), products.toList());
    }
    public List<Product> findAllHiddenProductByUserID(Long userID)
    {
        return this.productRepository.findAllHiddenProductByUserID(userID);
    }
    @Transactional
    public Product hideProductByProductID(Long userID, Long productID)
    {
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(opProduct.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        if (Objects.equals(product.getUserID(), userID)) {
            product.setVisible(false);
            this.productRepository.save(product);
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
    }
    public Product findProductByProductID(Long userID, Long productID)
    {
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(opProduct.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        if (product.getUserID().equals(userID)) {
            return product;
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
    }
    public Set<Product> findAllProductByProductGroupID(Long userID, Long productGroupID)
    {
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if ((productGroup.isPresent()) && (Objects.equals(productGroup.get().getUserID(), userID)))
        {
            return productGroup.get().getProducts();
        }
        return Collections.emptySet();
    }

    public List<Product> findAlertAmountProduct(Long userID){
        return this.productRepository.findAlertAmountProduct(userID);
    }


}

