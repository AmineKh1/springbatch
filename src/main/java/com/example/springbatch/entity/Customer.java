package com.example.springbatch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="CUSTOMER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private int id;
    private String email;
}
