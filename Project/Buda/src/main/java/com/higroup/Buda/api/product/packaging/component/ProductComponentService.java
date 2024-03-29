package com.higroup.Buda.api.product.packaging.component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductComponent;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.ProductComponentRepository;
import com.higroup.Buda.repositories.ProductRepository;

@Service
public class ProductComponentService {
    private final ProductComponentRepository productComponentRepository;
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public ProductComponentService(ProductComponentRepository productComponentRepository, ProductRepository productRepository, IngredientRepository ingredientRepository) {
        this.productComponentRepository = productComponentRepository;
        this.productRepository = productRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public ProductComponent findByProductAndIngredient(Long productID, Long ingredientID)
    {
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);
        if (ingredient.isEmpty())
        {
            return null;
        }
        Optional<ProductComponent> productComponent = this.productComponentRepository.findByProductAndIngredient(productID, ingredientID);
        return productComponent.orElse(null);
    }

    public List<ProductComponent> findAllByProductID(Long userID, Long productID)
    {
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        if ((product!=null) && (Objects.equals(product.getUserID(), userID)))
        {
            return this.productComponentRepository.findAllByProductID(productID);
        }
        else return Collections.emptyList();
    }

    public List<Product> findAllProductContainIngredient(Long userID, Long ingredientID)
    {
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);
        if ((ingredient.isPresent()) && (Objects.equals(ingredient.get().getUserID(), userID)))
        {
            return this.productRepository.findAllProductContainIngredient(ingredientID);
        }
        else return Collections.emptyList();
    }
    @Transactional
    public ProductComponent addIngredientToProduct(Long userID, Long productID, Long ingredientID)
    {
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        if (!product.getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        if ((ingredient.isPresent()) && (Objects.equals(ingredient.get().getUserID(), userID)))
        {
            ProductComponent productComponent = new ProductComponent();
            productComponent.setProduct(product);
            productComponent.setIngredient(ingredient.get());
            productComponent.setUserID(userID);
            this.productComponentRepository.save(productComponent);
            return productComponent;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
        }
    }
    @Transactional
    public void removeIngredientFromProduct(Long userID, Long productID, Long ingredientID)
    {
        Optional<ProductComponent> productComponent = this.productComponentRepository.findByProductAndIngredient(productID, ingredientID);
        if ((productComponent.isPresent()) && (Objects.equals(productComponent.get().getUserID(), userID)))
        {
            this.productComponentRepository.delete(productComponent.get());
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not in Product");
        }
    }
}
