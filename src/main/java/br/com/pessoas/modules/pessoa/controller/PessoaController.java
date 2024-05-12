package br.com.pessoas.modules.pessoa.controller;

import br.com.pessoas.modules.pessoa.dto.PessoaRequest;
import br.com.pessoas.modules.pessoa.dto.PessoaResponse;
import br.com.pessoas.modules.pessoa.model.Pessoa;
import br.com.pessoas.modules.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService service;

    @GetMapping("/{id}")
    public PessoaResponse buscaPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @GetMapping
    public List<Pessoa> buscarTodos() {
        return service.buscarTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaResponse salvar(@RequestBody @Validated PessoaRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/id")
    public Pessoa editar (@PathVariable Integer id, @Validated @RequestBody PessoaRequest request) {
        return service.editar(id, request);
    }
}
