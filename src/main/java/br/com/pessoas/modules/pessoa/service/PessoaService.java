package br.com.pessoas.modules.pessoa.service;

import br.com.pessoas.modules.comum.exception.NotFoundException;
import br.com.pessoas.modules.pessoa.dto.PessoaRequest;
import br.com.pessoas.modules.pessoa.dto.PessoaResponse;
import br.com.pessoas.modules.pessoa.model.Pessoa;
import br.com.pessoas.modules.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    public PessoaResponse buscarPorId(Integer id){
        return PessoaResponse.of(buscaPessoaPorId(id));
    }

    public PessoaResponse salvar(PessoaRequest request) {
        return PessoaResponse.of(repository.save(Pessoa.of(request)));
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
