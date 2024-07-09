package com.adas.crud_jppa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor

@AllArgsConstructor

@Data
@Builder

@Entity
public class Caixa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    private boolean status;

    @NonNull
    private Double saldo;

    @NonNull
    private Double limite;
}
