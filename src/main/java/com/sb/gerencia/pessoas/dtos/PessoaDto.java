package com.sb.gerencia.pessoas.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class PessoaDto {

    @NotBlank(message = "Não pode estar em branco")
    @Size(min = 3, max = 255, message = "Deve ter de 3 a 255 caracteres")
    private String nome;
    @NotBlank(message = "Não estar em branco")
    private Timestamp dataNascimento;

    private List<EnderecoDto> enderecos;
}
