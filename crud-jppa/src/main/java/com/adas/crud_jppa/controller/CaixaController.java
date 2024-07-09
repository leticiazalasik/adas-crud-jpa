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

    @PostMapping("/novo")
    public ResponseEntity<Caixa> add(@RequestBody Caixa caixa) {

        return ResponseEntity.ok(caixaService.save(caixa));
    }
        @GetMapping("/todos")
        public ResponseEntity<List<Caixa>> findAll() {

            return ResponseEntity.ok(caixaService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Caixa>findById(@PathVariable int id) {
            Caixa caixaEncontrado = caixaService.findById(id);

            if (caixaEncontrado == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(caixaEncontrado);
        }

    @PutMapping("/{id}")
    public ResponseEntity<Caixa>update(@RequestBody Caixa caixa, @PathVariable int id){
        Caixa caixaEncontrado = caixaService.findById(id);

        if (caixaEncontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(caixaService.save(caixa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Caixa> excluir (@PathVariable int id){
        Caixa caixaEncontrado = caixaService.findById(id);

        if (caixaEncontrado == null){
            return ResponseEntity.notFound().build();
        }
        caixaService.delete(caixaEncontrado);
        return ResponseEntity.ok(caixaEncontrado);

    }
    @PutMapping("/abrir/{id}")
    public ResponseEntity<String>abrirCaixa(@PathVariable int id){

        Caixa caixaEncontrado =caixaService.findById(id);

        if (caixaEncontrado == null){
            return ResponseEntity.notFound().build();
        }

        if (caixaEncontrado.isStatus() == true){
            return ResponseEntity.badRequest().body("O caixa "+ id +" já está aberto!");
        }
        caixaEncontrado.setStatus(true);
        caixaService.save(caixaEncontrado);
        return ResponseEntity.ok("O caixa "+ id +" está aberto!");
    }

    @PutMapping("/fechar/{id}")
    public ResponseEntity<String>fecharCaixa(@PathVariable int id ){

        Caixa caixaEncontrado =caixaService.findById(id);

        if (caixaEncontrado == null){
            return ResponseEntity.notFound().build();
        }

        if (caixaEncontrado.isStatus() == false){
            return ResponseEntity.badRequest().body("O caixa "+ id +" já está fechado!");
        }
        caixaEncontrado.setStatus(false);
        caixaService.save(caixaEncontrado);
        return ResponseEntity.ok("O caixa "+ id +" está aberto!");
    }




}


