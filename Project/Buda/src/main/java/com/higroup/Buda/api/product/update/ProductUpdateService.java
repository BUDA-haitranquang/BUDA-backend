package com.higroup.Buda.api.product.update;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.BeanUtils.NullAwareBeanUtilsBean;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class ProductUpdateService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductUpdateService(ProductRepository productRepository, UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Product editProduct(Long userID, Long productID, Product product) throws InvocationTargetException, IllegalAccessException {

        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product dest_product = opProduct.get();
        if (dest_product.getUserID().equals(userID))
        {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            product.setProductSKU(dest_product.getProductSKU());
            notNull.copyProperties(dest_product, product);
            this.productRepository.save(dest_product);
            return dest_product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not belong to user");

    }
}
