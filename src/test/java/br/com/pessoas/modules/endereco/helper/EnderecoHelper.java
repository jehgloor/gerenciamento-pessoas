package br.com.pessoas.modules.endereco.helper;

import br.com.pessoas.modules.endereco.dto.EnderecoRequest;
import br.com.pessoas.modules.endereco.enums.ESituacao;
import br.com.pessoas.modules.endereco.model.Endereco;

public class EnderecoHelper {
    public static EnderecoRequest umEnderecoRequest() {
        return EnderecoRequest.builder()
                .cep("86909-909")
                .uf("PR")
                .cidade("Londrina")
                .numero(123)
                .logradouro("Rua das Flores")
                .pessoaId(1)
                .situacao(ESituacao.PRIMARIA)
                .build();
    }
}
