--------------------------------------------------------------------------------------------
-- TABELA GASTOS
--------------------------------------------------------------------------------------------

create table schema_solucao.GASTOS
(
    id_gastos       int8 not null,
    data_gasto      timestamp,
    descricao       varchar(255),
    nome_usuario    varchar(255),
    tags            varchar(255),
    valor           numeric(19, 2),
    data_requisicao timestamp,
    primary key (id_gastos)
);
--------------------------------------------------------------------------------------------
-- SEQUENCIA PARA GASTOS
--------------------------------------------------------------------------------------------
CREATE SEQUENCE schema_solucao.SEQ_GASTOS
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807;

--------------------------------------------------------------------------------------------
-- SEQUENCIA PARA AUDITORIA
--------------------------------------------------------------------------------------------
CREATE SEQUENCE schema_solucao.hibernate_sequence
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807;

--------------------------------------------------------------------------------------------
-- CRIA TABELA DE AUDITORIA FROTA
--------------------------------------------------------------------------------------------
create table schema_auditoria.GASTOS_AUD
(
    id_gastos       int8 not null,
    rev             int4 not null,
    revtype         int2,
    data_gasto      timestamp,
    descricao       varchar(255),
    nome_usuario    varchar(255),
    tags            varchar(255),
    valor           numeric(19, 2),
    data_requisicao timestamp,
    primary key (id_gastos, rev)
);

--------------------------------------------------------------------------------------------
-- CRIA TABELA DE AUDITORIA REVINFO
--------------------------------------------------------------------------------------------
create table schema_auditoria.revinfo
(
    rev      int4 not null,
    revtstmp int8,
    primary key (rev)
);

--------------------------------------------------------------------------------------------
-- ADICIONA RESTRIÇÃO PARA TABELA GASTOS_AUD
--------------------------------------------------------------------------------------------
alter table schema_auditoria.GASTOS_AUD
    add constraint FK_GASTOS_AUD
        foreign key (rev)
            references schema_auditoria.revinfo;