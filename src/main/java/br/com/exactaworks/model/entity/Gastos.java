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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

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

    @NotBlank
    private String nomeUsuario;

    @NotBlank
    private String descricao;

    @NotNull
    private Date dataGasto;

    @DecimalMin("0.1")
    private BigDecimal valor;


    private String tags;

}
