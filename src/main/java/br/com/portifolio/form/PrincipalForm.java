package br.com.portifolio.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PrincipalForm extends JFrame {
    private JPanel pnlForm;
    protected JButton btnProduto;
    protected JButton btnMovimentoEstoque;

    public PrincipalForm() {
        this.inicializar();
        this.eventos();
    }

    private void inicializar() {
        this.setTitle("Gerenciamento de Estoque Simples");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.setResizable(false);
        this.buildPnlForm();
        this.getContentPane().add(pnlForm, BorderLayout.CENTER);
        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
    }

    public void buildPnlForm() {
        if (pnlForm == null) {
            pnlForm = new JPanel(new FlowLayout());

            btnProduto = new JButton("Gerenciar Produto");
            btnProduto.setPreferredSize(new Dimension(300, 30));
            btnProduto.setBackground(Color.lightGray);

            btnMovimentoEstoque = new JButton("Gerenciar Estoque");
            btnMovimentoEstoque.setPreferredSize(new Dimension(300, 30));
            btnMovimentoEstoque.setBackground(Color.lightGray);

            pnlForm.add(btnProduto);
            pnlForm.add(btnMovimentoEstoque);
        }
    }

    private void eventos() {
        btnMovimentoEstoque.addActionListener(this::btnMovimentoEstoqueClick);
        btnProduto.addActionListener(this::btnProdutoClick);
    }

    private void btnMovimentoEstoqueClick(ActionEvent ev) {
        MovimentoForm movimentoForm = new MovimentoForm();
        movimentoForm.setVisible(true);
    }

    private void btnProdutoClick(ActionEvent ev) {
        ProdutoForm produtoForm = new ProdutoForm();
        produtoForm.setVisible(true);
    }
}
