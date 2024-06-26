package br.com.pessoas.modules.endereco.dto;

import br.com.pessoas.modules.endereco.enums.ESituacao;
import br.com.pessoas.modules.endereco.model.Endereco;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EnderecoResponse {

    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;
    private String uf;
    private ESituacao situacao;
    private Integer pessoaId;

    public static EnderecoResponse of(Endereco endereco) {
        return EnderecoResponse.builder()
                .logradouro(endereco.getLogradouro())
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .uf(endereco.getUf())
                .situacao(endereco.getSituacao())
                .pessoaId(endereco.getPessoa().getId())
                .build();

    }

    public static List<EnderecoResponse> of(List<Endereco> enderecos) {
        return enderecos.stream().map(endereco -> EnderecoResponse.of(endereco)).toList();
    }
}
