package com.adas.crud_jppa.service;

import com.adas.crud_jppa.model.Caixa;
import com.adas.crud_jppa.model.Categoria;
import com.adas.crud_jppa.model.Produto;
import com.adas.crud_jppa.repository.CategoriaRepository;
import com.adas.crud_jppa.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;



    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto findById(int id){
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void delete (Produto produto){
        produtoRepository.delete(produto);
    }

public List<Produto> findByExactNome (String nome){
        return produtoRepository.findByExactNome(nome);
    }
    public List <Produto> findBySimilarNome (String nome){
        return produtoRepository.findBySimilarNome(nome);
    }

    public List <Produto> findByPreco (Double preco){
        return produtoRepository.findByPreco(preco);
    }
    public List <Produto> findByCategoria (Integer categoria){
        return produtoRepository.findByCategoria(categoria);
    }

    public List <Produto> findByCategoriaAtiva (Integer categoria){
        return produtoRepository.findByCategoria(categoria);
    }

    public List <Produto> findProdutosByCategoriaNome(String categoriaNome){
        return produtoRepository.findProdutosByCategoriaNome(categoriaNome);
    }
}
