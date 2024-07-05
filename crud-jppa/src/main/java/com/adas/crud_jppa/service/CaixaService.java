package com.adas.crud_jppa.service;

import com.adas.crud_jppa.model.Caixa;
import com.adas.crud_jppa.repository.CaixaRepository;
import com.adas.crud_jppa.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository caixaRepository;

    public Caixa salvar(Caixa caixa) {
        return caixaRepository.save(caixa);
    }


}
