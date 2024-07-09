package com.adas.crud_jppa.controller;

import com.adas.crud_jppa.model.Caixa;
import com.adas.crud_jppa.model.Categoria;
import com.adas.crud_jppa.model.Produto;
import com.adas.crud_jppa.service.CaixaService;
import com.adas.crud_jppa.service.CategoriaService;
import com.adas.crud_jppa.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
        private ProdutoService produtoService;

    @GetMapping("/todos")
        public ResponseEntity<List<Produto>> findAll() {

            return ResponseEntity.ok(produtoService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Produto>findById(@PathVariable int id){
            Produto produtoEncontrado=produtoService.findById(id);

            if(produtoEncontrado==null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(produtoEncontrado);
        }

        @PostMapping("/novo")
        public ResponseEntity<Produto> add(@RequestBody Produto produto) {

            return ResponseEntity.ok(produtoService.save(produto));
        }


        @PutMapping("/{id}")
        public ResponseEntity<Produto>update(@RequestBody Produto produto, @PathVariable int id){
            Produto produtoEncontrado = produtoService.findById(id);

            if (produtoEncontrado == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(produtoService.save(produto));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Produto>delete (@PathVariable int id){
            Produto produtoEncontrado = produtoService.findById(id);

            if (produtoEncontrado == null){
                return ResponseEntity.notFound().build();
            }
            produtoService.delete(produtoEncontrado);
            return ResponseEntity.ok(produtoEncontrado);

        }



    }
