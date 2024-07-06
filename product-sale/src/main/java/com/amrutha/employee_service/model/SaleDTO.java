package com.amrutha.employee_service.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaleDTO {

    private int id;

    private int quantity;

    private LocalDate saleDate;


}
