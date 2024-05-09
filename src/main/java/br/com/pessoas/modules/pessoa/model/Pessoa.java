package br.com.pessoas.modules.pessoa.model;

import br.com.pessoas.modules.endereco.model.Endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
//    private List<Endereco> enderecos;
}
