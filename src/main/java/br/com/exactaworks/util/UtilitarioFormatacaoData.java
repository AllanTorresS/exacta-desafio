package br.com.exactaworks.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UtilitarioFormatacaoData {

    public  String formatarData(String pattern, LocalDateTime data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return data.format(formatter);
    }
}
