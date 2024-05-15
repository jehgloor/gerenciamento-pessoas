package br.com.pessoas.modules.endereco.model;

import br.com.pessoas.modules.endereco.dto.EnderecoRequest;
import br.com.pessoas.modules.endereco.enums.ESituacao;
import br.com.pessoas.modules.pessoa.model.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ENDERECO")
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

    @Column(name = "SITUACAO")
    @Enumerated(EnumType.STRING)
    private ESituacao situacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Pessoa pessoa;

    public static Endereco of(EnderecoRequest request, Pessoa pessoa) {

        var endereco = new Endereco();
        endereco.setCep(request.getCep());
        endereco.setUf(request.getUf());
        endereco.setCidade(request.getCidade());
        endereco.setLogradouro(request.getLogradouro());
        endereco.setNumero(request.getNumero());
        endereco.setSituacao(request.getSituacao());
        endereco.setPessoa(pessoa);
        return endereco;
    }

    public void setAll(EnderecoRequest request, Pessoa pessoa) {
        this.setCep(request.getCep());
        this.setUf(request.getUf());
        this.setCidade(request.getCidade());
        this.setLogradouro(request.getLogradouro());
        this.setNumero(request.getNumero());
        this.setPessoa(pessoa);
        this.setSituacao(request.getSituacao());
    }
}
