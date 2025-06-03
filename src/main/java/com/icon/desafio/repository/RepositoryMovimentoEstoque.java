package com.icon.desafio.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.icon.desafio.model.MovimentoEstoqueModel;

public interface RepositoryMovimentoEstoque extends JpaRepository<MovimentoEstoqueModel, Long> {

    @Query("""
            SELECT p.descricao AS descricao,
                   SUM(m.quantidadeMovimentada) AS totalQuantidadeMovimentada,
                   SUM(m.quantidadeMovimentada * m.valorVenda) AS totalLucroBruto,
                   SUM(m.quantidadeMovimentada * m.valorVenda) - SUM(p.precoFornecedor) AS totalLucroLiquido,
                   SUM(p.precoFornecedor) AS totalFornecedor
            FROM MovimentoEstoqueModel m
            JOIN m.produto p
            WHERE m.tipoMovimentacao = com.icon.desafio.enums.TipoMovimentacao.SAIDA
            GROUP BY p.id
            """)
    Set<Object[]> gerarRelatorioLucros();

    @Query("""
            SELECT p.codigo,
                p.descricao,
                p.tipoProduto.nome,
                p.precoFornecedor,
                m.quantidadeMovimentada
            FROM MovimentoEstoqueModel m
            JOIN m.produto p
            WHERE m.id = :id
            """)
    List<Object[]> buscarDetalhamentoMovimentacao(@Param("id") Long id);

}
