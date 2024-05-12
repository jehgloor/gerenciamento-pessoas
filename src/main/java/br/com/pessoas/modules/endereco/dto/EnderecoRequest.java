package br.com.pessoas.modules.endereco.dto;

import br.com.pessoas.modules.endereco.enums.ESituacao;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EnderecoRequest {


    private String logradouro;

    private String cep;

    private Integer numero;

    private String cidade;

    private String uf;

    private ESituacao situacao;
@NotNull
    private Integer pessoaId;
}
