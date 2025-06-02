package com.icon.desafio.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.icon.desafio.dto.MovimentoEstoqueDTO;
import com.icon.desafio.dto.ProdutoDTO;
import com.icon.desafio.enums.TipoMovimentacao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movimento_estoque")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class MovimentoEstoqueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private ProdutoModel produto;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipoMovimentacao;

    private BigDecimal valorVenda;

    private LocalDate dataVenda;

    private Integer quantidadeMovimentada;

    public MovimentoEstoqueModel(MovimentoEstoqueDTO dto) {

        this.id = dto.id();

        if (dto.produtoId() != null) {
            this.produto = new ProdutoModel();
            this.produto.setId(dto.produtoId());
        }

        this.tipoMovimentacao = dto.tipoMovimentacao();
        this.valorVenda = dto.valorVenda();
        this.dataVenda = dto.dataVenda();
        this.quantidadeMovimentada = dto.quantidadeMovimentada();
    }

}
