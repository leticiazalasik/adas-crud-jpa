package com.adas.crud_jppa;

import java.util.HashMap;
import java.util.Map;

public class ExemploMap {

    public static void main(String[] args) {
        //exemplo de delcaraçao de um Hashmap

        Map<String, Object> objetoGenereico = new HashMap<>();

        //Inserindo valores em um HashMap "PUT"
        objetoGenereico.put("ID", 7);
        objetoGenereico.put("NOME", "HARRY");
        objetoGenereico.put("STATUS", true);

        //Exibindo os valores do HashMap "GET"
        System.out.println("Id: " + objetoGenereico.get("ID"));
        System.out.println("Nome: " + objetoGenereico.get("NOME"));
        System.out.println("Status: " + objetoGenereico.get("STATUS"));

        import java.util.HashMap;
import java.util.Map;

        public class ConsultaComHashMap {

            // Simulando um banco de dados (apenas para exemplo)
            private static final Map<Integer, String> bancoDeDados = new HashMap<>();

            // Método para executar a consulta
            public static String executarConsulta(HashMap<String, Object> criterios) {
                // Aqui, você pode construir sua consulta real usando os critérios do HashMap
                // Por exemplo, se você quiser buscar um produto pelo ID:
                Integer idProduto = (Integer) criterios.get("id");
                return bancoDeDados.get(idProduto);
            }

            public static void main(String[] args) {
                // Exemplo de uso
                bancoDeDados.put(1, "Produto A");
                bancoDeDados.put(2, "Produto B");

                HashMap<String, Object> criterios = new HashMap<>();
                criterios.put("id", 1); // Buscar produto com ID 1

                String resultado = executarConsulta(criterios);
                System.out.println("Resultado da consulta: " + resultado);
            }
        }


    }
}
