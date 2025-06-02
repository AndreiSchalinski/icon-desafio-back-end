package com.icon.desafio.dto;

import com.icon.desafio.model.ProdutoModel;

public record ProdutoBalancoDTO(
        Long id,
        String descricao,
        TipoProdutoDTO tipoProduto

) {
    public ProdutoBalancoDTO(ProdutoModel model) {
        this(
                model.getId(),
                model.getDescricao(),
                new TipoProdutoDTO(model.getTipoProduto()));

    }
}
