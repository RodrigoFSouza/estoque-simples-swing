package br.com.portifolio.tablemodel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.math.BigDecimal;

public class ProdutoCellRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        BigDecimal quantidadeMinima = (BigDecimal)table.getModel().getValueAt(row, 4);
        BigDecimal quantidadeAtual = (BigDecimal) table.getModel().getValueAt(row, 5);
        if (quantidadeAtual.compareTo(quantidadeMinima) < 0) {
            setBackground(Color.RED);
        } else {
            setBackground(null);
        }
        return this;
    }
}
