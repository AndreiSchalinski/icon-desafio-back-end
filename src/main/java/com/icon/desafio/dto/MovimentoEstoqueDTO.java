package com.icon.desafio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.icon.desafio.enums.TipoMovimentacao;
import com.icon.desafio.model.MovimentoEstoqueModel;

public record MovimentoEstoqueDTO(
        Long id,
        ProdutoDTO produto,
        TipoMovimentacao tipoMovimentacao,
        BigDecimal valorVenda,
        LocalDate dataVenda,
        Integer quantidadeMovimentada) {

    public MovimentoEstoqueDTO(MovimentoEstoqueModel model) {
        this(
            model.getId(),
            new ProdutoDTO(model.getProduto()),
            model.getTipoMovimentacao(),
            model.getValorVenda(),
            model.getDataVenda(),
            model.getQuantidadeMovimentada()
        );
    }
}
