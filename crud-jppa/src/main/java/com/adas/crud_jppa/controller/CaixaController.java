package com.adas.crud_jppa.controller;

import com.adas.crud_jppa.model.Caixa;
import com.adas.crud_jppa.model.Produto;
import com.adas.crud_jppa.service.CaixaService;
import com.adas.crud_jppa.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    private CaixaService caixaService;

    @PostMapping("/novo")
    public ResponseEntity<Caixa> cadastrarNovoCaixa(@RequestBody Produto produto) {

        return ResponseEntity.ok(produtoService.salvar(produto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Caixa>abrir(){

        if (caixa. == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoService.salvar(produto));
    }
}
