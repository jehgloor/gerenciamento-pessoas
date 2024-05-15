package br.com.pessoas.modules.endereco.controller;

import br.com.pessoas.modules.comum.exception.NotFoundException;
import br.com.pessoas.modules.endereco.enums.ESituacao;
import br.com.pessoas.modules.endereco.repository.EnderecoRepository;
import br.com.pessoas.modules.endereco.service.EnderecoService;
import br.com.pessoas.modules.pessoa.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static br.com.pessoas.helper.TestsHelper.convertObjectToJsonBytes;
import static br.com.pessoas.modules.endereco.helper.EnderecoHelper.umEnderecoRequest;
import static br.com.pessoas.modules.endereco.helper.EnderecoHelper.umEnderecoResponse;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class EnderecoControllerTest {

    private static final String BASE_URL = "/endereco";

    @Autowired
    private MockMvc mvc;
    @MockBean
    private RestTemplate restTemplate;
    @MockBean
    private EnderecoService service;
    @MockBean
    private PessoaService pessoaService;
    @MockBean
    private EnderecoRepository enderecoRepository;

    @Test
    public void buscarPorId_deveRetornar200_seExistirEndereco() throws Exception {
        when(service.buscarPorId(1)).thenReturn(umEnderecoResponse());

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.logradouro", is("Rua das rosas")))
                .andExpect(jsonPath("$.cep", is("86909-909")))
                .andExpect(jsonPath("$.numero", is(123)))
                .andExpect(jsonPath("$.cidade", is("São Paulo")))
                .andExpect(jsonPath("$.uf", is("SP")))
                .andExpect(jsonPath("$.situacao", is("PRIMARIA")))
                .andExpect(jsonPath("$.pessoaId", is(1)));

        verify(service).buscarPorId(1);
    }

    @Test
    public void buscarPorId_deveRetornar404_seNaoExistirEndereco() throws Exception {
        when(service.buscarPorId(9999)).thenThrow(new NotFoundException("Endereço não encontrado"));

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service).buscarPorId(9999);
    }

    @Test
    public void salvarEndereco_deveRetornar201_seSolicitado() throws Exception {
        var request = umEnderecoRequest();
        var response = umEnderecoResponse();

        when(service.salvarEndereco(request)).thenReturn(response);

        mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.logradouro", is("Rua das rosas")))
                .andExpect(jsonPath("$.cep", is("86909-909")))
                .andExpect(jsonPath("$.numero", is(123)))
                .andExpect(jsonPath("$.cidade", is("São Paulo")))
                .andExpect(jsonPath("$.uf", is("SP")))
                .andExpect(jsonPath("$.situacao", is("PRIMARIA")))
                .andExpect(jsonPath("$.pessoaId", is(1)));

        verify(service).salvarEndereco(request);
    }

    @Test
    public void salvarEndereco_deveRetornar400_seCamposObrigatoriosVazio() throws Exception {
        var request = umEnderecoRequest();
        var response = umEnderecoResponse();
        request.setCidade(null);
        request.setUf(null);
        request.setPessoaId(null);
        request.setSituacao(null);

        when(service.salvarEndereco(request)).thenReturn(response);

        mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].message", containsInAnyOrder(
                        "O campo uf must not be blank",
                        "O campo cidade must not be blank",
                        "O campo situacao must not be null",
                        "O campo pessoaId must not be null")));

        verify(service, never()).salvarEndereco(request);
    }

    @Test
    public void buscarTodos_deveRetornar200_seExistirEnderecos() throws Exception {
        when(service.buscarTodos()).thenReturn(List.of(umEnderecoResponse()));

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].logradouro", is("Rua das rosas")))
                .andExpect(jsonPath("$[0].cep", is("86909-909")))
                .andExpect(jsonPath("$[0].numero", is(123)))
                .andExpect(jsonPath("$[0].cidade", is("São Paulo")))
                .andExpect(jsonPath("$[0].uf", is("SP")))
                .andExpect(jsonPath("$[0].situacao", is("PRIMARIA")))
                .andExpect(jsonPath("$[0].pessoaId", is(1)));

        verify(service).buscarTodos();
    }

    @Test
    public void editarEndereco_deveRetornar200_seExistirEnderecos() throws Exception {
        var request = umEnderecoRequest();
        var response = umEnderecoResponse();

        when(service.salvarEndereco(request)).thenReturn(response);
        mvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());

        verify(service).editarEndereco(1, request);
    }
}
