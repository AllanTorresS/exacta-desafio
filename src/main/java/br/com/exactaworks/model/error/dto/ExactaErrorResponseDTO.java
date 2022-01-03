package br.com.exactaworks.model.error.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ExactaErrorResponseDTO {
    private String errorDiaHora;
    private List<String> listaDeErrors;
    private Integer httpStatusCodigo;
    private String httpStatusCodigoDescricao;
    private String debugMessage;
}
