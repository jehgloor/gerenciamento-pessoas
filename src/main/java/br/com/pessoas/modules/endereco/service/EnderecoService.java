package br.com.pessoas.modules.endereco.service;

import br.com.pessoas.modules.comum.exception.NotFoundException;
import br.com.pessoas.modules.endereco.dto.EnderecoRequest;
import br.com.pessoas.modules.endereco.dto.EnderecoResponse;
import br.com.pessoas.modules.endereco.enums.ESituacao;
import br.com.pessoas.modules.endereco.model.Endereco;
import br.com.pessoas.modules.endereco.repository.EnderecoRepository;
import br.com.pessoas.modules.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private PessoaService pessoaService;


    public EnderecoResponse buscarPorId(Integer id) {
        return EnderecoResponse.of(buscaEnderecoPorId(id));
    }

    public EnderecoResponse salvarEndereco(EnderecoRequest request) {

        var pessoa = pessoaService.buscaPessoaPorId(request.getPessoaId());
        if(request.getSituacao() == ESituacao.PRIMARIA) {
            repository.atualizaEnderecoParaSecundario(request.getPessoaId());
        }
        return EnderecoResponse.of(repository.save(Endereco.of(request, pessoa)));
    }

    private Endereco buscaEnderecoPorId(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("O Endereço não foi encontrado."));
    }

    public List<Endereco> buscarTodos() {
        return repository.findAll();
    }
}
