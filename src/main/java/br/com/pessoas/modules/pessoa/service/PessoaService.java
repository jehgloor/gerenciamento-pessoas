package br.com.pessoas.modules.pessoa.service;

import br.com.pessoas.modules.pessoa.model.Pessoa;
import br.com.pessoas.modules.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    public Optional<Pessoa> getById(){
        return repository.findById(1);
    }
}
