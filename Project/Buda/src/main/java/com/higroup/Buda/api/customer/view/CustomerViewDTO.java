package com.higroup.Buda.api.customer.view;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.MembershipType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerViewDTO {
    Customer customer;
    MembershipType membershipType;

}
