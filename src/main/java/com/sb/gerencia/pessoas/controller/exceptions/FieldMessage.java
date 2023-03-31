package com.sb.gerencia.pessoas.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldMessage {

    private String fieldMessage;
    private String name;
}
