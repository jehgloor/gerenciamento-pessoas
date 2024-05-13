package br.com.pessoas.modules.endereco.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static br.com.pessoas.modules.endereco.enums.ESituacao.PRIMARIA;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@Sql(scripts = {"classpath:/endereco.sql"})
public class EnderecoRepositoryTest {

    @Autowired
    EnderecoRepository repository;

    @Test
    public void atualizaEnderecoParaSecundario_deveAtualizarSituacao_quandoSolicitado() {

        assertThat(repository.findByPessoaIdAndSituacao(1, PRIMARIA).size()).isEqualTo(7);
        repository.atualizaEnderecoParaSecundario(1);

        assertThat(repository.findByPessoaIdAndSituacao(1, PRIMARIA).size()).isEqualTo(0);
    }


}
