package br.com.pessoas.modules.endereco.model;

import br.com.pessoas.modules.endereco.enums.ESituacao;
import br.com.pessoas.modules.pessoa.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ENDERECO")
@Entity
public class Endereco {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "NUMERO")
    private Integer numero;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "UF")
    private String uf;
//    private Pessoa pessoa;

    @Column(name = "SITUACAO")
    private ESituacao situacao;
}
