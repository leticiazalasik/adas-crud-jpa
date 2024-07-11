package com.adas.crud_jppa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor

@AllArgsConstructor
@Data

@Builder

@Entity
public class Historico {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;

       @ManyToOne
       @JoinColumn(name="cliente_id")
       private Cliente cliente;


        @ManyToMany(mappedBy = "historicos")
        private List<Produto> produtos;
    }


