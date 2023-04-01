package com.sb.gerencia.pessoas.repositories;

import com.sb.gerencia.pessoas.entities.Endereco;
import com.sb.gerencia.pessoas.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Long> {

    List<Endereco> findByIdPessoa(Pessoa pessoa);

}
