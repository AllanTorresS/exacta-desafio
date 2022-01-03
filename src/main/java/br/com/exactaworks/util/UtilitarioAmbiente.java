package br.com.exactaworks.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UtilitarioAmbiente {

    /**
     * Obtem a data atual
     * @return A hora atual formatada no padrão dia/mês/ano hora:min:seg
     */
    public LocalDateTime obterDataAtual (){
        LocalDateTime hoje = LocalDateTime.now();
        DateTimeFormatter  formatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return   LocalDateTime.parse(hoje.format(formatter),formatter);
    }
}
