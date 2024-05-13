package br.com.pessoas.modules.endereco.service;

import br.com.pessoas.modules.comum.exception.NotFoundException;
import br.com.pessoas.modules.endereco.repository.EnderecoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static br.com.pessoas.modules.endereco.helper.EnderecoHelper.umEndereco;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class EnderecoServiceTest {
    @InjectMocks
    private EnderecoService service;
    @Mock
    private EnderecoRepository repository;

    @Test
    public void buscarPorId_deveLancarException_quandoIdNaoEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(umEndereco()));

        assertThatThrownBy(() -> service.buscarPorId(2))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("No campo cidade grupo, se o valor toda");
    }
}
