package br.com.pessoas.modules.endereco.dto;

import br.com.pessoas.modules.endereco.enums.ESituacao;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class EnderecoRequest {

    private String logradouro;
    private String cep;
    private Integer numero;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;
    @NotNull
    private ESituacao situacao;
    @NotNull
    private Integer pessoaId;
}
