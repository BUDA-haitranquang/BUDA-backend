package com.higroup.Buda.api.product.group.addproduct;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddProductToGroupDTO {
    @NotNull(message = "product ID can not be null")
    private Long productID;
    @NotNull(message = "group ID can not be null")
    private Long productGroupID;
}
