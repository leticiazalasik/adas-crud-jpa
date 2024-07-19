package com.adas.crud_jppa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor

@AllArgsConstructor

@Getter @Setter

@Builder

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    private String nome;

    @NonNull
    private Double preco;

    @NonNull
    private Integer quantidade;

    //Vinculando varios produto para uma categoria

    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    //Vinculando produto para caixa, muito para muitos.
    @ManyToMany(mappedBy = "produtosCaixa")
    private List<Caixa>caixas;

}
