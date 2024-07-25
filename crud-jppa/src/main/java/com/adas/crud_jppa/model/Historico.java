package com.adas.crud_jppa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
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


        @ManyToMany
        @JoinTable(
                name = "registro_venda",
                joinColumns = @JoinColumn(name = "historico_id"),
                inverseJoinColumns = @JoinColumn(name = "produto_id")
        )
        private List<Produto> produtosHistorico;

            @NonNull
        private LocalDateTime data;
    }


