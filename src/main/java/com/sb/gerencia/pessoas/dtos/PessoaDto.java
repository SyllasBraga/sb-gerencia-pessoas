package com.sb.gerencia.pessoas.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {

    private Long id;
    @NotBlank(message = "Não pode estar em branco")
    @Size(min = 3, max = 255, message = "Deve ter de 3 a 255 caracteres")
    private String nome;
    @NotNull(message = "Não estar em branco")
    private Timestamp dataNascimento;

    private List<EnderecoDto> enderecos;
}
