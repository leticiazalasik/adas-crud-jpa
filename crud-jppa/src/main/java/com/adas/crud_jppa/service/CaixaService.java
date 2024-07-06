package com.adas.crud_jppa.service;

import com.adas.crud_jppa.model.Caixa;
import com.adas.crud_jppa.model.Produto;
import com.adas.crud_jppa.repository.CaixaRepository;
import com.adas.crud_jppa.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository caixaRepository;

    List<Double> listaMovimentacao = new ArrayList<>();

    public Caixa salvar(Caixa caixa) {
        return caixaRepository.save(caixa);
    }

    public List<Caixa> buscarTodos(){
        return caixaRepository.findAll();
    }

    public Caixa buscarPorId(int id){
        return caixaRepository.findById(id).orElse(null);
    }


    public void excluir (Caixa caixa){
        caixaRepository.delete(caixa);
    }

//    public List<Double> registrarMovimentacao (Double valor){
//        listaMovimentacao.add(valor);
//        return listaMovimentacao;

    }
