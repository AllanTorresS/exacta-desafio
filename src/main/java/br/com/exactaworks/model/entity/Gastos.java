package br.com.exactaworks.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Audited
@Table(name = "GASTOS")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Gastos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GASTOS")
    @SequenceGenerator(name = "SEQ_GASTOS", sequenceName = "SEQ_GASTOS",
            allocationSize = 1
    )
    @EqualsAndHashCode.Include
    private Long idGastos;

    private String nomeUsuario;
    private String descricao;
    private Date dataGasto;
    private BigDecimal valor;
    private String tags;

}
