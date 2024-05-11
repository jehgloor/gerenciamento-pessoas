package br.com.pessoas.modules.pessoa.dto;

import br.com.pessoas.modules.pessoa.model.Pessoa;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaResponse {
    private String nomeCompleto;
    private LocalDate dataNascimento;

    public static PessoaResponse of(Pessoa pessoa) {
        var response = new PessoaResponse();
        response.setNomeCompleto(pessoa.getNomeCompleto());
        response.setDataNascimento(pessoa.getDataNascimento());
        return response;
    }

}
