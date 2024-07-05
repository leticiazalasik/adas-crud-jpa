package com.adas.crud_jppa.repository;

import com.adas.crud_jppa.model.Categoria;
import com.adas.crud_jppa.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
