package com.adas.crud_jppa.controller;

import com.adas.crud_jppa.model.Historico;
import com.adas.crud_jppa.model.Produto;
import com.adas.crud_jppa.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoController {
    @Autowired
    private HistoricoService historicoService;

    @GetMapping("/todos")
    public ResponseEntity<List<Historico>> listarTodosHistoricos() {

        return ResponseEntity.ok(historicoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historico>buscarPorId(@PathVariable int id){
        Historico historicoEncontrado=historicoService.buscarPorId(id);

        if(historicoEncontrado==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(historicoEncontrado);
    }

    @PostMapping("/novo")
    public ResponseEntity<Historico> cadastrarNovoHistorico(@RequestBody Historico historico) {
historico.setData(LocalDateTime.now());
        return ResponseEntity.ok(historicoService.salvar(historico));
    }

 //   @PostMapping("vender-produtos")
   // public ResponseEntity<String>registrarVenda(@RequestBody Historico historico) {
//}

    @PutMapping("/{id}")
    public ResponseEntity<Historico>alterar(@RequestBody Historico historico, @PathVariable int id){
        Historico historicoEncontrado = historicoService.buscarPorId(id);

        if (historicoEncontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(historicoService.salvar(historico));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Historico> excluir (@PathVariable int id){
        Historico historicoEncontrado = historicoService.buscarPorId(id);

        if (historicoEncontrado == null){
            return ResponseEntity.notFound().build();
        }
        historicoService.excluir(historicoEncontrado);
        return ResponseEntity.ok(historicoEncontrado);

    }

    @GetMapping("/todos/relatorio")
    public ResponseEntity<List<Object[]>> findAll() {
        return ResponseEntity.ok(historicoService.buscarDetalhesDeTodosOsHistoricos());
    }
}
