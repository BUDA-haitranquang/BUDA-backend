package com.higroup.Buda.api.product.view;


import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.repositories.ProductRepository.ViewProductInfo;
import com.higroup.Buda.util.Checker.PresentChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ProductViewService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductGroupRepository productGroupRepository;
    @Autowired
    public ProductViewService(ProductRepository productRepository, ProductGroupRepository productGroupRepository, UserRepository userRepository)
    {
        this.productGroupRepository = productGroupRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
    @Autowired
    private PresentChecker presentChecker;

    public List<ViewProductInfo> findAllProductByUserID(Long userID)
    {
        return this.productRepository.findAllProductByUserID(userID);
    }
    public List<Product> findAllHiddenProductByUserID(Long userID)
    {
        return this.productRepository.findAllHiddenProductByUserID(userID);
    }
    @Transactional
    public Product hideProductByProductID(Long userID, Long productID)
    {
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        if ((product!=null) && (Objects.equals(product.getUserID(), userID)))
        {
            product.setVisible(false);
            this.productRepository.save(product);
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
    }
    public Product findProductByProductID(Long userID, Long productID)
    {
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        if ((product!=null) && product.getUserID().equals(userID))
        {
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

