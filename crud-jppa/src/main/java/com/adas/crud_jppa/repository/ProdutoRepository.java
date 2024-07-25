package com.adas.crud_jppa.repository;

import com.adas.crud_jppa.model.Categoria;
import com.adas.crud_jppa.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
@Query("SELECT p FROM Produto p WHERE p.nome = :NOME")
List<Produto> findByExactNome(@Param("NOME") String nome);

    @Query("SELECT p FROM Produto p WHERE p.nome ILIKE %:NOME%")
    List<Produto> findBySimilarNome(@Param("NOME") String nome);
@Query("SELECT p FROM Produto p WHERE p.preco >= :PRECO")
    List<Produto>findByPreco(@Param("PRECO")Double preco);

    @Query("SELECT p FROM Produto p WHERE p.categoria.id = :CATEGORIA_ID")
    List<Produto> findByCategoria(@Param("CATEGORIA_ID") Integer categoriaId);
    @Query("SELECT p FROM Produto p WHERE p.categoria.id = :CATEGORIA_ID AND p.categoria.status=true")
    List<Produto> findByCategoriaAtiva(@Param("CATEGORIA_ID") Integer categoriaId);

}
//Esse integrer é a chave primária