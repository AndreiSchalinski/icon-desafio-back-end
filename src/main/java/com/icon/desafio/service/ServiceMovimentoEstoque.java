package com.icon.desafio.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icon.desafio.dto.MovimentoEstoqueDTO;
import com.icon.desafio.enums.TipoMovimentacao;
import com.icon.desafio.model.MovimentoEstoqueModel;
import com.icon.desafio.model.ProdutoModel;
import com.icon.desafio.repository.RepositoryMovimentoEstoque;

@Service
public class ServiceMovimentoEstoque {

    private final RepositoryMovimentoEstoque repositoryMovimentoEstoque;

    private final ServiceProduto serviceProduto;

    @Autowired
    public ServiceMovimentoEstoque(RepositoryMovimentoEstoque repositoryMovimentoEstoque,
            ServiceProduto serviceProduto) {
        this.repositoryMovimentoEstoque = repositoryMovimentoEstoque;
        this.serviceProduto = serviceProduto;
    }

    public MovimentoEstoqueDTO salvarMovimentacao(MovimentoEstoqueDTO dto) {
        
        Optional<ProdutoModel> produtoOpt = serviceProduto.buscarProdutoID(dto.produto().id());
        if (produtoOpt.isEmpty()) {
            return null;
        }

        ProdutoModel produto = produtoOpt.get();
        TipoMovimentacao tipo = dto.tipoMovimentacao();

        Integer saldoPrevisionado = serviceProduto.calcularSaldoMovimentoEstoque(dto.produto(), tipo.name());

        if (tipo == TipoMovimentacao.SAIDA && saldoPrevisionado <= 0) {
            return null; 
        }

        produto.setQuantidade(saldoPrevisionado);
        serviceProduto.atualizaSaldoProduto(produto);

        MovimentoEstoqueModel savedModel = repositoryMovimentoEstoque.save(new MovimentoEstoqueModel(dto));
        return new MovimentoEstoqueDTO(savedModel);
    }

    public Set<MovimentoEstoqueDTO> buscaListaMovimentacoes() {
        return repositoryMovimentoEstoque.findAll().stream().map(MovimentoEstoqueDTO::new).collect(Collectors.toSet());
    }

}
