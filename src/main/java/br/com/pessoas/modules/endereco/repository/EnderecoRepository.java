package br.com.pessoas.modules.endereco.repository;

import br.com.pessoas.modules.endereco.enums.ESituacao;
import br.com.pessoas.modules.endereco.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Endereco e SET e.situacao = 'SECUNDARIA' WHERE e.pessoa.id = :pessoaId")
    void atualizaEnderecoParaSecundario(Integer pessoaId);

       List<Endereco> findByPessoaIdAndSituacao(Integer id, ESituacao situacao);
}
