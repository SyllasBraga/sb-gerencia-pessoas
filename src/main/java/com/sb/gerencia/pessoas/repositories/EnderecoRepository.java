package com.sb.gerencia.pessoas.repositories;

import com.sb.gerencia.pessoas.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Long> {
}
