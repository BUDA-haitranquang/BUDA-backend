package com.higroup.Buda.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.repositories.IngredientRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IngredientRepositoryTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");
    
    @Autowired
    private IngredientRepository ingredientRepository;

    
    private Ingredient ingredient;

    @BeforeEach
    public void setUp(){
        //ingredient
        ingredient = new Ingredient();
        ingredient.setName("carrot");
        ingredient.setPrice(20.0);
        ingredient.setUserID(12345L);
        ingredient.setAlertAmountLeft(10);
    }

    @AfterEach
    public void tearDown(){
        ingredientRepository.deleteAll();
    }

    @Test
    public void canFindIngredientByIngredientID(){
        // save ingredient
        ingredientRepository.save(ingredient);
        Long ingredientID = ingredient.getIngredientID();
        
        Optional<Ingredient> findIngredient = ingredientRepository.findIngredientByIngredientID(ingredientID);
        
        if(findIngredient.isPresent()){
            assertEquals(findIngredient.get(), ingredient);
        }
        else fail("cant find ingredient");

    }

    @Test
    public void canFindAllIngredientByUserID(){
        Long userID = 12345L;
        ingredientRepository.save(ingredient);

        List<Ingredient> allIngredients = ingredientRepository.findAllIngredientByUserID(userID);
        
        assertEquals(allIngredients, Arrays.asList(ingredient));
    }

    @Test 
    public void canFindIngredientByName(){
        String ingredientName = "carrot";
        
        ingredientRepository.save(ingredient);

        Optional<Ingredient> findingredient = ingredientRepository.findIngredientByName(ingredientName);
        if(findingredient.isPresent()){
            assertEquals(findingredient.get(), ingredient);
        }
        else{
            fail("Cant find ingredient");
        }
    }
}
