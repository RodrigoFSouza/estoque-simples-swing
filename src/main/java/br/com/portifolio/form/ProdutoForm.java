package br.com.portifolio.form;

import br.com.portifolio.components.JtextFieldSomenteNumeros;
import br.com.portifolio.controller.ProdutoController;
import br.com.portifolio.entity.Produto;
import br.com.portifolio.tablemodel.ProdutoCellRender;
import br.com.portifolio.tablemodel.ProdutoTableModel;
import br.com.portifolio.util.ConversorUtil;
import br.com.portifolio.util.DateLabelFormatter;
import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class ProdutoForm extends EstoqueForm {
    private static final String TITLE = "Cadastro de Produto";
    private JLabel lblDescricao;
    private JLabel lblValor;
    private JLabel lblQuantidadeMinima;
    private JLabel lblDataCadastro;
    private JLabel lblQuantidadeAtual;
    private JTextField txtQuantidadeAtual;
    private JTextField txtDescricao;
    private JtextFieldSomenteNumeros txtValor;
    private JtextFieldSomenteNumeros txtQuantidadeMinima;
    private JDatePickerImpl dpDataCadastro;
    private ProdutoController produtoController;
    private List<Produto> produtoList;

    public ProdutoForm() {
        this.inicializar();
    }

    protected void inicializar() {
        this.setTitle(TITLE);
        produtoController = new ProdutoController();
        super.inicializar();
    }
    @Override
    protected void buildForm() {
        if (pnlForm == null) {
            pnlForm = new JPanel(new MigLayout());
            pnlForm.setBorder(BorderFactory.createTitledBorder("Formulário"));
            pnlForm.setBounds(5, 0 , 640, 400);
        }
        lblDescricao = new JLabel("DESCRIÇÃO");
        txtDescricao = new JTextField(TAMANHO_TXT);

        lblValor = new JLabel("VALOR");
        txtValor = new JtextFieldSomenteNumeros();

        lblQuantidadeMinima = new JLabel("QUANTIDADE MÍNIMA");
        txtQuantidadeMinima = new JtextFieldSomenteNumeros();

        lblQuantidadeAtual = new JLabel("QUANTIDADE ATUAL");
        txtQuantidadeAtual = new JtextFieldSomenteNumeros();

        lblDataCadastro = new JLabel("DATA DE CADASTRO");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
        dpDataCadastro = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        pnlForm.add(lblDescricao);
        pnlForm.add(txtDescricao, "span, growx, wrap");

        pnlForm.add(lblValor);
        pnlForm.add(txtValor, "span, growx, wrap");

        pnlForm.add(lblQuantidadeMinima);
        pnlForm.add(txtQuantidadeMinima, "span, growx, wrap");

        pnlForm.add(lblQuantidadeAtual);
        pnlForm.add(txtQuantidadeAtual, "span, growx, wrap");

        pnlForm.add(lblDataCadastro);
        pnlForm.add(dpDataCadastro, "span, growx, wrap");
    }

    @Override
    protected void btnSalvarClick(ActionEvent ev) {
        if (txtDescricao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo descrição é obrigatório");
            return;
        }

        if (txtValor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo valor é obrigatório");
            return;
        }

        if (txtQuantidadeMinima.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo quantidade mínima é obrigatório");
            return;
        }

        if (txtQuantidadeAtual.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo quantidade atual é obrigatório");
            return;
        }

        if (dpDataCadastro.getModel().getValue() == null) {
            JOptionPane.showMessageDialog(this, "O campo data de cadastro é obrigatório");
            return;
        }

        BigDecimal valor = ConversorUtil.toBigDecimal(txtValor.getText());
        BigDecimal quantidadeMinima = ConversorUtil.toBigDecimal(txtQuantidadeMinima.getText());
        BigDecimal quantidadeAtual = ConversorUtil.toBigDecimal(txtQuantidadeAtual.getText());

        if (quantidadeAtual.compareTo(quantidadeMinima) < 0) {
            JOptionPane.showMessageDialog(this, "O Campo quantidade Atual deve ser maior que o mínimo");
            return;
        }

        Date selectedDate = (Date) dpDataCadastro.getModel().getValue();
        LocalDate dataCadastro = selectedDate.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
        String descricao = txtDescricao.getText();

        Produto novoProduto = new Produto(descricao, dataCadastro, valor, quantidadeMinima, quantidadeAtual);
        if (!txtId.getText().isEmpty()) {
            novoProduto.setId(Long.parseLong(txtId.getText()));
        }
        produtoController.salvarOuEditarProduto(novoProduto);

        JOptionPane.showMessageDialog(this, "Registro salvo com sucesso!");
        refreshTable();
        btnLimparClick(ev);
    }

    @Override
    protected void btnAtualizarClick(ActionEvent ev) {
        txtQuantidadeAtual.setEnabled(false);
        int rowIndex = dataTable.getSelectedRow();
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para atualizar");
            return;
        }
        Produto produto = new ProdutoTableModel(produtoList).get(rowIndex);
        setValoresForm(produto);
    }

    @Override
    protected void btnLimparClick(ActionEvent ev) {
        txtQuantidadeAtual.setEnabled(true);
        txtQuantidadeAtual.setText("");
        txtQuantidadeMinima.setText("");
        txtId.setText("");
        txtDescricao.setText("");
        txtValor.setText("");
        dpDataCadastro.getModel().setValue(null);
    }

    @Override
    protected void refreshTable() {
        produtoList = produtoController.listarTodosProdutos();
        if (!produtoList.isEmpty()) {
            dataTable.setModel(new ProdutoTableModel(produtoList));
            dataTable.setDefaultRenderer(Object.class, new ProdutoCellRender());
        }
    }

    protected void setValoresForm(Produto produto) {
        txtQuantidadeMinima.setText(produto.getQuantidadeMinima().toString());
        txtQuantidadeAtual.setText(produto.getQuantidadeAtual().toString());
        txtId.setText(produto.getId().toString());
        txtDescricao.setText(produto.getDescricao());
        txtValor.setText(produto.getValor().toString());
        dpDataCadastro.getModel().setDate(
                produto.getDataCadastro().getYear(),
                produto.getDataCadastro().getMonthValue(),
                produto.getDataCadastro().getDayOfMonth());
        dpDataCadastro.getModel().setSelected(true);
    }
}
