package com.icon.desafio.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icon.desafio.dto.MovimentoEstoqueDTO;
import com.icon.desafio.dto.BalancoProdutoDTO;
import com.icon.desafio.dto.ProdutoDTO;
import com.icon.desafio.enums.TipoMovimentacao;
import com.icon.desafio.model.ProdutoModel;
import com.icon.desafio.model.TipoProdutoModel;
import com.icon.desafio.repository.RepositoryProduto;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiceProduto {

    private final RepositoryProduto repositoryProduto;

    @Autowired
    public ServiceProduto(RepositoryProduto repositoryProduto) {
        this.repositoryProduto = repositoryProduto;
    }

    public ProdutoModel salvarProduto(ProdutoDTO produtoDTO) {
        return repositoryProduto.save(new ProdutoModel(produtoDTO));
    }

    public ProdutoModel alterarProduto(Long id, ProdutoDTO dto) {

        ProdutoModel produto = repositoryProduto.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado para editar cadastro!"));

        produto.setCodigo(dto.codigo());
        produto.setDescricao(dto.descricao());
        produto.setTipoProduto(new TipoProdutoModel(dto.tipoProduto()));
        produto.setPrecoFornecedor(dto.precoFornecedor());
        produto.setQuantidade(dto.quantidade());

        return repositoryProduto.save(produto);

    }

    public ProdutoDTO buscarProdutoID(Long id) {

        Optional<ProdutoModel> optionalProduto = repositoryProduto.findById(id);

        ProdutoDTO dto = new ProdutoDTO(optionalProduto.get());

        return dto;
    }

    public Set<ProdutoDTO> carregarListaProdutos() {
        return repositoryProduto.findAll().stream()
                .map(ProdutoDTO::new).collect(Collectors.toSet());
    }

    public void deleteProduto(Long id) {
        Optional<ProdutoModel> produtoModel = repositoryProduto.findById(id);

        if (produtoModel.isPresent()) {
            repositoryProduto.delete(produtoModel.get());
        } else {
            throw new EntityNotFoundException("Produto com ID " + id + " não encontrado.");
        }
    }

    public Integer calcularSaldoMovimentoEstoque(MovimentoEstoqueDTO dto) {

        ProdutoModel produto = repositoryProduto.findById(dto.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado para editar cadastro!"));

        if (dto.tipoMovimentacao() == TipoMovimentacao.ENTRADA) {
            return Integer.sum(produto.getQuantidade(), dto.quantidadeMovimentada());
        } else {
            return produto.getQuantidade() - dto.quantidadeMovimentada();
        }

    }

    public void atualizaSaldoProduto(ProdutoModel model) {
        repositoryProduto.save(model);
    }

    public Set<BalancoProdutoDTO> buscarProdutoPorTipo(Long idTipoProduto) {

        Set<Object[]> listProdutosTipo = repositoryProduto.consultarProdutosPorTipoComQuantidades(idTipoProduto);

        Set<BalancoProdutoDTO> dtos = new HashSet<>();

        for (Object[] row : listProdutosTipo) {

            Long produtoId = (Long) row[0];
            String descricao = (String) row[1];
            Long tipoProdutoId = (Long) row[2];
            String tipoProdutoNome = (String) row[3];
            Long totalSaidas = (Long) row[4];
            Long totalEntradas = (Long) row[5];
            Integer saldoProdutoDisponivel = (Integer) row[6];

            BalancoProdutoDTO dto = new BalancoProdutoDTO(
                    produtoId,
                    descricao,
                    tipoProdutoId,
                    tipoProdutoNome,
                    totalSaidas,
                    totalEntradas,
                    saldoProdutoDisponivel);

            dtos.add(dto);
        }

        return dtos;
    }

}