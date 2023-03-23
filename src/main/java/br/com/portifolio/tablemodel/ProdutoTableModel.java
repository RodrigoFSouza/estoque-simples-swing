package br.com.portifolio.tablemodel;

import br.com.portifolio.entity.Produto;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoTableModel extends AbstractTableModel {
    private static final int COL_ID = 0;
    private static final int COL_DESCRICAO = 1;
    private static final int COL_VALOR = 2;
    private static final int COL_DATA_CADASTRO = 3;
    private static final int COL_QUANTIDADE_MINIMA = 4;
    private static final int COL_QUANTIDADE_ATUAL = 5;

    private List<Produto> produtos;

    public ProdutoTableModel(List<Produto> produtos) {
        this.produtos = produtos;
    }


    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = produtos.get(rowIndex);
        if (columnIndex == COL_ID) {
            return produto.getId();
        } else if (columnIndex == COL_DESCRICAO) {
            return produto.getDescricao();
        } else if (columnIndex == COL_VALOR) {
            return produto.getValor();
        } else if (columnIndex == COL_QUANTIDADE_MINIMA) {
            return produto.getQuantidadeMinima();
        } else if (columnIndex == COL_DATA_CADASTRO) {
            return produto.getDataCadastro();
        } else if (columnIndex == COL_QUANTIDADE_ATUAL) {
            return produto.getQuantidadeAtual();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_ID:
                coluna = "ID";
                break;
            case COL_DESCRICAO:
                coluna = "Descrição";
                break;
            case COL_VALOR:
                coluna = "Valor";
                break;
            case COL_QUANTIDADE_MINIMA:
                coluna = "Qtd. Min";
                break;
            case COL_QUANTIDADE_ATUAL:
                coluna = "Qtd. Atual";
                break;
            case COL_DATA_CADASTRO:
                coluna = "Dt. Cadastro";
                break;
            default:
                throw new IllegalArgumentException("Coluna Inválida");
        }

        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_ID) {
            return Long.class;
        } else if (columnIndex == COL_DESCRICAO) {
            return String.class;
        } else if (columnIndex == COL_VALOR) {
            return BigDecimal.class;
        } else if (columnIndex == COL_QUANTIDADE_MINIMA) {
            return BigDecimal.class;
        } else if (columnIndex == COL_QUANTIDADE_ATUAL) {
            return BigDecimal.class;
        } else if (columnIndex == COL_DATA_CADASTRO) {
            return LocalDate.class;
        }
        return null;
    }

    public Produto get(int row) {
        return produtos.get(row);
    }
}
