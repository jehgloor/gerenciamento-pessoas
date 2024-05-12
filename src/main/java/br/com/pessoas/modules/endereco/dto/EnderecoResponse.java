package br.com.pessoas.modules.endereco.dto;

import br.com.pessoas.modules.endereco.enums.ESituacao;
import br.com.pessoas.modules.endereco.model.Endereco;
import lombok.Data;

@Data
public class EnderecoResponse {

    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;
    private String uf;
    private ESituacao situacao;
    private Integer pessoaId;

    public static EnderecoResponse of(Endereco endereco) {
        var response = new EnderecoResponse();
        response.setLogradouro(endereco.getLogradouro());
        response.setCep(endereco.getCep());
        response.setNumero(endereco.getNumero());
        response.setCidade(endereco.getCidade());
        response.setUf(endereco.getUf());
        response.setPessoaId(endereco.getPessoa().getId());
        return response;
    }
}
