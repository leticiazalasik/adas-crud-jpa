package com.adas.crud_jppa.controller;

import com.adas.crud_jppa.model.Caixa;
import com.adas.crud_jppa.model.Produto;
import com.adas.crud_jppa.repository.CaixaRepository;
import com.adas.crud_jppa.service.CaixaService;
import com.adas.crud_jppa.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    private CaixaService caixaService;

    @Autowired
    private CaixaRepository caixaRepository;

    private List<Double>entradas=new ArrayList<>();

    public void entradas (double valor){
        entradas.add(valor);
    }

    @GetMapping("/entradas")
    public List<Double>getEntradas(){
        return entradas;
    }

    @PostMapping("/novo")
    public ResponseEntity<Caixa> cadastrarNovoCaixa(@RequestBody Caixa caixa) {

        return ResponseEntity.ok(caixaService.salvar(caixa));
    }
        @GetMapping("/todos")
        public ResponseEntity<List<Caixa>> listarTodosCaixas() {

            return ResponseEntity.ok(caixaService.buscarTodos());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Caixa>buscarPorId(@PathVariable int id) {
            Caixa caixaEncontrado = caixaService.buscarPorId(id);

            if (caixaEncontrado == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(caixaEncontrado);
        }

    @PutMapping("/{id}")
    public ResponseEntity<Caixa>alterar(@RequestBody Caixa caixa, @PathVariable int id){
        Caixa caixaEncontrado = caixaService.buscarPorId(id);

        if (caixaEncontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(caixaService.salvar(caixa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Caixa> excluir (@PathVariable int id){
        Caixa caixaEncontrado = caixaService.buscarPorId(id);

        if (caixaEncontrado == null){
            return ResponseEntity.notFound().build();
        }
        caixaService.excluir(caixaEncontrado);
        return ResponseEntity.ok(caixaEncontrado);

    }
    @PutMapping("/abrir")
    public ResponseEntity<Caixa>abrirCaixa(@RequestBody int id){

        Caixa caixaEncontrado =caixaService.buscarPorId(id);

        if (caixaEncontrado == null){
            return ResponseEntity.notFound().build();
        }

        if (caixaEncontrado.isStatus() == true){
            return ResponseEntity.notFound().build();
        }
        caixaEncontrado.setStatus(true);
        return ResponseEntity.ok(caixaService.salvar(caixaEncontrado));
    }

    @PutMapping("/fechar")
    public ResponseEntity<Caixa>fecharCaixa(@RequestBody int id ){

        Caixa caixaEncontrado =caixaService.buscarPorId(id);

        if (caixaEncontrado == null){
            return ResponseEntity.notFound().build();
        }

        if (caixaEncontrado.isStatus() == false){
            return ResponseEntity.notFound().build();
        }
        caixaEncontrado.setStatus(false);
        return ResponseEntity.ok(caixaService.salvar(caixaEncontrado));
    }




}


