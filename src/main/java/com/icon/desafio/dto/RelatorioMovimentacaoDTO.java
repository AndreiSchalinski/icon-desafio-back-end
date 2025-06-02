package com.icon.desafio.dto;

import java.math.BigDecimal;

public record RelatorioMovimentacaoDTO(BigDecimal lucroProduto, Integer quantidadeTotalSaida,
        BigDecimal totalLucroLiquido) {

}
