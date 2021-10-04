package com.higroup.Buda.services;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductComponent;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.ProductComponentRepository;
import com.higroup.Buda.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ProductComponent findByProductAndIngredient(Long productID, Long ingredientID)
    {
        Optional<Product> product = this.productRepository.findProductByProductID(productID);
        if (product.isEmpty())
        {
            return null;
        }
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);
        if (ingredient.isEmpty())
        {
            return null;
        }
        Optional<ProductComponent> productComponent = this.productComponentRepository.findByProductAndIngredient(productID, ingredientID);
        return productComponent.orElse(null);
    }
}
