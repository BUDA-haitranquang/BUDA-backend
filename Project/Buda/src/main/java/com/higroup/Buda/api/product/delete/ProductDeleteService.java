package com.higroup.Buda.api.product.delete;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.repositories.ProductRepository;

@Service
public class ProductDeleteService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductDeleteService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }
    @Transactional
    public void deleteProductByProductID(Long userID, Long productID)
    {
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(opProduct.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();

        if (Objects.equals(product.getUserID(), userID))
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
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not belong to user");
    }
}
