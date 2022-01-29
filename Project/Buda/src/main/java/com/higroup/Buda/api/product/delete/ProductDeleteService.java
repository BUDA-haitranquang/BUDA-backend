package com.higroup.Buda.api.product.delete;

import java.util.Objects;
import java.util.Optional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductDeleteService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductDeleteService(ProductRepository productRepository, UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void deleteProductByProductID(Long userID, Long productID)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
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
