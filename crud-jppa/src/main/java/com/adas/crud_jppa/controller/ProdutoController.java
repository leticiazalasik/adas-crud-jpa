package com.adas.crud_jppa.controller;

import com.adas.crud_jppa.model.Caixa;
import com.adas.crud_jppa.model.Categoria;
import com.adas.crud_jppa.model.Historico;
import com.adas.crud_jppa.model.Produto;
import com.adas.crud_jppa.service.CaixaService;
import com.adas.crud_jppa.service.HistoricoService;

import com.adas.crud_jppa.service.CategoriaService;
import com.adas.crud_jppa.service.ProdutoService;
import jakarta.persistence.EntityManager;
import org.antlr.v4.runtime.misc.LogManager;
import org.apache.coyote.Response;
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

    @Autowired
    CategoriaService categoriaService;


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


    @PutMapping("/vender/lista/{idCliente}/{idCaixa}")
    public ResponseEntity<String> venderLista(@RequestBody List<Produto> produtos,
                                              @PathVariable int idCliente) {


        historicoService.registrarVenda(idCliente, produtos);
        return ResponseEntity.ok("Venda registrada com sucesso!");


    }

    @PutMapping("/vender/{quantidade}/{idCaixa}")
    public ResponseEntity<String> vender(@RequestBody Produto produto,
                                         @PathVariable int quantidade,
                                         @PathVariable int idCaixa) {

        // Verificar se o caixa está ativo
        Caixa caixa = caixaService.findById(idCaixa);
        if (!caixa.isStatus()) {
            return ResponseEntity.ok("O caixa de código "+ idCaixa + " está fechado! Nâo é possível realizar venda.");
        }

        // Busca o objeto atualizado no banco de dados com base no id informado no corpo da requisição
        Produto produtoAtual = produtoService.findById(produto.getId());

        // Validar a quantidade de estoque para realizar ou não a venda do produto
        if (produtoAtual.getQuantidade() < quantidade) {
            return ResponseEntity.ok("Estoque insulficiente para venda do produto.");
        }

        // Atualizando a quantidade do produto, subtraindo com base na quantidade recebida na requisição
        produtoAtual.setQuantidade(produtoAtual.getQuantidade() - quantidade);
        produtoService.save(produtoAtual);

        // Descobrir o valor total da venda
        Double totalVenda = quantidade * produtoAtual.getPreco();

        //registrar entrada
        caixa = caixaService.realizarMovimentacao(idCaixa, totalVenda, "entrada");
        // Atualizar o saldo do caixa
        caixa = caixaService.realizarMovimentacao(idCaixa, totalVenda, "ENTRADA");

        // Concatenando os valores para montar um recibo da movimentação, tanto no produto quanto no caixa
        String recibo = " " +
                "Produto vendido: " + produtoAtual.getNome() +
                "\nTotal da venda: R$ " + totalVenda +
                "\nCaixa atualizado: " + idCaixa +
                "\nSaldo atual do caixa: R$ " + caixa.getSaldo();

        return ResponseEntity.ok(recibo);
    }

    @PutMapping("/comprar/{quantidade}/{idCaixa}")
    public ResponseEntity<String> comprar(@RequestBody Produto produto,
                                          @PathVariable int quantidade,
                                          @PathVariable int idCaixa) {

        Caixa caixa = caixaService.findById(idCaixa);
        if (!caixa.isStatus()) {
            return ResponseEntity.ok("O caixa de código "+ idCaixa + " está fechado! Nâo é possível realizar compra.");
        }

        Produto produtoAtual = produtoService.findById(produto.getId());

        Double totalCompra = quantidade * produtoAtual.getPreco();

        //registrar saida no caixa
        caixa = caixaService.realizarMovimentacao(idCaixa, totalCompra, "saida");

        // Verificar se existe saldo disponível no caixa para realizar a compra
        if (caixa.getSaldo() < totalCompra) {
            return ResponseEntity.ok("Saldo insulficiente para realizar a compra!");
        }

        produtoAtual.setQuantidade(produtoAtual.getQuantidade() + quantidade);
        produtoService.save(produtoAtual);

        caixa = caixaService.realizarMovimentacao(idCaixa, totalCompra, "SAIDA");

        String recibo = " " +
                "Produto comprado: " + produtoAtual.getNome() +
                "\nTotal da compra: R$ " + totalCompra +
                "\nCaixa atualizado: " + idCaixa +
                "\nSaldo atual do caixa: R$ " + caixa.getSaldo();

        return ResponseEntity.ok(recibo);

    }

    @GetMapping("/nome-exato/{nome}")
    public ResponseEntity<List<Produto>>findByExactNome(@PathVariable String nome){
        return ResponseEntity.ok(produtoService.findByExactNome(nome));    }

@GetMapping("/nome-similar/{nome}")
public ResponseEntity<List<Produto>>findBySimilarNome(@PathVariable String nome){
    return ResponseEntity.ok(produtoService.findBySimilarNome(nome));
}

    @GetMapping("/preco/{preco}")
    public ResponseEntity<List<Produto>>findByPreco(@PathVariable Double preco){
        return ResponseEntity.ok(produtoService.findByPreco(preco));
    }

    @GetMapping("/produtos-categoria/{categoria}")
    public ResponseEntity<List<Produto>>findByCategoria(@PathVariable Integer categoria){
        return ResponseEntity.ok(produtoService.findByCategoria(categoria));
    }

    @GetMapping("/categoria-ativa/{categoria}")
    public ResponseEntity<?>findByCategoriaAtiva(@PathVariable Integer categoria) {
        Categoria categoriaEncontrada = categoriaService.buscarPorId(categoria);

        //Validando se a categoria não existe
        if (categoriaEncontrada == null) {
            return ResponseEntity.status(500).body("Categoria não encontrada para o código " + categoria);
        }
        //Validando se o status da categoria é false
        if (categoriaEncontrada.isStatus() == true) {
            return ResponseEntity.status(500).body("Categoria " + categoriaEncontrada.getNome() + " está inativa!");
        }
        //Retornar a lista de produtos pela categoria ativa
        return ResponseEntity.ok(produtoService.findByCategoria(categoria));
    }

}
