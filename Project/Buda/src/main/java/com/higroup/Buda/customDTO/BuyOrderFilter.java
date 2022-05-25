package com.higroup.Buda.customDTO;

import java.time.ZonedDateTime;

import com.higroup.Buda.entities.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyOrderFilter {
    private int page;
    private int limit;
    private String textID;
    private String supplierName;
    private Status status;
    private ZonedDateTime from;
    private ZonedDateTime to;
}
