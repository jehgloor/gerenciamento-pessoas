package br.com.pessoas.modules.pessoa.repository;

import br.com.pessoas.modules.pessoa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    Optional<Pessoa> findById(Integer id);
}
