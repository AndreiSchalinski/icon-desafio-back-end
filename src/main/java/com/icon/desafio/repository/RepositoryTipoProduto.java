package com.icon.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icon.desafio.model.TipoProdutoModel;

@Repository
public interface RepositoryTipoProduto extends JpaRepository<TipoProdutoModel, Long> {
    
}
