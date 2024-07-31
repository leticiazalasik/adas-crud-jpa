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


	class ConsultaComHashMap {

		private static final String url = "jdbc:postgresql://localhost/mydb";
		private static final String user = "postgres";
		private static final String password = "2149";

		private static final String QUERY = "SELECT id, name, preco FROM produto WHERE id = ?";

		public static void main(String[] args) {
			HashMap<String, Object> criterios = new HashMap<>();
			criterios.put("id", 1); // Buscar usuário com ID 1

			String resultado = executarConsulta(criterios);
			System.out.println("Resultado da consulta: " + resultado);
		}

		public static String executarConsulta(HashMap<String, Object> criterios) {
			try (Connection connection = DriverManager.getConnection(url, user, password);
				 PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
				preparedStatement.setInt(1, (int) criterios.get("id"));

				ResultSet rs = preparedStatement.executeQuery();
				if (rs.next()) {
					return "ID: " + rs.getInt("id") +
							", Nome: " + rs.getString("name") +
							", Preco: " + rs.getString("email") ;
				} else {
					return "Nenhum registro encontrado.";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return "Erro ao executar a consulta.";
			}
		}
	}

}
}
