package br.com.pessoas.modules.pessoa.dto;

import br.com.pessoas.modules.pessoa.model.Pessoa;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PessoaResponse {

    private String nomeCompleto;
    private LocalDate dataNascimento;

    public static PessoaResponse of(Pessoa pessoa) {
        return PessoaResponse.builder()
                .nomeCompleto(pessoa.getNomeCompleto())
                .dataNascimento(pessoa.getDataNascimento())
                .build();
    }

    public static List<PessoaResponse> of(List<Pessoa> pessoas) {
        return pessoas.stream().map(pessoa -> PessoaResponse.of(pessoa)).toList();
    }
}
