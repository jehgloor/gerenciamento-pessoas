package br.com.pessoas.modules.endereco.controller;

import br.com.pessoas.modules.endereco.dto.EnderecoRequest;
import br.com.pessoas.modules.endereco.dto.EnderecoResponse;
import br.com.pessoas.modules.endereco.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @GetMapping("/{id}")
    public EnderecoResponse buscaPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoResponse salvarEndereco(@Validated @RequestBody EnderecoRequest request) {
        return service.salvarEndereco(request);
    }

    @GetMapping
    public List<EnderecoResponse> buscarTodos() {
        return service.buscarTodos();
    }

    @PutMapping("/{idEndereco}")
    public EnderecoResponse editarEndereco(@PathVariable Integer idEndereco, @Validated @RequestBody EnderecoRequest request) {
        return service.editarEndereco(idEndereco, request);
    }

}
