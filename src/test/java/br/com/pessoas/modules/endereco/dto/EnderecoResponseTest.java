package br.com.pessoas.modules.endereco.dto;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

import static br.com.pessoas.modules.endereco.enums.ESituacao.PRIMARIA;
import static br.com.pessoas.modules.endereco.helper.EnderecoHelper.umEndereco;
import static br.com.pessoas.modules.endereco.helper.EnderecoHelper.umaListaEndereco;
import static org.assertj.core.api.Assertions.assertThat;

public class EnderecoResponseTest {

    @Test
    public void of_deveRetornarEnderecoResponse_quandoSolicitado() {
        assertThat(EnderecoResponse.of(umEndereco(1)))
                .extracting("logradouro", "cep", "numero", "cidade", "uf", "situacao", "pessoaId")
                .containsExactly("Rua das Flores", "86909-909", 123, "Londrina", "PR", PRIMARIA, 1);
    }

    @Test
    public void of_deveRetornarUmaListaEnderecoResponse_quandoSolicitado() {
        assertThat(EnderecoResponse.of(umaListaEndereco()))
                .extracting("logradouro", "cep", "numero", "cidade", "uf", "situacao", "pessoaId")
                .containsExactly(Tuple.tuple("Rua das Flores", "86909-909", 123, "Londrina", "PR", PRIMARIA, 1),
                        Tuple.tuple("Rua das Flores", "86909-909", 123, "Londrina", "PR", PRIMARIA, 1));
    }
}
