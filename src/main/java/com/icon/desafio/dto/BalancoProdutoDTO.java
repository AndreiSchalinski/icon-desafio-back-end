package com.icon.desafio.dto;

public record BalancoProdutoDTO(
        ProdutoBalancoDTO produto,
        Long totalSaidas,
        Long totalEntradas,
        Integer quantidadeDisponicel) {

    public BalancoProdutoDTO(
            Long produtoId,
            String descricao,
            Long tipoProdutoId,
            String tipoProdutoNome,
            Long totalSaidas,
            Long totalEntradas,
            Integer quantidadeDisponivel) {
        this(
                new ProdutoBalancoDTO(
                        produtoId,
                        descricao,
                        tipoProdutoId != null ? new TipoProdutoDTO(tipoProdutoId, tipoProdutoNome) : null),
                        totalSaidas,
                        totalEntradas,
                        quantidadeDisponivel);
                        
    }
}
