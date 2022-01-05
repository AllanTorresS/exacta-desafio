package br.com.exactaworks.service;

import br.com.exactaworks.massadados.DadosTesteGasto;
import br.com.exactaworks.model.entity.Gastos;
import br.com.exactaworks.model.error.custom.ExactaNotFoundExeception;
import br.com.exactaworks.repository.GastosRepository;
import br.com.exactaworks.util.UtilitarioAmbiente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GastoServiceTest {

    @InjectMocks
    private GastosService gastosService;

    @Mock
    private GastosRepository repository;

    @Mock
    private UtilitarioAmbiente ambiente;

    @Test
    public void deve_salvar_um_gasto_no_banco()
    {
        // Cenário
        Gastos  gasto = DadosTesteGasto.gastoCompleto() ;

        Mockito.when(repository.save(Mockito.any())).thenReturn(gasto);
        Mockito.when(ambiente.obterDataAtual()).thenReturn(LocalDateTime.now());

        //Ação
        gastosService.save(gasto);
        ArgumentCaptor<Gastos> registroPersistido = ArgumentCaptor.forClass(Gastos.class);

        //verifica se o repositorio foi chamado para incluir o Gasto
        Mockito.verify(repository, Mockito.times(1)).save(registroPersistido.capture());

    }

    @Test
    public void deve_buscar_um_gasto_por_id()
    {
        // Cenário
        Long id = 1L;
        Gastos  gasto = DadosTesteGasto.gastoCompleto() ;

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(gasto));


        //Ação
        gastosService.findById(1L);

        //verifica se o repositorio foi chamado para buscar um Gasto
        Mockito.verify(repository, Mockito.times(1)).findById(Mockito.any());

    }

    @Test
    public void deve_listar_todos_os_gastos()
    {
        // Cenário

        List<Gastos> gastos = Arrays.asList(DadosTesteGasto.gastoCompleto());
        Mockito.when(gastosService.findAll()).thenReturn(gastos);


        //Ação
        List<Gastos> gastosPersitidos = gastosService.findAll();

        //verifica se o repositorio foi chamado para buscar um Gasto
        Assertions.assertThat(gastosPersitidos).isNotEmpty();

    }



    @Test
    public void deve_gerar_uma_execao_quando_nao_encontrar_um_gasto() throws ExactaNotFoundExeception{

        // Cenário
        Long id = 2L;

        //Ação
        Assertions.assertThatExceptionOfType(ExactaNotFoundExeception.class)
                .isThrownBy(() -> gastosService.findById(id));

    }
}
