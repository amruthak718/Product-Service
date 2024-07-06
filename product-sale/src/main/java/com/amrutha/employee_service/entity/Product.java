package com.amrutha.employee_service.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

        @Id
         private Integer id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private double price;

        @Column(nullable = false)
        private int quantity;

        @Column(nullable = false)
        private String description;

        @OneToMany(cascade = CascadeType.ALL)
        private List<Sale> sales;

}
