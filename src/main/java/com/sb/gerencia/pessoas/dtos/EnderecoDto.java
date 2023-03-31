package com.sb.gerencia.pessoas.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sb.gerencia.pessoas.entities.Pessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class EnderecoDto {

    @NotBlank(message = "Não pode estar em branco")
    @Size(min = 3, max = 255, message = "Deve ter de 3 a 255 caracteres")
    private String logradouro;

    @NotBlank(message = "Não pode estar em branco")
    @Size(min = 9, max = 9, message = "Deve ter 9 caracteres")
    private String cep;

    @NotBlank(message = "Não pode estar em branco")
    @Size(min = 1, max = 255, message = "Deve ter de 1 a 255 caracteres")
    private int numero;

    @NotBlank(message = "Não pode estar em branco")
    @Size(min = 3, max = 255, message = "Deve ter de 3 a 255 caracteres")
    private String cidade;
}
