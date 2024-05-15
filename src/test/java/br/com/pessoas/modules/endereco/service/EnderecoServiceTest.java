package br.com.pessoas.modules.endereco.service;

import br.com.pessoas.modules.comum.exception.NotFoundException;
import br.com.pessoas.modules.endereco.model.Endereco;
import br.com.pessoas.modules.endereco.repository.EnderecoRepository;
import br.com.pessoas.modules.pessoa.service.PessoaService;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.pessoas.modules.endereco.enums.ESituacao.PRIMARIA;
import static br.com.pessoas.modules.endereco.enums.ESituacao.SECUNDARIA;
import static br.com.pessoas.modules.endereco.helper.EnderecoHelper.*;
import static br.com.pessoas.modules.pessoa.helper.PessoaHelper.umaPessoa;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService service;
    @Mock
    private EnderecoRepository repository;

    @Mock
    private PessoaService pessoaService;

    @Test
    public void buscarPorId_deveLancarException_quandoIdNaoEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("O Endereço não foi encontrado.");

        verify(repository).findById(1);
    }


    @Test
    public void buscarPorId_deveRetornarEndereco_quandoIdEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(umEndereco(1)));
        assertThat(service.buscarPorId(1))
                .extracting("logradouro", "cep", "numero", "cidade", "uf", "situacao", "pessoaId")
                .containsExactly("Rua das Flores", "86909-909", 123, "Londrina", "PR", PRIMARIA, 1);
        verify(repository).findById(1);
    }

    @Test
    public void salvarEndereco_deveSalvarEndereco_quandoSolicitado() {
        when(repository.save(any(Endereco.class))).thenReturn(umEndereco(1));
        when(pessoaService.buscaPessoaPorId(1)).thenReturn(umaPessoa());

        assertThat(service.salvarEndereco(umEnderecoRequest()))
                .extracting("logradouro", "cep", "numero", "cidade", "uf", "situacao", "pessoaId")
                .containsExactly("Rua das Flores", "86909-909", 123, "Londrina", "PR", PRIMARIA, 1);

        verify(pessoaService).buscaPessoaPorId(1);
        verify(repository).atualizaEnderecoParaSecundario(1);
        verify(repository).save(any(Endereco.class));
    }

    @Test
    public void salvarEndereco_naoDeveSalvarEndereco_quandoNaoExistirPessoa() {
        when(pessoaService.buscaPessoaPorId(1)).thenThrow(new NotFoundException("A Pessoa não foi encontrada."));

        assertThatThrownBy(() -> service.salvarEndereco(umEnderecoRequest()))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("A Pessoa não foi encontrada.");

        verify(pessoaService).buscaPessoaPorId(1);
        verify(repository, never()).save(any(Endereco.class));
    }

    @Test
    public void salvarEndereco_deveSalvarEnderecoEAtualizarAsSituacoesDosAntigos_quandoPassadoSituacaoPrimariaNaRequest() {
        var request = umEnderecoRequest();
        request.setSituacao(PRIMARIA);
        when(repository.save(any(Endereco.class))).thenReturn(umEndereco(1));
        when(pessoaService.buscaPessoaPorId(1)).thenReturn(umaPessoa());

        assertThat(service.salvarEndereco(request))
                .extracting("logradouro", "cep", "numero", "cidade", "uf", "situacao", "pessoaId")
                .containsExactly("Rua das Flores", "86909-909", 123, "Londrina", "PR", PRIMARIA, 1);

        verify(pessoaService).buscaPessoaPorId(1);
        verify(repository).atualizaEnderecoParaSecundario(1);
        verify(repository).save(any(Endereco.class));
    }

    @Test
    public void salvarEndereco_deveSalvarEnderecoENaoAtualizarAsSituacoesDosAntigos_quandoPassadoSituacaoSecundariaNaRequest() {
        var request = umEnderecoRequest();
        request.setSituacao(SECUNDARIA);
        when(repository.save(any(Endereco.class))).thenReturn(umEndereco(1));
        when(pessoaService.buscaPessoaPorId(1)).thenReturn(umaPessoa());

        assertThat(service.salvarEndereco(request))
                .extracting("logradouro", "cep", "numero", "cidade", "uf", "situacao", "pessoaId")
                .containsExactly("Rua das Flores", "86909-909", 123, "Londrina", "PR", PRIMARIA, 1);

        verify(pessoaService).buscaPessoaPorId(1);
        verify(repository, never()).atualizaEnderecoParaSecundario(1);
        verify(repository).save(any(Endereco.class));
    }

    @Test
    public void buscarTodos_deveBuscarTodos_quandoSolicitado() {
        when(repository.findAll()).thenReturn(umaListaEndereco());
        assertThat(service.buscarTodos())
                .extracting("logradouro", "cep", "numero", "cidade", "uf", "situacao", "pessoaId")
                .containsExactly(
                        Tuple.tuple("Rua das Flores", "86909-909", 123, "Londrina", "PR", PRIMARIA, 1),
                        Tuple.tuple("Rua das Flores", "86909-909", 123, "Londrina", "PR", PRIMARIA, 1));

        verify(repository).findAll();
    }

    @Test
    public void editarEndereco_deveAtualizarEndereco_quandoExistirEndereco() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(umEndereco(1)));

        var request = umEnderecoRequest();
        request.setLogradouro("Endereco Atualizado");
        assertThat(service.editarEndereco(1, request))
                .extracting("logradouro", "cep", "numero", "cidade", "uf", "situacao", "pessoaId")
                .containsExactly("Endereco Atualizado", "86909-909", 123, "Londrina", "PR", PRIMARIA, 1);
    }

    @Test
    public void editarEndereco_deveLancarException_quandoNaoExistirEndereco() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        var request = umEnderecoRequest();
        request.setLogradouro("Endereco Atualizado");
        assertThatThrownBy(() -> service.editarEndereco(1, request))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("O Endereço não foi encontrado.");
    }
}
