package com.sb.gerencia.pessoas.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private Timestamp dataNascimento;

    @ManyToOne
    @JsonManagedReference
    private List<Endereco> enderecos;
}
