package com.adas.crud_jppa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.HashMap;

@SpringBootApplication
public class CrudJppaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudJppaApplication.class, args);

		ClienteMap clienteMap = new ClienteMap();

		HashMap<String, Object> criterios = new HashMap<>();
		criterios.put("id", 2); // Buscar usuário com ID 1

		String resultado = clienteMap.executarConsulta(criterios);
		System.out.println("Resultado da consulta: " + resultado);
	}
}

