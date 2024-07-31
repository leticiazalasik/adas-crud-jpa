package com.adas.crud_jppa.controller;

import com.adas.crud_jppa.model.Categoria;
import com.adas.crud_jppa.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/todas")
    public ResponseEntity<List<Categoria>> listarTodasCategorias() {

        return ResponseEntity.ok(categoriaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable int id) {
        Categoria categoriaEncontrada = categoriaService.buscarPorId(id);

        if (categoriaEncontrada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriaEncontrada);
    }

    @PostMapping("/nova")
    public ResponseEntity<Categoria> cadastrarNovaCategoria(@RequestBody Categoria categoria) {

        return ResponseEntity.ok(categoriaService.salvar(categoria));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Categoria> alterar(@RequestBody Categoria categoria, @PathVariable int id) {
        Categoria categoriaEncontrada = categoriaService.buscarPorId(id);

        if (categoriaEncontrada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriaService.salvar(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> excluir(@PathVariable int id) {
        Categoria categoriaEncontrada = categoriaService.buscarPorId(id);

        if (categoriaEncontrada == null) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.excluir(categoriaEncontrada);
        return ResponseEntity.ok(categoriaEncontrada);

    }

    @GetMapping("nome/{nome}")
    public List<Categoria> findByNome(@PathVariable String nome) {
        return categoriaService.findByNome(nome);
    }

    @GetMapping("nome-similar/{nome}")
    public List<Categoria> findByNomeContainingIgnoreCase(@PathVariable String nome) {
        return categoriaService.findByNomeContaining(nome);
    }

    @GetMapping("categoria-ativa")
    public List<Categoria> findByStatusTrue() {
        return categoriaService.findByStatusTrue();

    }


    @PutMapping("/update-status/{id}/{status}")
    public ResponseEntity<Integer> updateCategoriaById(@PathVariable int id, @PathVariable Boolean status){

        return ResponseEntity.ok(categoriaService.updateCategoriaById(id, status));
    }

}