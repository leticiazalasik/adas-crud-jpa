package com.adas.crud_jppa.repository;

import com.adas.crud_jppa.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    List<Categoria> findByNome(String nome);

    List<Categoria> findByNomeContainingIgnoreCase(String nome);

    List<Categoria> findByStatusTrue();



}
