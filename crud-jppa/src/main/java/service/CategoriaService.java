package service;

import model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CategoriaRepository;

import java.util.List;

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

}
