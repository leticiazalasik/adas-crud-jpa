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



    }
}
