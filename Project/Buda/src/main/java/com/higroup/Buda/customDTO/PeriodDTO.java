package com.higroup.Buda.customDTO;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeriodDTO {
    private ZonedDateTime from;
    private ZonedDateTime to;
}
