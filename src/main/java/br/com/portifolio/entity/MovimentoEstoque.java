package br.com.portifolio.entity;

import br.com.portifolio.entity.enums.TipoMovimentacao;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_movimento_estoque")
public class MovimentoEstoque implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movimento_estoque_seq_gen")
    @SequenceGenerator(name = "movimento_estoque_seq_gen", sequenceName = "tb_movimento_estoque_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto")
    private Produto produto;

    @Column(name = "quantidade")
    private BigDecimal quantidade;
    @Column(name = "data_movimento")
    private LocalDate dataMovimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao")
    private TipoMovimentacao tipoMovimentacao;

    public MovimentoEstoque() {
    }

    public MovimentoEstoque(Produto produto, BigDecimal quantidade, LocalDate dataMovimento, TipoMovimentacao tipoMovimentacao) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataMovimento = dataMovimento;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDate dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimentoEstoque that = (MovimentoEstoque) o;
        return id.equals(that.id) && produto.equals(that.produto) && quantidade.equals(that.quantidade) && dataMovimento.equals(that.dataMovimento) && tipoMovimentacao == that.tipoMovimentacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produto, quantidade, dataMovimento, tipoMovimentacao);
    }
}
