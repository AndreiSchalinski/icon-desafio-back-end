package com.icon.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icon.desafio.model.ProdutoModel;

@Repository
public interface RepositoryProduto extends JpaRepository<ProdutoModel, Long> {

}
