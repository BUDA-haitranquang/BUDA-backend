package com.higroup.Buda.api.ingredient.quantitylog;

import java.util.List;

import com.higroup.Buda.repositories.IngredientLeftLogRepository.ViewIngredientLeftLogInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class IngredientLeftLogViewDTO {
    private Long count;
    private List<ViewIngredientLeftLogInfo> ingredientLeftLogs;
}
