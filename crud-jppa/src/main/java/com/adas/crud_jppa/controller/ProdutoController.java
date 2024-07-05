package com.adas.crud_jppa.controller;

import com.adas.crud_jppa.model.Categoria;
import com.adas.crud_jppa.model.Produto;
import com.adas.crud_jppa.service.CategoriaService;
import com.adas.crud_jppa.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
        private ProdutoService produtoService;

        @GetMapping("/todos")
        public ResponseEntity<List<Produto>> listarTodosProdutos() {

            return ResponseEntity.ok(produtoService.buscarTodos());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Produto>buscarPorId(@PathVariable int id){
            Produto produtoEncontrado=produtoService.buscarPorId(id);

            if(produtoEncontrado==null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(produtoEncontrado);
        }

        @PostMapping("/novo")
        public ResponseEntity<Produto> cadastrarNovoProduto(@RequestBody Produto produto) {

            return ResponseEntity.ok(produtoService.salvar(produto));
        }


        @PutMapping("/{id}")
        public ResponseEntity<Produto>alterar(@RequestBody Produto produto, @PathVariable int id){
            Produto produtoEncontrado = produtoService.buscarPorId(id);

            if (produtoEncontrado == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(produtoService.salvar(produto));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Produto> excluir (@PathVariable int id){
            Produto produtoEncontrado = produtoService.buscarPorId(id);

            if (produtoEncontrado == null){
                return ResponseEntity.notFound().build();
            }
            produtoService.excluir(produtoEncontrado);
            return ResponseEntity.ok(produtoEncontrado);

        }

    @PostMapping("/vender/{quantidade}")
    public ResponseEntity<Produto> venderProduto(@RequestBody int id, @PathVariable int quantidade) {

        Produto produtoEncontrado=produtoService.buscarPorId(id);

        if (produtoEncontrado==null){
            return ResponseEntity.notFound().build();
        }

        if (produtoEncontrado.getQuantidade()<quantidade ) {
            return ResponseEntity.notFound().build();

        }
    produtoEncontrado.setQuantidade(produtoEncontrado.getQuantidade()-quantidade);
        return ResponseEntity.ok(produtoService.salvar(produtoEncontrado));
    }

    @PostMapping("/comprar/{quantidade}")
    public ResponseEntity<Produto> comprarProduto(@RequestBody int id, @PathVariable int quantidade) {

        Produto produtoEncontrado=produtoService.buscarPorId(id);

        if(produtoEncontrado==null){
            return ResponseEntity.notFound().build();
        }

        produtoEncontrado.setQuantidade(produtoEncontrado.getQuantidade()+quantidade);
        return ResponseEntity.ok(produtoService.salvar(produtoEncontrado));
    }
    }
