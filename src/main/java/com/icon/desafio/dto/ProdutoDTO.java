package com.icon.desafio.dto;

import java.math.BigDecimal;

import com.icon.desafio.model.ProdutoModel;

public record ProdutoDTO(
        Long id,
        Long codigo,
        String descricao,
        TipoProdutoDTO tipoProduto,
        BigDecimal precoFornecedor,
        Integer quantidade) {
    public ProdutoDTO(ProdutoModel model) {
        this(
                model.getId(),
                model.getCodigo(),
                model.getDescricao(),
                new TipoProdutoDTO(model.getTipoProduto()),
                model.getPrecoFornecedor(),
                model.getQuantidade());
    }

}
