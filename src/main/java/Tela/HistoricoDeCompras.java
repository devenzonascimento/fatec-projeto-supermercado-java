package Tela;

import DAO.HistoricoDeComprasDAO;
import Interface.IHistoricoDeCompras;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class HistoricoDeCompras {
    private ArrayList<Entidade.HistoricoDeCompras> historicoDeCompras;
    private IHistoricoDeCompras historicoDeComprasDAO;

    private JTextField barraPesquisaTxt;
    private JButton pesquisarBtn;
    private JButton atualizarBtn;

    private DefaultTableModel tableModel;
    private JTable table;

    public HistoricoDeCompras() {
        barraPesquisaTxt = new JTextField(50);
        atualizarBtn = new JButton();
        pesquisarBtn = new JButton();

        tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Quantidade de Compras", "Valor Total Gasto", "Data da Ultima Compra", "Frequencia de Compra em dias"}, 0);
        table = new JTable(tableModel);

        historicoDeComprasDAO = new HistoricoDeComprasDAO();
        historicoDeCompras = historicoDeComprasDAO.gerar();
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

        for (Entidade.HistoricoDeCompras registroDoHistorico : historicoDeCompras) {
            tableModel.addRow(new Object[]{
                    registroDoHistorico.getFornecedorId(),
                    registroDoHistorico.getFornecedorNome(),
                    registroDoHistorico.getQuantidadeDeCompras(),
                    ("R$ " + String.format("%.2f", registroDoHistorico.getValorTotalGasto())),
                    registroDoHistorico.getDataUltimaCompra(),
                    registroDoHistorico.getFrequenciaDeCompraEmDias()
            });
        }
    }

    private void configurarEventos() {
        atualizarBtn.addActionListener(this::atualizarListagem);
        pesquisarBtn.addActionListener(this::pesquisarFornecedor);
    }

    private void atualizarListagem(ActionEvent event) {
        historicoDeCompras = historicoDeComprasDAO.gerar();

        barraPesquisaTxt.setText("");

        recarregarListagem();
    }

    private void pesquisarFornecedor(ActionEvent event) {
        historicoDeCompras = historicoDeComprasDAO.pesquisarPorNome(barraPesquisaTxt.getText());

        recarregarListagem();
    }
}
