package br.com.pessoas.modules.pessoa.dto;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static br.com.pessoas.modules.pessoa.helper.PessoaHelper.umaListaPessoas;
import static br.com.pessoas.modules.pessoa.helper.PessoaHelper.umaPessoa;
import static org.assertj.core.api.Assertions.assertThat;

public class PessoaResponseTest {
    @Test
    public void of_deveRetornarPessoaResponse_quandoSolicitado() {
        assertThat(PessoaResponse.of(umaPessoa()))
                .extracting("nomeCompleto", "dataNascimento")
                .containsExactly("Josue da Silva", LocalDate.of(2000, 03, 01));

    }

    @Test
    public void of_deveRetornarUmaListaPessoaResponse_quandoSolicitado() {
        assertThat(PessoaResponse.of(umaListaPessoas()))
                .extracting("nomeCompleto", "dataNascimento")
                .containsExactly(Tuple.tuple("Priscila Cunha", LocalDate.of(1990, 01, 01)),
                        Tuple.tuple("Tiago Aparecido", LocalDate.of(2000, 02, 01)));
    }
}
