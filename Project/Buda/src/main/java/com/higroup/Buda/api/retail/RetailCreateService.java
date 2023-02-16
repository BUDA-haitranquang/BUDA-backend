package com.higroup.Buda.api.retail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.api.product.packaging.component.add.AddProductComponentDTO;
import com.higroup.Buda.api.product.packaging.component.add.AddProductComponentService;
import com.higroup.Buda.api.retail.customDTO.RetailCreateDTO;
import com.higroup.Buda.api.retail.customDTO.RetailCreateFromIngredientDTO;
import com.higroup.Buda.api.retail.customDTO.RetailCreateFromProductDTO;
import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.PictureRepository;
import com.higroup.Buda.repositories.ProductRepository;

@Service
public class RetailCreateService {
    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;
    private final AddProductComponentService addProductComponentService;
    private final PictureRepository pictureRepository;

    @Autowired
    public RetailCreateService(IngredientRepository ingredientRepository, ProductRepository productRepository,
            AddProductComponentService addProductComponentService, PictureRepository pictureRepository) {
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository;
        this.addProductComponentService = addProductComponentService;
        this.pictureRepository = pictureRepository;
    }

    @Transactional
    public Product createNewRetailFromIngredient(Long userID,
            RetailCreateFromIngredientDTO retailCreateFromIngredientDTO) {
        Optional<Ingredient> ingredient = this.ingredientRepository
                .findIngredientByIngredientID(retailCreateFromIngredientDTO.getIngredientID());
        if ((ingredient.isPresent()) && (ingredient.get().getUserID().equals(userID))) {
            Long count = this.productRepository.findNumberOfProductByUserID(userID);
            Product product = new Product();
            product.setProductSKU("PRO-" + count);
            product.setUserID(userID);
            product.setName(ingredient.get().getName());
            product.setDescription(ingredient.get().getDescription());
            product.setAmountLeft(ingredient.get().getAmountLeft());
            product.setAlertAmount(ingredient.get().getAlertAmountLeft());
            product.setSellingPrice(retailCreateFromIngredientDTO.getPrice());
            product.setCostPerUnit(ingredient.get().getPrice());
            product.setPicture(ingredient.get().getPicture());
            this.productRepository.save(product);
            Product tmp = this.productRepository.findProductByUserIDAndProductSKU(userID, product.getProductSKU());
            AddProductComponentDTO addProductComponentDTO = new AddProductComponentDTO(tmp.getProductID(),
                    retailCreateFromIngredientDTO.getIngredientID(), 1L);
            this.addProductComponentService.addProductComponent(userID, addProductComponentDTO);
            return product;
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
    }
    @Transactional
    public Ingredient createNewRetailFromProduct(Long userID, RetailCreateFromProductDTO retailCreateFromProductDTO) {
        Optional<Product> product = this.productRepository
                .findProductByProductID(retailCreateFromProductDTO.getProductID());
        if ((product.isPresent()) && (product.get().getUserID().equals(userID))) {
            Long count = this.ingredientRepository.findNumberOfIngredientByUserID(userID);
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientSKU("ING-" + count);
            ingredient.setUserID(userID);
            ingredient.setName(product.get().getName());
            ingredient.setDescription(product.get().getDescription());
            ingredient.setAmountLeft(product.get().getAmountLeft());
            ingredient.setAlertAmountLeft(product.get().getAlertAmount());
            ingredient.setPrice(product.get().getCostPerUnit());
            ingredient.setPicture(product.get().getPicture());
            this.ingredientRepository.save(ingredient);
            Ingredient tmp = this.ingredientRepository.findIngredientByUserIDAndIngredientSKU(userID,
                    ingredient.getIngredientSKU());
            AddProductComponentDTO addProductComponentDTO = new AddProductComponentDTO(
                    retailCreateFromProductDTO.getProductID(), tmp.getIngredientID(), 1L);
            this.addProductComponentService.addProductComponent(userID, addProductComponentDTO);
            return ingredient;
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }
    @Transactional
    public Product createNewRetail(Long userID, RetailCreateDTO retailCreateDTO) {

        Product product = new Product();
        Long countProduct = this.productRepository.findNumberOfProductByUserID(userID);
        product.setProductSKU("PRO-" + countProduct);
        product.setUserID(userID);
        product.setName(retailCreateDTO.getName());
        product.setDescription(retailCreateDTO.getDescription());
        product.setAmountLeft(retailCreateDTO.getAmountLeft());
        product.setAlertAmount(retailCreateDTO.getAlertAmountLeft());
        product.setSellingPrice(retailCreateDTO.getSellingPrice());
        product.setCostPerUnit(retailCreateDTO.getPrice());
        Picture picture = this.pictureRepository.findPictureByPictureID(retailCreateDTO.getPictureID()).get();
        try {
            product.setPicture(picture);
        } catch (Exception e) {
        }
        this.productRepository.save(product);
        Ingredient ingredient = new Ingredient();
        Long countIngredient = this.ingredientRepository.findNumberOfIngredientByUserID(userID);
        ingredient.setIngredientSKU("ING-" + countIngredient);
        ingredient.setUserID(userID);
        ingredient.setName(retailCreateDTO.getName());
        ingredient.setDescription(retailCreateDTO.getDescription());
        ingredient.setAmountLeft(retailCreateDTO.getAmountLeft());
        ingredient.setAlertAmountLeft(retailCreateDTO.getAlertAmountLeft());
        ingredient.setPrice(retailCreateDTO.getPrice());
        ingredient.setPicture(picture);
        try {
            ingredient.setPicture(picture);
        } catch (Exception e) {
        }
        this.ingredientRepository.save(ingredient);
        Product tmpProduct = this.productRepository.findProductByUserIDAndProductSKU(userID, product.getProductSKU());
        Ingredient tmpIngredient = this.ingredientRepository.findIngredientByUserIDAndIngredientSKU(userID,
                ingredient.getIngredientSKU());
        this.addProductComponentService.addProductComponent(userID,
                new AddProductComponentDTO(tmpProduct.getProductID(), tmpIngredient.getIngredientID(), 1L));
        return product;
    }
}
