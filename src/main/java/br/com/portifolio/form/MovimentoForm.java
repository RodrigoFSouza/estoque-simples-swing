package br.com.portifolio.form;

import br.com.portifolio.components.ComboBoxFilterDecorator;
import br.com.portifolio.components.CustomComboRenderer;
import br.com.portifolio.controller.MovimentoEstoqueController;
import br.com.portifolio.controller.ProdutoController;
import br.com.portifolio.entity.MovimentoEstoque;
import br.com.portifolio.entity.Produto;
import br.com.portifolio.entity.enums.TipoMovimentacao;
import br.com.portifolio.tablemodel.MovimentoEstoqueTableModel;
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

public class MovimentoForm extends EstoqueForm {
    private static final String TITLE = "Movimentação de Estoque";
    private JLabel lblProduto;
    private JLabel lblQuantidade;
    private JLabel lblDataMovimentacao;
    private JLabel lblTipoMovimentacao;
    private JComboBox cbProduto;
    private JTextField txtQuantidade;
    private JDatePickerImpl dpDataMovimentacao;
    private JComboBox cbTipoMovimentacao;

    private MovimentoEstoqueController movimentoEstoqueController;
    private ProdutoController produtoController;

    private List<MovimentoEstoque> movimentosEstoque;

    public MovimentoForm() {
        this.inicializar();
    }

    protected void inicializar() {
        this.setTitle(TITLE);
        this.movimentoEstoqueController = new MovimentoEstoqueController();
        this.produtoController = new ProdutoController();
        super.inicializar();
    }

    @Override
    protected void btnSalvarClick(ActionEvent ev) {
        if (cbProduto.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um produto!");
            return;
        }
        if (dpDataMovimentacao.getModel().getValue() == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma data!");
            return;
        }

        if (txtQuantidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a quantidade!");
            return;
        }
        if (cbTipoMovimentacao.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um tipo de movimentação!");
            return;
        }

        BigDecimal quantidade = ConversorUtil.toBigDecimal(txtQuantidade.getText());
        Produto produto = (Produto) cbProduto.getSelectedItem();

        Date selectedDate = (Date) dpDataMovimentacao.getModel().getValue();
        LocalDate dataMovimentacao = selectedDate.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
        String tipoMovimentacao = (String) cbTipoMovimentacao.getSelectedItem();
        TipoMovimentacao tipoMovimentacaoEnum = TipoMovimentacao.valueOf(tipoMovimentacao);

        MovimentoEstoque novoMovimentoEstoque = new MovimentoEstoque(produto, quantidade, dataMovimentacao, tipoMovimentacaoEnum);
        if (!txtId.getText().isEmpty()) {
            novoMovimentoEstoque.setId(Long.parseLong(txtId.getText()));
        }
        movimentoEstoqueController.salvarOuEditarMovimentoEstoque(novoMovimentoEstoque);

        JOptionPane.showMessageDialog(this, "Registro salvo com sucesso!");
        refreshTable();
        btnLimparClick(ev);
    }

    @Override
    protected void btnAtualizarClick(ActionEvent ev) {
        int rowIndex = dataTable.getSelectedRow();
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para atualizar");
            return;
        }
        MovimentoEstoque movimentoEstoque = new MovimentoEstoqueTableModel(movimentosEstoque).get(rowIndex);
        setValoresForm(movimentoEstoque);
    }

    @Override
    protected void btnLimparClick(ActionEvent ev) {
        cbProduto.setSelectedItem(null);
        dpDataMovimentacao.getModel().setValue(null);
        txtQuantidade.setText("");
        cbTipoMovimentacao.setSelectedItem(null);
    }

    @Override
    protected void buildForm() {
        if (pnlForm == null) {
            pnlForm = new JPanel(new MigLayout());
            pnlForm.setBorder(BorderFactory.createTitledBorder("Formulário"));
            pnlForm.setBounds(5, 0 , 640, 400);
        }

        lblProduto = new JLabel("Produto");
        List<Produto> produtos = produtoController.listarTodosProdutos();
        cbProduto = new JComboBox<>(
                produtos.toArray(new Produto[produtos.size()]));

        ComboBoxFilterDecorator<Produto> decorate = ComboBoxFilterDecorator.decorate(cbProduto,
                CustomComboRenderer::getProdutoDisplayText,
                MovimentoForm::produtoFilter);

        cbProduto.setRenderer(new CustomComboRenderer(decorate.getFilterTextSupplier()));

        lblQuantidade = new JLabel("QUANTIDADE");
        txtQuantidade = new JTextField(TAMANHO_TXT);

        lblDataMovimentacao = new JLabel("DATA DE CADASTRO");
        UtilDateModel model = new UtilDateModel();
        LocalDate now = LocalDate.now();

        model.setDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
        dpDataMovimentacao = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        lblTipoMovimentacao = new JLabel("TIPO DE MOVIMENTAÇÃO");
        String[] tipoMovimentacao = new String[] { "ENTRADA", "SAIDA" };
        cbTipoMovimentacao = new JComboBox<>(tipoMovimentacao);

        pnlForm.add(lblProduto);
        pnlForm.add(cbProduto, "span, growx, wrap");

        pnlForm.add(lblQuantidade);
        pnlForm.add(txtQuantidade, "span, growx, wrap");

        pnlForm.add(lblDataMovimentacao);
        pnlForm.add(dpDataMovimentacao, "span, growx, wrap");

        pnlForm.add(lblTipoMovimentacao);
        pnlForm.add(cbTipoMovimentacao, "span, growx, wrap");

    }

    private static boolean produtoFilter(Produto emp, String textToFilter) {
        if (textToFilter.isEmpty()) {
            return true;
        }
        return CustomComboRenderer.getProdutoDisplayText(emp).toLowerCase()
                .contains(textToFilter.toLowerCase());
    }

    @Override
    protected void refreshTable() {
        movimentosEstoque = movimentoEstoqueController.listarTodosMovimentosEstoque();
        if (!movimentosEstoque.isEmpty()) {
            dataTable.setModel(new MovimentoEstoqueTableModel(movimentosEstoque));
        }
    }

    protected void setValoresForm(MovimentoEstoque movimentoEstoque) {
        cbProduto.setSelectedItem(movimentoEstoque.getProduto());
        dpDataMovimentacao.getModel().setDate(
                movimentoEstoque.getDataMovimento().getYear(),
                movimentoEstoque.getDataMovimento().getMonthValue(),
                movimentoEstoque.getDataMovimento().getDayOfMonth());
        dpDataMovimentacao.getModel().setSelected(true);
        txtQuantidade.setText(movimentoEstoque.getQuantidade().toString());
        cbTipoMovimentacao.setSelectedItem(movimentoEstoque.getTipoMovimentacao().toString());

        txtId.setText(movimentoEstoque.getId().toString());
    }
}
