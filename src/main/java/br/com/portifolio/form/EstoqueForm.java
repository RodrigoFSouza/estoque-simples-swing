package br.com.portifolio.form;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class EstoqueForm extends JFrame {
    protected static final int TAMANHO_TXT = 20;
    protected JPanel pnlForm;
    protected JPanel pnlLista;
    protected JPanel pnlRodape;
    protected JButton btnSalvar;
    protected JButton btnAtualizar;
    protected JButton btnLimpar;
    protected JButton btnFechar;

    protected JTable dataTable;
    protected JScrollPane scrollPane;

    protected JLabel lblId;
    protected JTextField txtId;

    public EstoqueForm() {
        this.inicializar();
        this.eventos();
    }

    protected void inicializar() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.setResizable(false);
        this.buildPnlForm();
        this.getContentPane().add(pnlForm, BorderLayout.PAGE_START);
        this.buildPnlLista();
        this.getContentPane().add(pnlLista, BorderLayout.CENTER);
        this.getContentPane().add(getPnlRodape(), BorderLayout.PAGE_END);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(600, 400));
        this.pack();
        this.refreshTable();
    }

    public void buildPnlLista() {
        if (pnlLista == null) {
            pnlLista = new JPanel(new MigLayout());
            pnlLista.setBorder(BorderFactory.createTitledBorder("Listagem"));
            pnlLista.setBounds(5, 160 , 640, 320);

            dataTable = new JTable();
            scrollPane = new JScrollPane(dataTable);

            pnlLista.add(scrollPane, "span, grow, push");
        }
    }

    public JPanel getPnlRodape() {
        if (pnlRodape == null) {
            pnlRodape = new JPanel(new MigLayout());
            pnlRodape.setBorder(BorderFactory.createEtchedBorder());
            pnlRodape.setBounds(5, 0, 400, 40);

            btnSalvar = new JButton("Salvar");
            btnAtualizar = new JButton("Autalizar");
            btnLimpar = new JButton("Limpar");
            btnFechar = new JButton("Cancelar");

            pnlRodape.add(btnSalvar, "gapleft 150");
            pnlRodape.add(btnAtualizar, "gap unrelated");
            pnlRodape.add(btnLimpar, "gap unrelated");
            pnlRodape.add(btnFechar, "gap unrelated");
        }
        return pnlRodape;
    }

    protected void btnFecharClick(ActionEvent ev) {
        this.setVisible(false);
        this.dispose();
    }

    private void eventos() {
        btnSalvar.addActionListener(this::btnSalvarClick);
        btnLimpar.addActionListener(this::btnLimparClick);
        btnFechar.addActionListener(this::btnFecharClick);
        btnAtualizar.addActionListener(this::btnAtualizarClick);
    }

    public void buildPnlForm() {
        if (pnlForm == null) {
            pnlForm = new JPanel(new MigLayout());
            pnlForm.setBorder(BorderFactory.createTitledBorder("Formulário"));
            pnlForm.setBounds(5, 0 , 640, 400);

            lblId = new JLabel("ID");
            txtId  = new JTextField(TAMANHO_TXT);
            txtId.setEditable(false);

            pnlForm.add(lblId);
            pnlForm.add(txtId, "span, growx, wrap");

            buildForm();
        }
    }

    protected abstract void btnSalvarClick(ActionEvent ev);
    protected abstract void btnAtualizarClick(ActionEvent ev);
    protected abstract void btnLimparClick(ActionEvent ev);
    protected abstract void buildForm();
    protected abstract void refreshTable();
}
