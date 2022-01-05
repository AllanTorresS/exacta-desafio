package br.com.exactaworks.massadados;

import br.com.exactaworks.model.entity.Gastos;
import br.com.exactaworks.util.UtilitarioFormatacaoData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DadosTesteGasto {

    public static Gastos gastoCompleto(){
        UtilitarioFormatacaoData formatacaoData = new UtilitarioFormatacaoData();
        Gastos gastosVo = new Gastos();

        gastosVo.setIdGastos(1L);
        gastosVo.setDataRequisicao(formatacaoData.obterData("dd/MM/yyyy HH:mm","01/12/2021 08:31"));
        gastosVo.setDataGasto(formatacaoData.obterData("dd/MM/yyyy HH:mm","01/12/2021 08:31"));
        gastosVo.setTags("gastos financieros");
        gastosVo.setValor(BigDecimal.TEN);
        gastosVo.setDescricao("Gasto simulado para teste");
        gastosVo.setNomeUsuario("Usuario comum");
        return gastosVo;
    }



}
