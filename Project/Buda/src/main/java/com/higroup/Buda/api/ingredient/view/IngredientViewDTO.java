package com.higroup.Buda.api.ingredient.view;

import java.util.List;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.repositories.IngredientRepository.ViewIngredientInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class IngredientViewDTO {
    private Long count;
    private List<ViewIngredientInfo> ingredients;
}
