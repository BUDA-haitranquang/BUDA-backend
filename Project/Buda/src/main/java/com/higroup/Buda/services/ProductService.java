package com.higroup.Buda.services;

import java.lang.reflect.InvocationTargetException;
import java.time.ZonedDateTime;
import java.util.*;

import com.higroup.Buda.BeanUtils.NullAwareBeanUtilsBean;
import com.higroup.Buda.entities.*;
import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductGroupRepository productGroupRepository;
    private final ProductLeftLogRepository productLeftLogRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, ProductGroupRepository productGroupRepository, UserRepository userRepository, ProductLeftLogRepository productLeftLogRepository)
    {
        this.productGroupRepository = productGroupRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productLeftLogRepository = productLeftLogRepository;
    }
    @Autowired
    private PresentChecker presentChecker;

    public ResponseEntity<?> registerNewProduct(Long userID, Product product)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            return ResponseEntity.badRequest().body("User not found");
        }
        product.setUserID(userID);
        this.productRepository.save(product);
        return ResponseEntity.ok().body(product);
    }
    public List<Product> findAllProductByUserID(Long userID)
    {
        return this.productRepository.findAllProductByUserID(userID);
    }
    public List<Product> findAllHiddenProductByUserID(Long userID)
    {
        return this.productRepository.findAllHiddenProductByUserID(userID);
    }
    public Product hideProductByProductID(Long userID, Long productID)
    {
        Product product = this.productRepository.findProductByProductID(productID);
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
        Product product = this.productRepository.findProductByProductID(productID);
        if ((product!=null) && (Objects.equals(product.getUserID(), userID)))
        {
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
    }
    public void deleteProductByProductID(Long userID, Long productID)
    {
        Product product = this.productRepository.findProductByProductID(productID);
        if ((product!=null) && (Objects.equals(product.getUserID(), userID)))
        {
            if (product.getVisible())
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not delete a visible product");
            }
            else
            {
                this.productRepository.delete(product);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exists");
    }
    public List<Product> findAllProductByProductGroupID(Long userID, Long productGroupID)
    {
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if ((productGroup.isPresent()) && (Objects.equals(productGroup.get().getUserID(), userID)))
        {
            return this.productRepository.findAllProductByProductGroup(productGroup.get());
        }
        return Collections.emptyList();
    }
    public Product editProductQuantity(Long userID, Long productID, Integer amountLeftChange, String message)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Product product = this.productRepository.findProductByProductID(productID);
        if (Objects.equals(product.getUserID(), userID))
        {
            Integer amountLeft = product.getAmountLeft();
            if (amountLeft + amountLeftChange < 0)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough amount for the request");
            }
            product.setAmountLeft(amountLeft + amountLeftChange);
            this.productRepository.save(product);
            ProductLeftLog productLeftLog = new ProductLeftLog();
            productLeftLog.setProduct(product);
            productLeftLog.setAmountLeftChange(amountLeftChange);
            productLeftLog.setUserID(userID);
            productLeftLog.setMessage(message);
            productLeftLog.setCreationTime(ZonedDateTime.now());
            this.productLeftLogRepository.save(productLeftLog);
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }
    public List<Ingredient> findAlertAmountProduct(Long userID){
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        return this.productRepository.findAlertAmountProduct();
    }
    public Product editProduct(Long userID, Long productID, Product product) throws InvocationTargetException, IllegalAccessException {

        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Product dest_product = this.productRepository.findProductByProductID(productID);
        if (Objects.equals(dest_product.getUserID(), userID))
        {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(dest_product, product);
            return dest_product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");

    }
}
