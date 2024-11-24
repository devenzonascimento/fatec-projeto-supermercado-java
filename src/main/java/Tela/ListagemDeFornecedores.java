package Tela;

import Entidade.Fornecedor;
import DAO.FornecedorDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ListagemDeFornecedores {
    private ArrayList<Fornecedor> fornecedores;
    private FornecedorDAO fornecedorDAO;

    private JTextField barraPesquisaTxt;
    private JButton pesquisarBtn;
    private JButton atualizarBtn;

    private DefaultTableModel tableModel;
    private JTable table;

    private Consumer<Fornecedor> aoEditarFornecedor;

    public ListagemDeFornecedores(Consumer<Fornecedor> aoEditarFornecedor) {
        this.aoEditarFornecedor = aoEditarFornecedor;

        barraPesquisaTxt = new JTextField(50);
        atualizarBtn = new JButton();
        pesquisarBtn = new JButton();

        tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "CNPJ", "Telefone", "E-Mail", "Endereco"}, 0);
        table = new JTable(tableModel);

        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(90);
        table.getColumnModel().getColumn(3).setPreferredWidth(70);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(250);

        fornecedorDAO = new FornecedorDAO();
        fornecedores = fornecedorDAO.listar();
        recarregarListagem();

        configurarEventos();
    }

    public JPanel criar() {
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
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        layout.gridx = 0;
        panel.add(new JLabel("Pesquisar fornecedor pelo nome: "), layout);

        pesquisarBtn.setText("Pesquisar");
        atualizarBtn.setText("Atualizar");

        layout.gridx = 2;
        panel.add(pesquisarBtn, layout);

        layout.gridx = 3;
        layout.insets = new Insets(0, 20, 0, 0);
        panel.add(atualizarBtn, layout);

        layout.gridx = 1;
        layout.weightx = 1.0;
        layout.fill = GridBagConstraints.BOTH;
        layout.insets = new Insets(0, 10, 0, 10);
        panel.add(barraPesquisaTxt, layout);

        return panel;
    }

    private JScrollPane criarTabela() {
        return new JScrollPane(table);
    }

    private void recarregarListagem() {
        tableModel.setRowCount(0);

        for (Fornecedor fornecedor : fornecedores) {
            tableModel.addRow(new Object[]{
                    fornecedor.getId(),
                    fornecedor.getNome(),
                    fornecedor.getCnpj(),
                    fornecedor.getTelefone(),
                    fornecedor.getEmail(),
                    fornecedor.getEndereco()
            });
        }
    }

    private void configurarEventos() {
        atualizarBtn.addActionListener(this::atualizarListagem);
        pesquisarBtn.addActionListener(this::pesquisarFornecedor);
        table.addMouseListener(selecionarFornecedor());
    }

    private void editarFornecedor(long id) {
        int linha = table.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(id);
        fornecedor.setNome((String) tableModel.getValueAt(linha, 1));
        fornecedor.setCnpj((String) tableModel.getValueAt(linha, 2));
        fornecedor.setTelefone((String) tableModel.getValueAt(linha, 3));
        fornecedor.setEmail((String) tableModel.getValueAt(linha, 4));
        fornecedor.setEndereco((String) tableModel.getValueAt(linha, 5));

        aoEditarFornecedor.accept(fornecedor);
    }

    private void excluirFornecedor(long id) {
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza de que deseja excluir o fornecedor com ID: " + id + "?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            fornecedorDAO.remover(id);
            atualizarListagem(null);
            JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso!");
        }
    }

    private void atualizarListagem(ActionEvent event) {
        fornecedores = fornecedorDAO.listar();

        barraPesquisaTxt.setText("");

        recarregarListagem();
    }

    private void pesquisarFornecedor(ActionEvent event) {
        fornecedores = fornecedorDAO.pesquisarPorNome(barraPesquisaTxt.getText());

        recarregarListagem();
    }

    private MouseAdapter selecionarFornecedor() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int linha = table.getSelectedRow();

                if (linha != -1) {
                    long id = (long) table.getValueAt(linha, 0);
                    String nome = (String) table.getValueAt(linha, 1);

                    Object[] opcoes = {"Editar", "Excluir", "Cancelar"};
                    int escolha = JOptionPane.showOptionDialog(
                            null,
                            "Você deseja editar ou excluir o fornecedor?\n\nNome: " + nome + "\nID: " + id,
                            "Opções",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opcoes,
                            opcoes[2] // Botão padrão (Cancelar)
                    );

                    // Trata as escolhas
                    switch (escolha) {
                        case JOptionPane.YES_OPTION:
                            editarFornecedor(id);
                            break;
                        case JOptionPane.NO_OPTION:
                            excluirFornecedor(id);
                            break;
                        default:
                            break;
                    }
                }
            }
        };
    }
}
