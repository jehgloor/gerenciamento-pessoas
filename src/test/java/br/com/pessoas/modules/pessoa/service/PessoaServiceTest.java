package br.com.pessoas.modules.pessoa.service;

import br.com.pessoas.modules.comum.exception.NotFoundException;
import br.com.pessoas.modules.pessoa.model.Pessoa;
import br.com.pessoas.modules.pessoa.repository.PessoaRepository;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static br.com.pessoas.modules.pessoa.helper.PessoaHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @InjectMocks
    private PessoaService service;
    @Mock
    private PessoaRepository repository;


    @Test
    public void buscarPorId_deveLancarException_quandoIdNaoEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("A Pessoa não foi encontrado.");

        verify(repository).findById(1);
    }


    @Test
    public void buscarPorId_deveRetornarPessoa_quandoIdEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(umaPessoa()));
        assertThat(service.buscarPorId(1))
                .extracting("nomeCompleto", "dataNascimento")
                .containsExactly("Josue da Silva", LocalDate.of(2000, 03, 01));
        verify(repository).findById(1);
    }

    @Test
    public void salvar_deveSalvarPessoa_quandoSolicitado() {
        when(repository.save(any(Pessoa.class))).thenReturn(umaPessoa());

        assertThat(service.salvar(umaPessoaRequest()))
                .extracting("nomeCompleto", "dataNascimento")
                .containsExactly("Josue da Silva", LocalDate.of(2000, 03, 01));

        verify(repository).save(any(Pessoa.class));
    }


    @Test
    public void buscarTodos_deveBuscarTodos_quandoSolicitado() {
        when(repository.findAll()).thenReturn(umaListaPessoas());
        assertThat(service.buscarTodos())
                .extracting("nomeCompleto", "dataNascimento")
                .containsExactly(
                        Tuple.tuple("Priscila Cunha", LocalDate.of(1990, 01, 01)),
                        Tuple.tuple("Tiago Aparecido", LocalDate.of(2000, 02, 01)));

        verify(repository).findAll();
    }

    @Test
    public void editar_deveAtualizarPessoa_quandoExistirPessoa() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(umaPessoa()));

        assertThat(service.editar(1, umaPessoaRequest()))
                .extracting("nomeCompleto", "dataNascimento")
                .containsExactly("Maria de Jesus", LocalDate.of(2000, 8, 01));
    }

    @Test
    public void editarPessoa_deveLancarException_quandoNaoExistirPessoa() {
        when(repository.findById(1)).thenReturn(Optional.empty());


        assertThatThrownBy(() -> service.editar(1, umaPessoaRequest()))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("A Pessoa não foi encontrado.");
    }
}
