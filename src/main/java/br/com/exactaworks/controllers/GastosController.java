package br.com.exactaworks.controllers;

import br.com.exactaworks.model.entity.Gastos;
import br.com.exactaworks.service.GastosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/V1/gastos-manager")
@Api(value = "API REST Gastos")
public class GastosController {

    @Autowired
    private GastosService gastosService;

    @PostMapping(value = "/save")
    @ApiOperation(value = "Salva um gasto")
    public ResponseEntity save(@Valid @RequestBody Gastos gasto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.gastosService.save(gasto));
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "Busca um gasto pelo seu id")
    public ResponseEntity findById(@Valid @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.gastosService.findById(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "Lista todos os gastos")
    public ResponseEntity listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.gastosService.findAll());
    }

}
