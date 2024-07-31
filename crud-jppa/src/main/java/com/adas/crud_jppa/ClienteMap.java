package com.adas.crud_jppa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
public class ClienteMap {
    private static final String url = "jdbc:postgresql://localhost/crud_jpa";
        private static final String user = "postgres"; // Substitua pelo seu usuário do PostgreSQL
        private static final String password = "2149"; // Substitua pela sua senha do PostgreSQL

        private static final String QUERY = "SELECT id, nome, cpf FROM cliente WHERE id = ?";

        public String executarConsulta(HashMap<String, Object> criterios) {
            try (Connection connection = DriverManager.getConnection(url, user, password);
                 PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
                preparedStatement.setInt(1, (int) criterios.get("id"));

                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    return "ID: " + rs.getInt("id") +
                            ", Nome: " + rs.getString("nome") +
                            ", cpf: " + rs.getString("cpf");
                } else {
                    return "Nenhum registro encontrado.";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return "Erro ao executar a consulta.";
            }
        }

    }
