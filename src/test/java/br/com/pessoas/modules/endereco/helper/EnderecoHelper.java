package br.com.pessoas.modules.endereco.helper;

import br.com.pessoas.modules.endereco.dto.EnderecoRequest;
import br.com.pessoas.modules.endereco.dto.EnderecoResponse;
import br.com.pessoas.modules.endereco.enums.ESituacao;
import br.com.pessoas.modules.endereco.model.Endereco;

import java.util.List;

import static br.com.pessoas.modules.pessoa.helper.PessoaHelper.umaPessoa;

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

    public static Endereco umEndereco(Integer id) {
        return Endereco.builder()
                .id(id)
                .cep("86909-909")
                .uf("PR")
                .cidade("Londrina")
                .numero(123)
                .logradouro("Rua das Flores")
                .pessoa(umaPessoa())
                .situacao(ESituacao.PRIMARIA)
                .build();
    }

    public static List<Endereco> umaListaEndereco() {
        return List.of(umEndereco(1), umEndereco(2));
    }

    public static EnderecoResponse umEnderecoResponse() {
        return EnderecoResponse.builder()
                .cep("86909-909")
                .uf("SP")
                .cidade("São Paulo")
                .numero(123)
                .logradouro("Rua das rosas")
                .pessoaId(1)
                .situacao(ESituacao.PRIMARIA)
                .build();
    }

}
