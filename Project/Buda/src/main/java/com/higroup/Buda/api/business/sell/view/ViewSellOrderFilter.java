package com.higroup.Buda.api.business.sell.view;

import java.time.ZonedDateTime;

import com.higroup.Buda.entities.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewSellOrderFilter {
    private String customerName;
    private ZonedDateTime from;
    private ZonedDateTime to;
    private Status status;
    private String textID;
}
