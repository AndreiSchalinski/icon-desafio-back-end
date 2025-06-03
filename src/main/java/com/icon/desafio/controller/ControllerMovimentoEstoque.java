package com.icon.desafio.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icon.desafio.dto.DetalhamentoMovimentacaoDTO;
import com.icon.desafio.dto.MovimentacaoRelatorioDTO;
import com.icon.desafio.dto.MovimentoEstoqueDTO;
import com.icon.desafio.service.ServiceMovimentoEstoque;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/movimentacao")
public class ControllerMovimentoEstoque {

    private final ServiceMovimentoEstoque serviceMovimentoEstoque;

    @Autowired
    public ControllerMovimentoEstoque(ServiceMovimentoEstoque serviceMovimentoEstoque) {
        this.serviceMovimentoEstoque = serviceMovimentoEstoque;
    }

    @PostMapping("save")
    public ResponseEntity<MovimentoEstoqueDTO> post(@RequestBody MovimentoEstoqueDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceMovimentoEstoque.salvarMovimentacao(dto));
    }

    @GetMapping("list")
    public ResponseEntity<Set<MovimentoEstoqueDTO>> getMovimentacoes() {
        return ResponseEntity.ok().body(serviceMovimentoEstoque.buscaListaMovimentacoes());
    }

    @GetMapping("relatorio")
    public ResponseEntity<Set<MovimentacaoRelatorioDTO>> getRelatorioLucro() {
        return ResponseEntity.ok().body(serviceMovimentoEstoque.gerarRelatorioLucros());
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhamentoMovimentacaoDTO> buscarDetalhamento(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(serviceMovimentoEstoque.buscarDetalhamentoMovimentacao(id));
    }

}
