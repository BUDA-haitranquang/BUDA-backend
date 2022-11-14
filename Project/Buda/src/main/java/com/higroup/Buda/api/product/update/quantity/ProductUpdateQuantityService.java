package com.higroup.Buda.api.product.update.quantity;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductUpdateQuantityService {
    private final ProductRepository productRepository;
    private final ProductLeftLogRepository productLeftLogRepository;

    @Autowired
    public ProductUpdateQuantityService(ProductRepository productRepository, ProductLeftLogRepository productLeftLogRepository)
    {
        this.productRepository = productRepository;
        this.productLeftLogRepository = productLeftLogRepository;
    }
    @Transactional
    public Product editProductQuantity(Long userID, Long productID, Integer amountLeftChange, String message)
    {
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(opProduct.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        if (Objects.equals(product.getUserID(), userID))
        {
            updateProductAmountLeft(product, amountLeftChange);
            ProductLeftLog productLeftLog = new ProductLeftLog();
            productLeftLog.setProduct(product);
            productLeftLog.setAmountLeftChange(amountLeftChange);
            productLeftLog.setUserID(userID);
            productLeftLog.setMessage(message);
            productLeftLog.setCreationTime(ZonedDateTime.now());
            this.productLeftLogRepository.save(productLeftLog);
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not belong to user");
    }

    @Transactional
    public void updateProductAmountLeft(Product product, Integer amountLeftChange) {
        Integer amountLeft = product.getAmountLeft();
        if (amountLeft + amountLeftChange < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough amount for the request");
        }
        product.setAmountLeft(amountLeft + amountLeftChange);

        productRepository.save(product);
    }
}
