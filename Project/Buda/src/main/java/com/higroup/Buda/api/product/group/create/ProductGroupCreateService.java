package com.higroup.Buda.api.product.group.create;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.ProductRepository;

@Service
public class ProductGroupCreateService {
    private final ProductGroupRepository productGroupRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductGroupCreateService(ProductGroupRepository productGroupRepository, ProductRepository productRepository) {
        this.productGroupRepository = productGroupRepository;
        this.productRepository = productRepository;
    }
    public List<ProductGroup> findAllByUserID(Long userID)
    {
        return this.productGroupRepository.findAllByUserID(userID);
    }
    @Transactional
    public ProductGroup createProductGroup(Long userID, ProductGroup productGroup)
    {

        productGroup.setUserID(userID);
        this.productGroupRepository.save(productGroup);
        return productGroup;
    }
    @Transactional
    public void deleteProductGroup(Long userID, Long productGroupID)
    {
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if (productGroup.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product group not found");
        }
        this.productGroupRepository.delete(productGroup.get());
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
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
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
    @Transactional
    public void removeProductFromProductGroup(Long userID, Long productGroupID, Long productID)
    {
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        if (!product.getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if (productGroup.isPresent() && Objects.equals(productGroup.get().getUserID(), userID))
        {
            Set<ProductGroup> productGroups = product.getProductGroups();
            if (productGroups.removeIf(productGroup1 -> productGroup1.getProductGroupID().equals(productGroupID)))
            {
                product.setProductGroups(productGroups);
                this.productRepository.save(product);
                Set<Product> products = productGroup.get().getProducts();
                products.removeIf(product1 -> product1.getProductID().equals(productID));
                productGroup.get().setProducts(products);
                this.productGroupRepository.save(productGroup.get());
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    public List<Product> findAllProductByProductGroup(Long userID, Long productGroupID)
    {
        return this.productRepository.findAllProductByProductGroup(productGroupID, userID);
    }
}
