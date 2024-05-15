package br.com.pessoas.modules.pessoa.controller;

import br.com.pessoas.modules.comum.exception.NotFoundException;
import br.com.pessoas.modules.pessoa.repository.PessoaRepository;
import br.com.pessoas.modules.pessoa.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static br.com.pessoas.helper.TestsHelper.convertObjectToJsonBytes;
import static br.com.pessoas.modules.pessoa.helper.PessoaHelper.umaPessoaRequest;
import static br.com.pessoas.modules.pessoa.helper.PessoaHelper.umaPessoaResponse;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class PessoaControllerTest {

    private static final String BASE_URL = "/pessoa";

    @Autowired
    private MockMvc mvc;
    @MockBean
    private RestTemplate restTemplate;
    @MockBean
    private PessoaService service;
    @MockBean
    private PessoaRepository pessoaRepository;

    @Test
    public void buscarPorId_deveRetornar200_seExistirPessoa() throws Exception {
        when(service.buscarPorId(1)).thenReturn(umaPessoaResponse());

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeCompleto", is("Vanessa Silva")))
                .andExpect(jsonPath("$.dataNascimento", is("1990-06-01")));

        verify(service).buscarPorId(1);
    }

    @Test
    public void buscarPorId_deveRetornar404_seNaoExistirPessoa() throws Exception {
        when(service.buscarPorId(9999)).thenThrow(new NotFoundException("Endereço não encontrado"));

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service).buscarPorId(9999);
    }

    @Test
    public void salvarPessoa_deveRetornar201_seSolicitado() throws Exception {
        var request = umaPessoaRequest();
        var response = umaPessoaResponse();

        when(service.salvar(request)).thenReturn(response);

        mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomeCompleto", is("Vanessa Silva")))
                .andExpect(jsonPath("$.dataNascimento", is("1990-06-01")));

        verify(service).salvar(request);
    }

    @Test
    public void buscarTodos_deveRetornar200_seExistirPessoas() throws Exception {
        when(service.buscarTodos()).thenReturn(List.of(umaPessoaResponse()));

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeCompleto", is("Vanessa Silva")))
                .andExpect(jsonPath("$[0].dataNascimento", is("1990-06-01")));

        verify(service).buscarTodos();
    }

    @Test
    public void editarPessoa_deveRetornar200_seExistirPessoas() throws Exception {
        var request = umaPessoaRequest();
        var response = umaPessoaResponse();

        when(service.salvar(request)).thenReturn(response);
        mvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());

        verify(service).editar(1, request);
    }
}
