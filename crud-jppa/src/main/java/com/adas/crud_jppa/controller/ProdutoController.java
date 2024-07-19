package com.adas.crud_jppa.controller;

import com.adas.crud_jppa.model.Caixa;
import com.adas.crud_jppa.model.Categoria;
import com.adas.crud_jppa.model.Historico;
import com.adas.crud_jppa.model.Produto;
import com.adas.crud_jppa.service.CaixaService;
import com.adas.crud_jppa.service.HistoricoService;

import com.adas.crud_jppa.service.CategoriaService;
import com.adas.crud_jppa.service.ProdutoService;
import org.antlr.v4.runtime.misc.LogManager;
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
   CaixaService caixaService;

    @Autowired
    HistoricoService historicoService;

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


    @PostMapping("/comprar/{quantidade}/{idCaixa}")
    public ResponseEntity<String> comprarProduto(@RequestBody  Produto produto, @PathVariable int quantidade, @PathVariable  int idCaixa) {

        //Busca o objeto atualizado no banco de dados com base no id informado no corpo da requisiçao

        Caixa caixa = caixaService.findById(idCaixa);
        if (!caixa.isStatus()) {
            return ResponseEntity.ok("Não foi possível realizar a compra pois o caixa "+ idCaixa + " está fechado.");

        }

        Produto produtoAtual = produtoService.findById(produto.getId());
        //Valor compra
        double valorCompra= produto.getPreco()*quantidade;

        if (caixa.getSaldo()<valorCompra){
            return ResponseEntity.ok("Saldo insufieciente para compra.");
        }

        produtoAtual.setQuantidade(produtoAtual.getQuantidade()+quantidade);
        produtoService.save(produtoAtual);

        //atualizar o saldo do caixa
        caixa = caixaService.realizarMovimentacao(idCaixa, valorCompra, "saida");

        String recibo = "" +
                "Produto comprado: "+ produtoAtual.getNome() +
                "\n Valor Total da compra: R$ " + valorCompra +
                "\n Caixa atualizado: " + idCaixa +
                "\n Saldo atual do caixa: " + caixa.getSaldo();
        return ResponseEntity.ok(recibo);
    }

    @PostMapping("/vender/{quantidade}/{idCaixa}")
    public ResponseEntity<String> venderProduto(@RequestBody  Produto produto, @PathVariable int quantidade, @PathVariable  int idCaixa) {


        //Busca o objeto atualizado no banco de dados com base no id informado no corpo da requisiçao
        Produto produtoAtual = produtoService.findById(produto.getId());
        Caixa caixa = caixaService.findById(idCaixa);
        if (!caixa.isStatus()) {
            return ResponseEntity.ok("Não foi possível realizar a venda pois o caixa "+ idCaixa + " está fechado.");


        }
        if (produtoAtual.getQuantidade()<quantidade ) {
            return ResponseEntity.ok("Estoque insuficiente para venda.");
        }
        produtoAtual.setQuantidade(produtoAtual.getQuantidade()-quantidade);
        produtoService.save(produtoAtual);


        //Valor entrada
        double valorVenda= produto.getPreco()*quantidade;


        //atualizar o saldo do caixa
        caixa = caixaService.realizarMovimentacao(idCaixa, valorVenda, "entrada");


        String recibo = "" +
                "Produto vendido: "+ produtoAtual.getNome() +
                "\n Valor Total da venda: R$ " + valorVenda +
                "\n Caixa atualizado: " + idCaixa +
                "\n Saldo atual do caixa: " + caixa.getSaldo();
        return ResponseEntity.ok(recibo);
    }

}
