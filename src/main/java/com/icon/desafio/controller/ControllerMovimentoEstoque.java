package com.icon.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icon.desafio.dto.MovimentoEstoqueDTO;
import com.icon.desafio.service.ServiceMovimentoEstoque;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/movimentacao")
public class ControllerMovimentoEstoque {

    private final ServiceMovimentoEstoque serviceMovimentoEstoque;

    @Autowired
    public ControllerMovimentoEstoque(ServiceMovimentoEstoque serviceMovimentoEstoque) {
        this.serviceMovimentoEstoque = serviceMovimentoEstoque;
    }

    @PostMapping("save")
    public ResponseEntity<?> post(@RequestBody MovimentoEstoqueDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceMovimentoEstoque.salvarMovimentacao(dto));
    }

    @GetMapping("list")
    public ResponseEntity<?> getMovimentacoes() {
        return ResponseEntity.ok().body(serviceMovimentoEstoque.buscaListaMovimentacoes());
    }

}
