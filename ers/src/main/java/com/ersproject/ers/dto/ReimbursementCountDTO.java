package com.ersproject.ers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReimbursementCountDTO {
    private int total;
    private int approved;
    private int pending;
    private int denied;
}
