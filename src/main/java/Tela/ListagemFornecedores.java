package Tela;

import Entidade.Fornecedor;
import DAO.FornecedorDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ListagemFornecedores {

    private ArrayList<Fornecedor> fornecedores;
    private FornecedorDAO fornecedorDAO;

    private JTextField barraPesquisaTxt;
    private JButton pesquisarBtn;
    private JButton atualizarBtn;

    private DefaultTableModel tableModel;
    private JTable table;

    public ListagemFornecedores() {
        fornecedorDAO = new FornecedorDAO();
        fornecedores = fornecedorDAO.listar();

        barraPesquisaTxt = new JTextField(50);
        atualizarBtn = new JButton();
        pesquisarBtn = new JButton();
    }

    public JPanel criarListagem() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        JPanel cabecalhoTabela = criarCabecalhoTabela();

        layout.gridy = 0;
        layout.gridx = 0;
        layout.weightx = 1.0;
        layout.fill = GridBagConstraints.BOTH;
        panel.add(cabecalhoTabela, layout);

        layout.gridy = 1;
        layout.weighty = 1.0;
        layout.insets = new Insets(20, 0, 0, 0);
        JScrollPane tabela = criarTabela();

        panel.add(tabela, layout);

        return panel;
    }

    private JPanel criarCabecalhoTabela() {
        atualizarBtn.addActionListener(this::aoAtualizarListagem);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        layout.gridx = 0;
        panel.add(new JLabel("Pesquisar fornecedor pelo nome: "), layout);

        pesquisarBtn.setText("Pesquisar");

        layout.gridx = 2;
        panel.add(pesquisarBtn, layout);

        atualizarBtn.setText("Atualizar");

        layout.gridx = 3;
        layout.insets = new Insets(0, 20, 0, 0);
        panel.add(atualizarBtn, layout);

        layout.gridx = 1;
        layout.weightx = 1.0;
        layout.fill = GridBagConstraints.BOTH;
        panel.add(barraPesquisaTxt, layout);

        return panel;
    }

    private JScrollPane criarTabela() {
        String[] columnNames = {"ID", "Nome", "CNPJ", "Telefone", "E-Mail", "Endereco"};

        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        atualizarTabela();

        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(90);
        table.getColumnModel().getColumn(3).setPreferredWidth(70);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(250);

        return new JScrollPane(table);
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);

        fornecedores.forEach(fornecedor -> {
            tableModel.addRow(new Object[]{
                    fornecedor.getId(),
                    fornecedor.getNome(),
                    fornecedor.getCnpj(),
                    fornecedor.getTelefone(),
                    fornecedor.getEmail(),
                    fornecedor.getEndereco()
            });
        });
    }

    private void aoAtualizarListagem(ActionEvent event) {
        fornecedores = fornecedorDAO.listar();
        atualizarTabela();
    }
}
