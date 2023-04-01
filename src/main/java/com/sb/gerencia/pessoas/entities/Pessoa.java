package com.sb.gerencia.pessoas.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Timestamp dataNascimento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPessoa", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Endereco> enderecos;
}
