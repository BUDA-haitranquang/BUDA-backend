package com.higroup.Buda.services;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductComponent;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.ProductComponentRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private PresentChecker presentChecker;

    public ProductComponent findByProductAndIngredient(Long productID, Long ingredientID)
    {
        presentChecker.checkIdAndRepository(productID, productRepository);
        Product product = this.productRepository.findProductByProductID(productID);
        
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
        Product product = this.productRepository.findProductByProductID(productID);
        if ((product!=null) && (product.getUserID() == userID))
        {
            return this.productComponentRepository.findAllByProductID(productID);
        }
        else return Collections.emptyList();
    }

    public List<Product> findAllProductContainIngredient(Long userID, Long ingredientID)
    {
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);
        if ((ingredient.isPresent()) && (ingredient.get().getUserID() == userID))
        {
            return this.productRepository.findAllProductContainIngredient(ingredientID);
        }
        else return Collections.emptyList();
    }
}
