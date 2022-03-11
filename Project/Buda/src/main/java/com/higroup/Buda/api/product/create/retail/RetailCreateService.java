package com.higroup.Buda.api.product.create.retail;

import com.higroup.Buda.api.product.packaging.component.add.AddProductComponentDTO;
import com.higroup.Buda.api.product.packaging.component.add.AddProductComponentService;
import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
public class RetailCreateService {
    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final AddProductComponentService addProductComponentService;

    @Autowired
    public RetailCreateService(IngredientRepository ingredientRepository, ProductRepository productRepository, UserRepository userRepository, AddProductComponentService addProductComponentService)
    {
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.addProductComponentService = addProductComponentService;
    }
    @Transactional
    public Product createNewRetailProduct(Long userID, RetailCreateDTO retailCreateDTO)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(retailCreateDTO.getIngredientID());
        if (ingredient.isPresent() && Objects.equals(ingredient.get().getUserID(), userID))
        {
            Product productBySKU = this.productRepository.findProductByUserIDAndProductSKU(userID, retailCreateDTO.getProductSKU());
            if (productBySKU!=null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another product with this SKU has already existed: " + productBySKU.getName());
            }
            Product product = new Product();
            product.setProductSKU(retailCreateDTO.getProductSKU());
            product.setUserID(userID);
            product.setName(ingredient.get().getName());
            product.setDescription(ingredient.get().getDescription());
            product.setAmountLeft(ingredient.get().getAmountLeft());
            product.setAlertAmount(ingredient.get().getAlertAmountLeft());
            product.setSellingPrice(retailCreateDTO.getPrice());
            product.setCostPerUnit(ingredient.get().getPrice());
            product.setPictureID(ingredient.get().getPictureID());
            this.productRepository.save(product);
            Product tmp = this.productRepository.findProductByUserIDAndProductSKU(userID, product.getProductSKU());
            AddProductComponentDTO addProductComponentDTO = new AddProductComponentDTO(tmp.getProductID(), retailCreateDTO.getIngredientID(), 1L);
            this.addProductComponentService.addProductComponent(userID, addProductComponentDTO);
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
    }
}
