package br.com.pessoas.modules.pessoa.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PessoaRequest {

    @NotNull
    private String nomeCompleto;
    @NotNull
    private LocalDate dataNascimento;
}
