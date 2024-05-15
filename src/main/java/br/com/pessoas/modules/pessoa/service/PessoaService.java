package br.com.pessoas.modules.pessoa.service;

import br.com.pessoas.modules.comum.exception.NotFoundException;
import br.com.pessoas.modules.pessoa.dto.PessoaRequest;
import br.com.pessoas.modules.pessoa.dto.PessoaResponse;
import br.com.pessoas.modules.pessoa.model.Pessoa;
import br.com.pessoas.modules.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    public PessoaResponse buscarPorId(Integer id) {
        return PessoaResponse.of(buscaPessoaPorId(id));
    }

    @Transactional
    public PessoaResponse salvar(PessoaRequest request) {
        return PessoaResponse.of(repository.save(Pessoa.of(request)));
    }

    public List<PessoaResponse> buscarTodos() {

        return PessoaResponse.of(repository.findAll());
    }

    @Transactional
    public PessoaResponse editar(Integer id, PessoaRequest request) {
        var pessoa = buscaPessoaPorId(id);
        pessoa.setAll(request);
        repository.save(pessoa);
        return PessoaResponse.of(pessoa);
    }

    public Pessoa buscaPessoaPorId(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("A Pessoa não foi encontrado."));
    }
}
