package com.adas.crud_jppa.service;

import com.adas.crud_jppa.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adas.crud_jppa.repository.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria>buscarTodos(){
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(int id){
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void excluir (Categoria categoria){
        categoriaRepository.delete(categoria);
    }

    public List<Categoria>findByNome(String nome){
        return categoriaRepository.findByNome(nome);
    }

    public List<Categoria>findByNomeContaining(String nome){
        return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Categoria> findByStatusTrue(){
        return categoriaRepository.findByStatusTrue();
    }

    public int updateCategoriaById (int id, Boolean status) {
        return categoriaRepository.updateCategoriaById(id, status);
    }


}
