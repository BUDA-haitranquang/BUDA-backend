package com.higroup.Buda.api.retail;

import com.higroup.Buda.api.product.packaging.component.add.AddProductComponentDTO;
import com.higroup.Buda.api.product.packaging.component.add.AddProductComponentService;
import com.higroup.Buda.api.retail.customDTO.RetailCreateDTO;
import com.higroup.Buda.api.retail.customDTO.RetailCreateFromIngredientDTO;
import com.higroup.Buda.api.retail.customDTO.RetailCreateFromProductDTO;
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
    public Product createNewRetailFromIngredient(Long userID, RetailCreateFromIngredientDTO retailCreateFromIngredientDTO)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(retailCreateFromIngredientDTO.getIngredientID());
        if (ingredient.isPresent() && Objects.equals(ingredient.get().getUserID(), userID))
        {
            Product productBySKU = this.productRepository.findProductByUserIDAndProductSKU(userID, retailCreateFromIngredientDTO.getProductSKU());
            if (productBySKU!=null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another product with this SKU has already existed: " + productBySKU.getName());
            }
            Product product = new Product();
            product.setProductSKU(retailCreateFromIngredientDTO.getProductSKU());
            product.setUserID(userID);
            product.setName(ingredient.get().getName());
            product.setDescription(ingredient.get().getDescription());
            product.setAmountLeft(ingredient.get().getAmountLeft());
            product.setAlertAmount(ingredient.get().getAlertAmountLeft());
            product.setSellingPrice(retailCreateFromIngredientDTO.getPrice());
            product.setCostPerUnit(ingredient.get().getPrice());
            product.setPictureID(ingredient.get().getPictureID());
            this.productRepository.save(product);
            Product tmp = this.productRepository.findProductByUserIDAndProductSKU(userID, product.getProductSKU());
            AddProductComponentDTO addProductComponentDTO = new AddProductComponentDTO(tmp.getProductID(), retailCreateFromIngredientDTO.getIngredientID(), 1L);
            this.addProductComponentService.addProductComponent(userID, addProductComponentDTO);
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
    }
    public Ingredient createNewRetailFromProduct(Long userID, RetailCreateFromProductDTO retailCreateFromProductDTO)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Optional<Product> product = this.productRepository.findProductByProductID(retailCreateFromProductDTO.getProductID());
        if (product.isPresent() && Objects.equals(product.get().getUserID(), userID))
        {
            Ingredient ingredientBySKU = this.ingredientRepository.findIngredientByUserIDAndIngredientSKU(userID, retailCreateFromProductDTO.getIngredientSKU());
            if (ingredientBySKU!=null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another ingredient with this SKU has already existed: " + ingredientBySKU.getName());
            }
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientSKU(retailCreateFromProductDTO.getIngredientSKU());
            ingredient.setUserID(userID);
            ingredient.setName(product.get().getName());
            ingredient.setDescription(product.get().getDescription());
            ingredient.setAmountLeft(product.get().getAmountLeft());
            ingredient.setAlertAmountLeft(product.get().getAlertAmount());
            ingredient.setPrice(product.get().getCostPerUnit());
            ingredient.setPictureID(product.get().getPictureID());
            this.ingredientRepository.save(ingredient);
            Ingredient tmp = this.ingredientRepository.findIngredientByUserIDAndIngredientSKU(userID, ingredient.getIngredientSKU());
            AddProductComponentDTO addProductComponentDTO = new AddProductComponentDTO(retailCreateFromProductDTO.getProductID(), tmp.getIngredientID(), 1L);
            this.addProductComponentService.addProductComponent(userID, addProductComponentDTO);
            return ingredient;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }
    public Product createNewRetail(Long userID, RetailCreateDTO retailCreateDTO)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Product productBySKU = this.productRepository.findProductByUserIDAndProductSKU(userID, retailCreateDTO.getProductSKU());
        if (productBySKU!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another product with this SKU has already existed: " + productBySKU.getName());
        }
        Ingredient ingredientBySKU = this.ingredientRepository.findIngredientByUserIDAndIngredientSKU(userID, retailCreateDTO.getIngredientSKU());
        if (ingredientBySKU!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another ingredient with this SKU has already existed: " + ingredientBySKU.getName());
        }
        Product product = new Product();
        product.setProductSKU(retailCreateDTO.getProductSKU());
        product.setUserID(userID);
        product.setName(retailCreateDTO.getName());
        product.setDescription(retailCreateDTO.getDescription());
        product.setAmountLeft(retailCreateDTO.getAmountLeft());
        product.setAlertAmount(retailCreateDTO.getAlertAmountLeft());
        product.setSellingPrice(retailCreateDTO.getSellingPrice());
        product.setCostPerUnit(retailCreateDTO.getPrice());
        product.setPictureID(retailCreateDTO.getPictureID());
        this.productRepository.save(product);
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientSKU(retailCreateDTO.getIngredientSKU());
        ingredient.setUserID(userID);
        ingredient.setName(retailCreateDTO.getName());
        ingredient.setDescription(retailCreateDTO.getDescription());
        ingredient.setAmountLeft(retailCreateDTO.getAmountLeft());
        ingredient.setAlertAmountLeft(retailCreateDTO.getAlertAmountLeft());
        ingredient.setPrice(retailCreateDTO.getPrice());
        ingredient.setPictureID(retailCreateDTO.getPictureID());
        this.ingredientRepository.save(ingredient);
        Product tmpProduct = this.productRepository.findProductByUserIDAndProductSKU(userID, product.getProductSKU());
        Ingredient tmpIngredient = this.ingredientRepository.findIngredientByUserIDAndIngredientSKU(userID, ingredient.getIngredientSKU());
        this.addProductComponentService.addProductComponent(userID, new AddProductComponentDTO(tmpProduct.getProductID(), tmpIngredient.getIngredientID(), 1L));
        return product;
    }
}
