package br.com.portifolio.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq_gen")
    @SequenceGenerator(name = "produto_seq_gen", sequenceName = "tb_produto_id_seq")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "quantidade_minima")
    private BigDecimal quantidadeMinima;

    @Column(name = "quantidade_atual")
    private BigDecimal quantidadeAtual;

    public Produto() {
    }

    public Produto(String descricao, LocalDate dataCadastro, BigDecimal valor, BigDecimal quantidadeMinima, BigDecimal quantidadeAtual) {
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        this.valor = valor;
        this.quantidadeMinima = quantidadeMinima;
        this.quantidadeAtual = quantidadeAtual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(BigDecimal quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public BigDecimal getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(BigDecimal quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id) && descricao.equals(produto.descricao) && dataCadastro.equals(produto.dataCadastro) && valor.equals(produto.valor) && quantidadeMinima.equals(produto.quantidadeMinima);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, dataCadastro, valor, quantidadeMinima);
    }
}
