package com.adas.crud_jppa.repository;

import com.adas.crud_jppa.model.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaixaRepository extends JpaRepository<Caixa, Integer> {
}
