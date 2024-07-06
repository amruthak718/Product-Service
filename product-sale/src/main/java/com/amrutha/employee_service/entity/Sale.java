package com.amrutha.employee_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Sale {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private int productId;

        private int quantity;

        private LocalDate saleDate;


}
