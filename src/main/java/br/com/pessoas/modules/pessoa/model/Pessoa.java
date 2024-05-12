package br.com.pessoas.modules.pessoa.model;

import br.com.pessoas.modules.endereco.model.Endereco;

import br.com.pessoas.modules.pessoa.dto.PessoaRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PESSOA")
@Entity
public class Pessoa {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa")
    private List<Endereco> enderecos;

    public static Pessoa of(PessoaRequest request) {
        var pessoa = new Pessoa();
        pessoa.setNomeCompleto(request.getNomeCompleto());
        pessoa.setDataNascimento(request.getDataNascimento());

        return pessoa;
    }
}
