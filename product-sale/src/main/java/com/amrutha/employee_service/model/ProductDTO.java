package com.amrutha.employee_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductDTO {

    private Integer id;

    private String name;

    private double price;

    private int quantity;

    private String description;

    private List<SaleDTO> sales;


}
