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

    public Caixa save(Caixa caixa) {
        return caixaRepository.save(caixa);
    }

    public List<Caixa> findAll(){
        return caixaRepository.findAll();
    }

    public Caixa findById(int id){
        return caixaRepository.findById(id).orElse(null);
    }


    public void delete (Caixa caixa){
        caixaRepository.delete(caixa);
    }


    }
