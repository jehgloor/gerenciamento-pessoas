package br.com.pessoas.modules.pessoa.model;

import br.com.pessoas.modules.endereco.model.Endereco;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static br.com.pessoas.modules.endereco.enums.ESituacao.PRIMARIA;
import static br.com.pessoas.modules.endereco.helper.EnderecoHelper.umEndereco;
import static br.com.pessoas.modules.endereco.helper.EnderecoHelper.umEnderecoRequest;
import static br.com.pessoas.modules.pessoa.helper.PessoaHelper.umaPessoa;
import static br.com.pessoas.modules.pessoa.helper.PessoaHelper.umaPessoaRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class PessoaTest {

    @Test
    public void of_deveRetornarPessoa_quandoSolicitado() {
        assertThat(Pessoa.of(umaPessoaRequest()))
                .extracting("nomeCompleto", "dataNascimento")
                .containsExactly("Maria de Jesus", LocalDate.of(2000, 8, 01));
    }

    @Test
    public void setAll_deveAtualizarPessoa_quandoSolicitado() {
        var pessoa = umaPessoa();
        assertThat(pessoa)
                .extracting("nomeCompleto", "dataNascimento")
                .containsExactly("Josue da Silva", LocalDate.of(2000, 3, 01));

        var request = umaPessoaRequest();
        request.setNomeCompleto("Patricia mendes");
        request.setDataNascimento(LocalDate.of(1999, 8, 01));
        pessoa.setAll(request);

        assertThat(pessoa)
                .extracting("nomeCompleto", "dataNascimento")
                .containsExactly("Patricia mendes", LocalDate.of(1999, 8, 01));
    }
}
