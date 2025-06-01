package com.icon.desafio.dto;

import com.icon.desafio.model.TipoProdutoModel;

public record TipoProdutoDTO(
        Long id,
        String nome) {
    public TipoProdutoDTO(TipoProdutoModel model) {
        this(
                model.getId(),
                model.getNome());
    }

}
