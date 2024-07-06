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

    @Autowired
    private CaixaService caixaService;

    private List<Double> entradas = new ArrayList<>();


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

    @PostMapping("/vender/{quantidade}/{id}")
    public ResponseEntity<Produto> venderProduto(@RequestBody  int idCaixa, @PathVariable int quantidade, @PathVariable  int id) {

        Produto produtoEncontrado=produtoService.buscarPorId(id);

        if (produtoEncontrado==null){
            return ResponseEntity.notFound().build();
        }

        if (produtoEncontrado.getQuantidade()<quantidade ) {
            return ResponseEntity.notFound().build();

        }
    produtoEncontrado.setQuantidade(produtoEncontrado.getQuantidade()-quantidade);
        double valorVenda= produtoEncontrado.getPreco()*quantidade;

        Caixa caixaEncontrado =caixaService.buscarPorId(idCaixa);
        if (caixaEncontrado==null){
            return ResponseEntity.notFound().build();

        }
        if(caixaEncontrado.isStatus()==false){
            return ResponseEntity.notFound().build();

        }
        caixaEncontrado.setSaldo(caixaEncontrado.getSaldo()+valorVenda);
        caixaService.salvar(caixaEncontrado);
        //caixaService.registrarMovimentacao(valorVenda);

        return ResponseEntity.ok(produtoService.salvar(produtoEncontrado));
    }

    @PostMapping("/comprar/{quantidade}/{id}")
    public ResponseEntity<Produto> comprarProduto(@RequestBody int idCaixa, @PathVariable int quantidade, @PathVariable  int id) {

        Produto produtoEncontrado=produtoService.buscarPorId(id);

        if (produtoEncontrado==null){
            return ResponseEntity.notFound().build();
        }

        Caixa caixaEncontrado =caixaService.buscarPorId(idCaixa);
        if (caixaEncontrado==null){
            return ResponseEntity.notFound().build();

        }
        if(caixaEncontrado.isStatus()==false){
            return ResponseEntity.notFound().build();

        }
        produtoEncontrado.setQuantidade(produtoEncontrado.getQuantidade()+quantidade);
        double valorCompra= produtoEncontrado.getPreco()*quantidade;

        caixaEncontrado.setSaldo(caixaEncontrado.getSaldo()-valorCompra);
        caixaService.salvar(caixaEncontrado);

        return ResponseEntity.ok(produtoService.salvar(produtoEncontrado));
    }


    }
