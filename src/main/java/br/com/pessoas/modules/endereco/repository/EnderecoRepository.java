package br.com.pessoas.modules.endereco.repository;

import br.com.pessoas.modules.endereco.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
