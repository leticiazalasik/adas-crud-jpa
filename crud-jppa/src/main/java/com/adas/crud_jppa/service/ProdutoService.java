package com.adas.crud_jppa.service;

import com.adas.crud_jppa.model.Categoria;
import com.adas.crud_jppa.model.Produto;
import com.adas.crud_jppa.repository.CategoriaRepository;
import com.adas.crud_jppa.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscarTodos(){
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(int id){
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void excluir (Produto produto){
        produtoRepository.delete(produto);
    }

}
