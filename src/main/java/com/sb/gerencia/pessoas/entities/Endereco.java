package com.sb.gerencia.pessoas.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String logradouro;
    private String cep;
    private int numero;
    private String cidade;
    private Boolean principal;

    @OneToMany
    @JsonBackReference
    private Pessoa pessoa;
}
