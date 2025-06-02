package com.icon.desafio.dto;

import java.math.BigDecimal;

public record MovimentacaoRelatorioDTO(
    String descricao,
    Long totalQuantidadeMovimentada,
    BigDecimal totalLucroBruto,
    BigDecimal totalLucroLiquido,
    BigDecimal totalFornecedor
) {}