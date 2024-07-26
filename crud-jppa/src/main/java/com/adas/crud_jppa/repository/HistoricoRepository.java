package com.adas.crud_jppa.repository;

import com.adas.crud_jppa.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {


    @Query("SELECT h.id, p.nome, c.nome FROM Historico h " +
            "JOIN h.produtosHistorico p " +
            "JOIN h.cliente c " +
            "ORDER BY h.id ASC")
    List<Object[]> buscarDetalhesDeTodosOsHistoricos();


}
