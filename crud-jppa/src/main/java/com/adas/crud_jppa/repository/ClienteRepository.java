package com.adas.crud_jppa.repository;

import com.adas.crud_jppa.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
