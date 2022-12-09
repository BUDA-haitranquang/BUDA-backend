package com.higroup.Buda.api.product.packaging.component.add;

import java.util.Optional;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductComponent;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.ProductComponentRepository;
import com.higroup.Buda.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AddProductComponentService {
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;
    private final ProductComponentRepository productComponentRepository;
    @Autowired 
    public AddProductComponentService(ProductRepository productRepository, ProductComponentRepository productComponentRepository,
    IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
        this.productComponentRepository = productComponentRepository;
        this.productRepository = productRepository;
    }
    public ProductComponent addProductComponent(Long userID, AddProductComponentDTO addProductComponentDTO){
        Optional<Product> opProduct = this.productRepository.findProductByProductID(addProductComponentDTO.getProductID());
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        Optional<Ingredient> ingredientOptional = this.ingredientRepository.findIngredientByIngredientID(addProductComponentDTO.getIngredientID());
        if ((product != null) && (ingredientOptional.isPresent()) 
        && (product.getUserID().equals(userID))
        && (ingredientOptional.get().getUserID().equals(userID))){
            Ingredient ingredient = ingredientOptional.get();
            ProductComponent productComponent = new ProductComponent();
            productComponent.setIngredient(ingredient);
            productComponent.setProduct(product);
            productComponent.setUserID(userID);
            productComponent.setRequiredQuantity(addProductComponentDTO.getRequiredQuantity());
            productComponent.setTotalCost(addProductComponentDTO.getRequiredQuantity() * ingredient.getPrice());
            this.productComponentRepository.save(productComponent);
            return productComponent;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product or Ingredient can not be found");
    }
}
