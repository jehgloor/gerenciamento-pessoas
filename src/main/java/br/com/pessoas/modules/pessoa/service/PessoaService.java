package br.com.pessoas.modules.pessoa.service;

import br.com.pessoas.modules.comum.exception.NotFoundException;
import br.com.pessoas.modules.pessoa.dto.PessoaRequest;
import br.com.pessoas.modules.pessoa.model.Pessoa;
import br.com.pessoas.modules.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    public Pessoa buscarPorId(Integer id){
        return buscaPessoaPorId(id);
    }

    public Pessoa salvar(PessoaRequest request) {

        return repository.save(Pessoa.of(request));
    }

    public List<Pessoa> buscarTodos() {
        return repository.findAll();
    }

    public Pessoa editar(Integer id, PessoaRequest request) {
        var pessoa = buscaPessoaPorId(id);
        return repository.save(pessoa.of(request));
    }

    private Pessoa buscaPessoaPorId (Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("A Pessoa não foi encontrado."));
    }
}
