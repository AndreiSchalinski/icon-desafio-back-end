package com.icon.desafio.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.icon.desafio.model.ProdutoModel;

@Repository
public interface RepositoryProduto extends JpaRepository<ProdutoModel, Long> {

    @Query("""
                SELECT
                    p.id,
                    p.descricao,
                    p.tipoProduto.id,
                    p.tipoProduto.nome,
                    SUM(CASE WHEN m.tipoMovimentacao = com.icon.desafio.enums.TipoMovimentacao.SAIDA THEN m.quantidadeMovimentada ELSE 0 END),
                    SUM(CASE WHEN m.tipoMovimentacao = com.icon.desafio.enums.TipoMovimentacao.ENTRADA THEN m.quantidadeMovimentada ELSE 0 END),
                    p.quantidade
                FROM ProdutoModel p
                LEFT JOIN MovimentoEstoqueModel m ON m.produto = p
                WHERE (:tipoId IS NULL OR p.tipoProduto.id = :tipoId)
                GROUP BY p.id, p.descricao, p.tipoProduto.id, p.tipoProduto.nome
            """)
    Set<Object[]> consultarProdutosPorTipoComQuantidades(@Param("tipoId") Long tipoId);

}
