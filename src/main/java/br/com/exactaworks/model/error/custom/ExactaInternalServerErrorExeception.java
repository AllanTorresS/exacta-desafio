package br.com.exactaworks.model.error.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ExactaInternalServerErrorExeception extends RuntimeException {

    public ExactaInternalServerErrorExeception(String mensagem) {
        super(mensagem);
    }
}
