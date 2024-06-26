package br.com.pessoas.modules.pessoa.model;

import br.com.pessoas.modules.endereco.model.Endereco;
import br.com.pessoas.modules.pessoa.dto.PessoaRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PESSOA")
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
    @JsonIgnore
    private List<Endereco> enderecos;

    public static Pessoa of(PessoaRequest request) {
        var pessoa = new Pessoa();
        pessoa.setNomeCompleto(request.getNomeCompleto());
        pessoa.setDataNascimento(request.getDataNascimento());

        return pessoa;
    }

    public void setAll(PessoaRequest request) {
        this.setNomeCompleto(request.getNomeCompleto());
        this.setDataNascimento(request.getDataNascimento());
    }
}
