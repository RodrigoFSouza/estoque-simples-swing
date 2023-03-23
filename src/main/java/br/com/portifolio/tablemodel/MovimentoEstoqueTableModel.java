package br.com.portifolio.tablemodel;

import br.com.portifolio.entity.MovimentoEstoque;
import br.com.portifolio.entity.enums.TipoMovimentacao;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class MovimentoEstoqueTableModel extends AbstractTableModel {

    private static final int COL_ID = 0;
    private static final int COL_PRODUTO = 1;
    private static final int COL_QUANTIDADE = 2;
    private static final int COL_DATA_MOVIMENTO = 3;
    private static final int COL_TIPO_MOVIMENTACAO = 4;

    private List<MovimentoEstoque> movimentosEstoque;

    public MovimentoEstoqueTableModel(List<MovimentoEstoque> movimentosEstoque) {
        this.movimentosEstoque = movimentosEstoque;
    }

    @Override
    public int getRowCount() {
        return this.movimentosEstoque.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MovimentoEstoque movimentoEstoque = movimentosEstoque.get(rowIndex);
        if (columnIndex == COL_ID) {
            return movimentoEstoque.getId();
        } else if (columnIndex == COL_PRODUTO) {
            return movimentoEstoque.getProduto().getDescricao();
        } else if (columnIndex == COL_QUANTIDADE) {
            return movimentoEstoque.getQuantidade();
        } else if (columnIndex == COL_DATA_MOVIMENTO) {
            return movimentoEstoque.getDataMovimento();
        } else if (columnIndex == COL_TIPO_MOVIMENTACAO) {
            return movimentoEstoque.getTipoMovimentacao();
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
            case COL_PRODUTO:
                coluna = "Produto";
                break;
            case COL_QUANTIDADE:
                coluna = "Qtd";
                break;
            case COL_DATA_MOVIMENTO:
                coluna = "Dt. Movimento";
                break;
            case COL_TIPO_MOVIMENTACAO:
                coluna = "Tipo Movimentação";
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
        } else if (columnIndex == COL_PRODUTO) {
            return String.class;
        } else if (columnIndex == COL_QUANTIDADE) {
            return BigDecimal.class;
        } else if (columnIndex == COL_DATA_MOVIMENTO) {
            return LocalDate.class;
        } else if (columnIndex == COL_TIPO_MOVIMENTACAO) {
            return TipoMovimentacao.class;
        }
        return null;
    }

    public MovimentoEstoque get(int rowIndex) {
        return this.movimentosEstoque.get(rowIndex);
    }
}
