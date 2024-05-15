package br.com.pessoas.modules.pessoa.helper;

import br.com.pessoas.modules.pessoa.dto.PessoaRequest;
import br.com.pessoas.modules.pessoa.dto.PessoaResponse;
import br.com.pessoas.modules.pessoa.model.Pessoa;

import java.time.LocalDate;
import java.util.List;

public class PessoaHelper {

    public static Pessoa umaPessoa() {
        return Pessoa.builder()
                .nomeCompleto("Josue da Silva")
                .dataNascimento(LocalDate.of(2000, 03, 01))
                .id(1)
                .build();
    }

    public static List<Pessoa> umaListaPessoas() {
        return List.of(
                Pessoa.builder()
                        .nomeCompleto("Priscila Cunha")
                        .dataNascimento(LocalDate.of(1990, 01, 01))
                        .id(1)
                        .build(),
                Pessoa.builder()
                        .nomeCompleto("Tiago Aparecido")
                        .dataNascimento(LocalDate.of(2000, 02, 01))
                        .id(1)
                        .build()
        );
    }

    public static PessoaRequest umaPessoaRequest() {
        return PessoaRequest.builder()
                .nomeCompleto("Maria de Jesus")
                .dataNascimento(LocalDate.of(2000, 8, 01))
                .build();
    }

    public static PessoaResponse umaPessoaResponse() {
        return PessoaResponse.builder()
                .nomeCompleto("Vanessa Silva")
                .dataNascimento(LocalDate.of(1990, 06, 01))
                .build();
    }
}
