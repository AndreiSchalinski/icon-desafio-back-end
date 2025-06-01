package com.icon.desafio.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icon.desafio.dto.TipoProdutoDTO;
import com.icon.desafio.model.TipoProdutoModel;
import com.icon.desafio.service.ServiceTipoProduto;

@RestController
@RequestMapping("/api/categoria")
public class ControllerTipoProduto {

    private final ServiceTipoProduto serviceTipoProduto;

    @Autowired
    public ControllerTipoProduto(ServiceTipoProduto serviceTipoProduto) {
        this.serviceTipoProduto = serviceTipoProduto;
    }

    @PostMapping("save")
    public ResponseEntity<?> postCategoria(@RequestBody TipoProdutoDTO tipoProdutoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceTipoProduto.salvarTipoProduto(tipoProdutoDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<TipoProdutoModel> alterar(@PathVariable("id") Long id, @RequestBody TipoProdutoDTO tipoProdutoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceTipoProduto.alterarTipoProduto(id, tipoProdutoDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<TipoProdutoModel>> getCategoria(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(serviceTipoProduto.buscarTipoProdutoID(id));
    }

    @GetMapping("list")
    public ResponseEntity<Set<TipoProdutoDTO>> getCategorias() {
        return ResponseEntity.ok().body(serviceTipoProduto.carregarListaTipoProdutos());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable("id") Long id) {
        serviceTipoProduto.deleteTipoProduto(id);
        return ResponseEntity.noContent().build();
    }
}
