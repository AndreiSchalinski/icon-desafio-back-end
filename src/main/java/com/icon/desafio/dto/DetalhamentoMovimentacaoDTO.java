package com.icon.desafio.dto;

import java.math.BigDecimal;

public record DetalhamentoMovimentacaoDTO(
    Long codigo,
    String descricao,
    String tipoProduto,
    BigDecimal precoFornecedor,
    Integer quantidadeMovimentada
) {
    public DetalhamentoMovimentacaoDTO(
        Long codigo,
        String descricao,
        String tipoProduto,
        BigDecimal precoFornecedor,
        Integer quantidadeMovimentada
    ) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.tipoProduto = tipoProduto;
        this.precoFornecedor = precoFornecedor;
        this.quantidadeMovimentada = quantidadeMovimentada;
    }
}
