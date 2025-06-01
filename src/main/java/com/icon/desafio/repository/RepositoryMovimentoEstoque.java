package com.icon.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icon.desafio.model.MovimentoEstoqueModel;

public interface RepositoryMovimentoEstoque extends JpaRepository<MovimentoEstoqueModel, Long> {
    
}
