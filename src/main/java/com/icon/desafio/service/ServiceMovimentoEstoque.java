package com.icon.desafio.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icon.desafio.dto.MovimentoEstoqueDTO;
import com.icon.desafio.model.MovimentoEstoqueModel;
import com.icon.desafio.repository.RepositoryMovimentoEstoque;

@Service
public class ServiceMovimentoEstoque {

    private final RepositoryMovimentoEstoque repositoryMovimentoEstoque;

    @Autowired
    public ServiceMovimentoEstoque(RepositoryMovimentoEstoque repositoryMovimentoEstoque){
        this.repositoryMovimentoEstoque = repositoryMovimentoEstoque;
    }

    public MovimentoEstoqueDTO salvarMovimentacao(MovimentoEstoqueDTO dto){
        MovimentoEstoqueModel savedModel = repositoryMovimentoEstoque.save(new MovimentoEstoqueModel(dto));
        return new MovimentoEstoqueDTO(savedModel);
    }

    public Set<MovimentoEstoqueDTO> buscaListaMovimentacoes(){
        return repositoryMovimentoEstoque.findAll().stream().map(MovimentoEstoqueDTO::new).collect(Collectors.toSet());
    }
    
}
