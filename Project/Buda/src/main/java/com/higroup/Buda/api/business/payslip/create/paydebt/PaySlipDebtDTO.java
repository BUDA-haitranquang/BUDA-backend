package com.higroup.Buda.api.business.payslip.create.paydebt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaySlipDebtDTO {
    private List<Long> buyOrderIDs;
}
