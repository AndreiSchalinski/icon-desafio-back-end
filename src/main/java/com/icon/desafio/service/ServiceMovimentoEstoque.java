package com.icon.desafio.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.icon.desafio.dto.MovimentacaoRelatorioDTO;
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

        Optional<ProdutoModel> produtoOpt = serviceProduto.buscarProdutoID(dto.produtoId());
        if (produtoOpt.isEmpty()) {
            return null;
        }

        ProdutoModel produto = produtoOpt.get();

        Integer saldoPrevisionado = serviceProduto.calcularSaldoMovimentoEstoque(dto);

        if (dto.tipoMovimentacao() == TipoMovimentacao.SAIDA && saldoPrevisionado < 0) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente para a saída do produto.");

        }

        produto.setQuantidade(saldoPrevisionado);
        serviceProduto.atualizaSaldoProduto(produto);

        MovimentoEstoqueModel savedModel = repositoryMovimentoEstoque.save(new MovimentoEstoqueModel(dto));
        return new MovimentoEstoqueDTO(savedModel);
    }

    public Set<MovimentoEstoqueDTO> buscaListaMovimentacoes() {
        return repositoryMovimentoEstoque.findAll().stream().map(MovimentoEstoqueDTO::new).collect(Collectors.toSet());
    }

    public List<MovimentacaoRelatorioDTO> gerarRelatorioLucros() {

        List<Object[]> resultados = repositoryMovimentoEstoque.gerarRelatorioLucros();

        List<MovimentacaoRelatorioDTO> dtos = new ArrayList<>();

        for (Object[] row : resultados) {

            String descricao = (String) row[0];
            Long totalQuantidadeMovimentada = (Long) row[1];
            BigDecimal totalLucroBruto = (BigDecimal) row[2];
            BigDecimal totalLucroLiquido = (BigDecimal) row[3];
            BigDecimal totalFornecedor = (BigDecimal) row[4];

            MovimentacaoRelatorioDTO dto = new MovimentacaoRelatorioDTO(
                    descricao,
                    totalQuantidadeMovimentada,
                    totalLucroBruto,
                    totalLucroLiquido,
                    totalFornecedor);

            dtos.add(dto);
        }

        return dtos;
    }

}
