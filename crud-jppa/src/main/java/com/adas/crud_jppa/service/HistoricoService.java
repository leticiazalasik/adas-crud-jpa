package com.adas.crud_jppa.service;

import com.adas.crud_jppa.model.Cliente;
import com.adas.crud_jppa.model.Historico;
import com.adas.crud_jppa.model.Produto;
import com.adas.crud_jppa.repository.ClienteRepository;
import com.adas.crud_jppa.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private ClienteService clienteService;

    public List<Historico> buscarTodos() {
        return historicoRepository.findAll();
    }

    public Historico buscarPorId(int id) {
        return historicoRepository.findById(id).orElse(null);
    }

    public Historico salvar(Historico historico) {
        return historicoRepository.save(historico);
    }

    public void excluir(Historico historico) {
        historicoRepository.delete(historico);
    }

    public void registrarVenda(Integer idCliente, List<Produto> produtos){

        Cliente cliente = clienteService.buscarPorId(idCliente);


        //Declarandi um objeto da classe histórico com base em um construtor
        // sob demanda, feito através da anotaçao 'Builder'
        Historico historico = Historico
                .builder()
                .data(LocalDateTime.now())
                .cliente(cliente)
                .produtosHistorico(produtos)
                .build();

        this.salvar(historico);
    }

    public List<Object[]>buscarDetalhesDeTodosOsHistoricos(){
        return historicoRepository.buscarDetalhesDeTodosOsHistoricos();
    }
}
