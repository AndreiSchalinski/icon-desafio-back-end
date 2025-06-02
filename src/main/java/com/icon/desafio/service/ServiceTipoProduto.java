package com.icon.desafio.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icon.desafio.dto.TipoProdutoDTO;
import com.icon.desafio.model.TipoProdutoModel;
import com.icon.desafio.repository.RepositoryTipoProduto;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiceTipoProduto {

    private final RepositoryTipoProduto repositoryTipoProduto;

    @Autowired
    public ServiceTipoProduto(RepositoryTipoProduto repositoryTipoProduto) {
        this.repositoryTipoProduto = repositoryTipoProduto;
    }

    public TipoProdutoModel salvarTipoProduto(TipoProdutoDTO tipoProdutoDTO) {
        return repositoryTipoProduto.save(new TipoProdutoModel(tipoProdutoDTO));
    }

    public TipoProdutoModel alterarTipoProduto(Long id, TipoProdutoDTO tipoProdutoDTO) {

        TipoProdutoModel tipoProduto = repositoryTipoProduto.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo produto com ID " + id + " não encontrado."));

        BeanUtils.copyProperties(tipoProdutoDTO, tipoProduto, "id");

        return repositoryTipoProduto.save(tipoProduto);
    }

    public Optional<TipoProdutoModel> buscarTipoProdutoID(Long id) {
        return repositoryTipoProduto.findById(id);
    }

    public Set<TipoProdutoDTO> carregarListaTipoProdutos() {
        return repositoryTipoProduto.findAll().stream()
                .map(TipoProdutoDTO::new).collect(Collectors.toSet());
    }

    public void deleteTipoProduto(Long id) {
        Optional<TipoProdutoModel> tipoProduto = repositoryTipoProduto.findById(id);

        if (tipoProduto.isPresent()) {
            repositoryTipoProduto.delete(tipoProduto.get());
        } else {
            throw new EntityNotFoundException("Tipo de produto com ID " + id + " não encontrado.");
        }

    }

}
