package com.icon.desafio.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icon.desafio.dto.ProdutoDTO;
import com.icon.desafio.model.ProdutoModel;
import com.icon.desafio.service.ServiceProduto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/produto")
public class ControllerProduto {

    private final ServiceProduto serviceProduto;

    @Autowired
    public ControllerProduto(ServiceProduto serviceProduto) {
        this.serviceProduto = serviceProduto;
    }

    @PostMapping("save")
    public ResponseEntity<ProdutoModel> postProduto(@RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceProduto.salvarProduto(produtoDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoModel> alterar(@PathVariable("id") Long id, @RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok().body(serviceProduto.alterarProduto(id, produtoDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ProdutoModel>> getProduto(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(serviceProduto.buscarProdutoID(id));
    }

    @GetMapping("list")
    public ResponseEntity<Set<ProdutoDTO>> getProdutos() {
        return ResponseEntity.ok().body(serviceProduto.carregarListaProdutos());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable("id") Long id) {
        serviceProduto.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }

}
