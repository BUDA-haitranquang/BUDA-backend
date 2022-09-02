package com.higroup.Buda.api.product.update.quantity;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.customDTO.QuantityLog;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.LeftLogType;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductUpdateQuantityService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductLeftLogRepository productLeftLogRepository;
    @Autowired
    public ProductUpdateQuantityService(ProductRepository productRepository, UserRepository userRepository, ProductLeftLogRepository productLeftLogRepository)
    {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productLeftLogRepository = productLeftLogRepository;
    }
    @Transactional
    public Product editProductQuantity(Long userID, Long productID, QuantityLog quantityLog)
    {
        Integer amountLeftChange = quantityLog.getAmountLeftChange();
        String message = quantityLog.getMessage();
        LeftLogType leftLogType = quantityLog.getLeftLogType();

        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
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
            if (leftLogType.equals(LeftLogType.REMOVE)){
                productLeftLog.setOtherCost(product.getCostPerUnit() * (- amountLeftChange));
            }
            this.productLeftLogRepository.save(productLeftLog);
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not belong to user");
    }
}
