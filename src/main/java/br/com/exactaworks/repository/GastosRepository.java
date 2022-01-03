package br.com.exactaworks.repository;

import br.com.exactaworks.model.entity.Gastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastosRepository extends JpaRepository<Gastos, Long> {

}
