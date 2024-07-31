package com.adas.crud_jppa.repository;

import com.adas.crud_jppa.model.Categoria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    List<Categoria> findByNome(String nome);

    List<Categoria> findByNomeContainingIgnoreCase(String nome);

    List<Categoria> findByStatusTrue();

    @Modifying
    @Transactional
    @Query("UPDATE Categoria c SET c.status = :STATUS WHERE c.id = :ID")
    int updateCategoriaById(@Param("ID") int id, @Param("STATUS") Boolean status);

}
