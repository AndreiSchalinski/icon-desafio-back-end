package com.icon.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.icon.desafio.model.MovimentoEstoqueModel;

public interface RepositoryMovimentoEstoque extends JpaRepository<MovimentoEstoqueModel, Long> {

    @Query("SELECT p.descricao AS descricao, SUM(m.quantidadeMovimentada) AS totalQuantidade\r\n" + //
            "    FROM MovimentoEstoqueModel m JOIN m.produto p\r\n" + //
            "    GROUP BY p.descricao")
    List<Object[]> gerarRelatorioLucros();

}
