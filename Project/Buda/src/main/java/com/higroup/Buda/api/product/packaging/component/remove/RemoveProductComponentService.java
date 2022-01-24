package com.higroup.Buda.api.product.packaging.component.remove;

import java.util.Optional;

import com.higroup.Buda.entities.ProductComponent;
import com.higroup.Buda.repositories.ProductComponentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RemoveProductComponentService {
    private final ProductComponentRepository productComponentRepository;
    @Autowired
    public RemoveProductComponentService(ProductComponentRepository productComponentRepository){
        this.productComponentRepository = productComponentRepository;
    }
    public void removeProductComponent(Long userID, RemoveProductComponentDTO removeProductComponentDTO){
        Optional<ProductComponent> productComponentOptional = 
        this.productComponentRepository.findByProductAndIngredient(removeProductComponentDTO.getProductID(), 
        removeProductComponentDTO.getIngredientID());
        if ((productComponentOptional.isPresent()) 
        && (productComponentOptional.get().getUserID().equals(userID))){
            ProductComponent productComponent = productComponentOptional.get();
            this.productComponentRepository.delete(productComponent);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product component not found");
        }
    }
}
