package com.higroup.Buda.services;

import java.lang.reflect.InvocationTargetException;
import java.time.ZonedDateTime;
import java.util.*;

import javax.transaction.Transactional;

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
    @Transactional
    public Product registerNewProduct(Long userID, Product product)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        product.setUserID(userID);
        this.productRepository.save(product);
        return product;
    }
    public List<Product> findAllProductByUserID(Long userID)
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
    public Set<Product> findAllProductByProductGroupID(Long userID, Long productGroupID)
    {
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if ((productGroup.isPresent()) && (Objects.equals(productGroup.get().getUserID(), userID)))
        {
            return productGroup.get().getProducts();
        }
        return Collections.emptySet();
    }
    @Transactional
    public Product updateProductbyProductID(Product product, Product newproduct){
        if(newproduct == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not exists");
        }
        // check which features need update
        if(newproduct.getName() != null){
            product.setName(newproduct.getName());
        }
        if(newproduct.getAmountLeft() != null){
            product.setAmountLeft(newproduct.getAmountLeft()); 
        }
        if(newproduct.getDescription() != null){
            product.setDescription(newproduct.getDescription());
        }
        if(newproduct.getSellingPrice() != null){
            product.setSellingPrice(newproduct.getSellingPrice());
        }
        if(newproduct.getVisible() != null){
            product.setVisible(newproduct.getVisible());
        }
        if(newproduct.getPictureID() != null){
            product.setPictureID(newproduct.getPictureID());
        }
        if(newproduct.getAlertAmount() != null){
            product.setAlertAmount(newproduct.getAlertAmount());
        }
        if(newproduct.getCostPerUnit() != null){
            product.setCostPerUnit(newproduct.getCostPerUnit());
        }

        productRepository.save(product);
        return product;
    }
    @Transactional
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
    public List<Product> findAlertAmountProduct(Long userID){
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        return this.productRepository.findAlertAmountProduct(userID);
    }
    @Transactional
    public Product editProduct(Long userID, Long productID, Product product) throws InvocationTargetException, IllegalAccessException {

        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Product dest_product = this.productRepository.findProductByProductID(productID);
        if (dest_product.getUserID().equals(userID))
        {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(dest_product, product);
            this.productRepository.save(dest_product);
            return dest_product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");

    }
}
