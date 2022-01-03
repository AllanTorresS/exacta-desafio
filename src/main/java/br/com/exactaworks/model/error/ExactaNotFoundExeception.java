package br.com.exactaworks.model.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExactaNotFoundExeception extends RuntimeException {

    public ExactaNotFoundExeception(String mensagem) {
        super(mensagem);
    }
}
