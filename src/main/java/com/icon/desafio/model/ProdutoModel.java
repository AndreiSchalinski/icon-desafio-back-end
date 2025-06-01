package com.icon.desafio.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icon.desafio.dto.ProdutoDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long codigo;

    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_produto_id", referencedColumnName = "id")
    private TipoProdutoModel tipoProduto;

    private BigDecimal precoFornecedor;

    private Integer quantidade;

    @JsonIgnore
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<MovimentoEstoqueModel> movimentos;

    public ProdutoModel(ProdutoDTO dto) {
        this.id = dto.id();
        this.codigo = dto.codigo();
        this.descricao = dto.descricao();

        if (dto.tipoProduto() != null) {
            this.tipoProduto = new TipoProdutoModel();
            this.tipoProduto.setId(dto.tipoProduto().id());
            this.tipoProduto.setNome(dto.tipoProduto().nome());
        }

        this.precoFornecedor = dto.precoFornecedor();
        this.quantidade = dto.quantidade();
    }

}
