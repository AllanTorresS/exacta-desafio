package br.com.exactaworks.controllers;

import br.com.exactaworks.model.error.custom.ExactaInternalServerErrorExeception;
import br.com.exactaworks.model.error.custom.ExactaNotFoundExeception;
import br.com.exactaworks.model.error.dto.ExactaErrorResponseDTO;
import br.com.exactaworks.util.UtilitarioFormatacaoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Classe que centraliza tratamento de erros
 */
@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UtilitarioFormatacaoData formatacaoData;

    private final String preFixo = "Parâmentro ";

    /**
     * Centraliza o error de atributos invalidos na requisição
     * @param ex Tipo de execeção
     * @param headers Cabeçalhos da requisição
     * @param status O status do erro
     * @param request A requisição
     * @return Resposta de erro tratada com os campos invalidos
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> this.preFixo + x.getField() + ", " + x.getDefaultMessage())
                .collect(Collectors.toList());

        ExactaErrorResponseDTO exactaErrorResponseDTO = this.montarErrorResponse(errors, status, "campos com erros");

        return new ResponseEntity(exactaErrorResponseDTO, headers, status);
    }

    /**
     * Centraliza o error de conversão para Json ou outro formato
     * @param ex Tipo de execeção
     * @param headers Cabeçalhos da requisição
     * @param status O status do erro
     * @param request A requisição
     * @return Resposta de erro tratada
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> erros = Arrays.asList(this.mensagemErroProperties("json.error", request));
        String debugMessage = ex.getLocalizedMessage();

        ExactaErrorResponseDTO acertErrorResponse = this.montarErrorResponse(erros, status, debugMessage);

        return new ResponseEntity(acertErrorResponse, headers, status);
    }

    /**
     * Centraliza o error de metodo não suportado
     * @param ex Tipo de execeção
     * @param headers Cabeçalhos da requisição
     * @param status O status do erro
     * @param request A requisição
     * @return Resposta de erro tratada
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> erros = Arrays.asList(this.mensagemErroProperties("end.point.parameters.unsupported", request));
        String debugMessage = ex.getLocalizedMessage();

        ExactaErrorResponseDTO acertErrorResponse = this.montarErrorResponse(erros, status, debugMessage);

        return new ResponseEntity(acertErrorResponse, headers, status);

    }

    /**
     * Centraliza o error de end point não encontrado
     * @param ex Tipo de execeção
     * @param request A requisição
     * @return Resposta de erro tratada
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> erros = Arrays.asList(this.mensagemErroProperties("end.point.not.found", request));
        String debugMessage = ex.getLocalizedMessage();

        ExactaErrorResponseDTO acertErrorResponse = this.montarErrorResponse(erros, status, debugMessage);

        return new ResponseEntity(acertErrorResponse, headers, status);
    }

    /**
     * Centraliza o error de dados não encontrados
     * @param ex Tipo de exaceção
     * @param request A requisição
     * @return Resposta de erro tratada
     */
    @ExceptionHandler(ExactaNotFoundExeception.class)
    public ResponseEntity<Object> handleAcertNotFoundException(ExactaNotFoundExeception ex, WebRequest request) {

        List<String> erros = Arrays.asList(this.mensagemErroProperties("not.found.execption", request));
        String debugMessage = ex.getStackTrace().toString();

        ExactaErrorResponseDTO acertErrorResponse = this.montarErrorResponse(erros, HttpStatus.NOT_FOUND, debugMessage);

        return new ResponseEntity(acertErrorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Centraliza error de Interno
     * @param ex Tipo de exaceção
     * @param request A requisição
     * @return Resposta de erro tratada
     */
    @ExceptionHandler(ExactaInternalServerErrorExeception.class)
    public ResponseEntity<Object> handleAcertInternalServerErrorExeception(ExactaInternalServerErrorExeception ex, WebRequest request) {

        List<String> erros = Arrays.asList(this.mensagemErroProperties("internal.server.error.execption", request));
        String debugMessage = ex.getStackTrace().toString();

        ExactaErrorResponseDTO errorResponseDTO = this.montarErrorResponse(erros, HttpStatus.NOT_FOUND, debugMessage);

        return new ResponseEntity(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    /**
     * Monta uma resposta de erro detalhada
     * @param errors Lista com os errors que ocorreram
     * @param status O status do erro
     * @param debugMessage Mensagem gerada pelo aplicação
     * @return Um DTO contendo todos os erros
     */
    private ExactaErrorResponseDTO montarErrorResponse(List<String> errors, HttpStatus status, String debugMessage) {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        LocalDateTime hoje = LocalDateTime.now();
        String dataFormatada = formatacaoData.formatarData(pattern, hoje);

        ExactaErrorResponseDTO acertErrorResponse = new ExactaErrorResponseDTO();

        acertErrorResponse.setHttpStatusCodigo(status.value());
        acertErrorResponse.setHttpStatusCodigoDescricao(status.toString());
        acertErrorResponse.setErrorDiaHora(dataFormatada);
        acertErrorResponse.setListaDeErrors(errors);
        acertErrorResponse.setDebugMessage(debugMessage);

        return acertErrorResponse;
    }

    /**
     * Obtem a mensagem a partir de uma chave
     * @param mensagenChave Chave que indica a mensagem
     * @param request A requisição
     * @return A mensagem
     */
    private String mensagemErroProperties(String mensagenChave, WebRequest request) {
        Locale locale = request.getLocale();
        return this.messageSource.getMessage(mensagenChave, null, locale);
    }
}
