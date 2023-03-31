package com.sb.gerencia.pessoas.controller.exceptions;

import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationError extends StandardError{

    List<FieldMessage> listErrors = new ArrayList<>();

    public ValidationError(Instant timestamp, int status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getListErrors(){
        return this.listErrors;
    }

    public void addListErrors(String field, String message){
        listErrors.add(new FieldMessage(field, message));
    }

}
