package com.higroup.Buda.api.product.group.addproduct;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AddProductToGroupService {
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    @Autowired
    public AddProductToGroupService(ProductGroupRepository productGroupRepository, ProductRepository productRepository){
        this.productGroupRepository = productGroupRepository;
        this.productRepository = productRepository;
    }
    @Transactional
    public ProductGroup addProductToProductGroup(Long userID, Long productGroupID, Long productID)
    {
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if (productGroup.isPresent() && Objects.equals(productGroup.get().getUserID(), userID))
        {
            Set<Product> products = productGroup.get().getProducts();
            for (Product product : products) {
                if (product.getProductID().equals(productID)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product's already in the group.");
                }
            }
            Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
            if(!opProduct.isPresent()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
            }
            Product product = opProduct.get();
            if (!product.getUserID().equals(userID))
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not belong to user");
            }
            Set<ProductGroup> productGroups = product.getProductGroups();
            productGroups.add(productGroup.get());
            product.setProductGroups(productGroups);
            products.add(product);
            productGroup.get().setProducts(products);
            this.productRepository.save(product);
            this.productGroupRepository.save(productGroup.get());
            return productGroup.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product group not found");
    }
}
