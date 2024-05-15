package br.com.pessoas.modules.endereco.model;

import org.junit.jupiter.api.Test;

import static br.com.pessoas.modules.endereco.enums.ESituacao.PRIMARIA;
import static br.com.pessoas.modules.endereco.helper.EnderecoHelper.umEndereco;
import static br.com.pessoas.modules.endereco.helper.EnderecoHelper.umEnderecoRequest;
import static br.com.pessoas.modules.pessoa.helper.PessoaHelper.umaPessoa;
import static org.assertj.core.api.Assertions.assertThat;

public class EnderecoTest {

    @Test
    public void of_deveRetornarEndereco_quandoSolicitado() {
        assertThat(Endereco.of(umEnderecoRequest(), umaPessoa()))
                .extracting("logradouro", "cep", "numero", "cidade", "uf", "situacao",
                        "pessoa.id")
                .containsExactly("Rua das Flores", "86909-909", 123, "Londrina", "PR", PRIMARIA, 1);
    }

    @Test
    public void setAll_deveAtualizarEndereco_quandoSolicitado() {
        var endereco = umEndereco(1);
        assertThat(endereco)
                .extracting("logradouro", "cep", "numero", "cidade", "uf", "situacao", "pessoa.id")
                .containsExactly("Rua das Flores", "86909-909", 123, "Londrina", "PR", PRIMARIA, 1);

        var request = umEnderecoRequest();
        request.setLogradouro("Rua Guargulio");
        endereco.setAll(request);

        assertThat(endereco)
                .extracting("logradouro", "cep", "numero", "cidade", "uf", "situacao", "pessoa.id")
                .containsExactly("Rua Guargulio", "86909-909", 123, "Londrina", "PR", PRIMARIA, 1);
    }
}
