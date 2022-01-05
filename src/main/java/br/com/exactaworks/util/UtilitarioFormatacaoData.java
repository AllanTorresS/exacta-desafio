package br.com.exactaworks.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UtilitarioFormatacaoData {

    /**
     * Formata uma data baseada em um padrão
     * @param pattern O padrão de formatação ex: dd/MM/yyyy
     * @param data A data a ser formatada
     * @return A data formatada no padrão especificado
     */
    public  String formatarData(String pattern, LocalDateTime data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return data.format(formatter);
    }

    /**
     * Converte uma string para um {@link LocalDateTime}
     * @param pattern O padrão de formatação ex: dd/MM/yyyy
     * @param data A data a ser convertida
     * @return O texto convertido em data
     */
    public  LocalDateTime obterData(String pattern, String data) {
        return LocalDateTime.parse(data, DateTimeFormatter.ofPattern(pattern));
    }
}
