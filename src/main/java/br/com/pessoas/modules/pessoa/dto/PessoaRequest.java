package br.com.pessoas.modules.pessoa.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class PessoaRequest {

    @NotBlank
    private String nomeCompleto;
    @NotNull
    private LocalDate dataNascimento;
}
