package br.com.portifolio.components;

import br.com.portifolio.entity.Produto;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class CustomComboRenderer extends DefaultListCellRenderer {
    public static final Color background = new Color(225, 240, 255);
    private static final Color defaultBackground = (Color) UIManager.get("List.background");
    private static final Color defaultForeground = (Color) UIManager.get("List.foreground");
    private Supplier<String> highlightTextSupplier;

    public CustomComboRenderer(Supplier<String> highlightTextSupplier) {
        this.highlightTextSupplier = highlightTextSupplier;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Produto produto = (Produto) value;
        if (produto == null) {
            return this;
        }
        String text = getProdutoDisplayText(produto);
        text = HtmlHighlighter.highlightText(text, highlightTextSupplier.get());
        this.setText(text);
        if (!isSelected) {
            this.setBackground(index % 2 == 0 ? background : defaultBackground);
        }
        this.setForeground(defaultForeground);
        return this;
    }

    public static String getProdutoDisplayText(Produto produto) {
        if (produto == null) {
            return "";
        }
        return String.format("%s", produto.getDescricao());
    }
}