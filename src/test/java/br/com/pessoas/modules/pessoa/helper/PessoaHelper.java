package br.com.pessoas.modules.pessoa.helper;

import br.com.pessoas.modules.pessoa.model.Pessoa;

import java.time.LocalDate;

public class PessoaHelper {

    public static Pessoa umaPessoa() {
        return Pessoa.builder()
                .nomeCompleto("Josue da Silva")
                .dataNascimento(LocalDate.of(2000,03,01))
                .id(1).build();
    }
}
