package br.com.pessoas.modules.endereco.model;

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
    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;
    private String uf;
//    private Pessoa pessoa;
}
