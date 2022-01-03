package br.com.exactaworks.service;

import br.com.exactaworks.model.entity.Gastos;
import br.com.exactaworks.model.error.ExactaNotFoundExeception;
import br.com.exactaworks.repository.GastosRepository;
import br.com.exactaworks.util.UtilitarioAmbiente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GastosService {

    @Autowired
    private GastosRepository gastosRepository;

    @Autowired
    private UtilitarioAmbiente ambiente;

    /**
     * Salva um {@link Gastos} no banco
     * @param gasto Entidade a ser salva
     * @return O Gasto salvo
     */
    @Transactional
    public Gastos save(Gastos gasto) {
        gasto.setDataRequisicao(ambiente.obterDataAtual());

        return this.gastosRepository.save(gasto);
    }

    /**
     * Busca um {@link Gastos} por Id
     * @param id O Id do gasto buscado
     * @return O Gasto salvo
     * @throws ExactaNotFoundExeception Caso não seja encontrado o Gasto com o Id informado
     */
    public Gastos findById(Long id) throws ExactaNotFoundExeception {
        return this.gastosRepository.findById(id).orElseThrow(()-> new ExactaNotFoundExeception("not.found.execption"));
    }

    /**
     * Lista todos os {@link Gastos} existentes
     * @return Lista com os gastos salvos
     */
    public List<Gastos> findAll() {
        return this.gastosRepository.findAll();
    }
}
