package Tela;

import DAO.FornecedorDAO;
import DAO.PedidoDAO;
import Entidade.*;
import Enum.MetodoPagamento;
import Enum.TipoPagamento;
import Tela.Utilitarios.MostrarPopUp;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolicitacaoDePedido {

    private JComboBox<Fornecedor> fornecedorComboBox;
    private JLabel valorTotalLbl;
    private JComboBox<MetodoPagamento> metodoPagamentoComboBox;
    private JTextField dataEntregaTxt;

    private JComboBox<Produto> produtoComboBox;
    private JTextField quantidadeTxt;
    private JTextField precoUnitarioTxt;

    private JButton adicionarItemBtn;
    private JButton salvarPedidoBtn;

    private DefaultTableModel itensTableModel;
    private JTable itensTable;

    private MostrarPopUp popUp;

    public SolicitacaoDePedido() {
        fornecedorComboBox = new JComboBox<Fornecedor>();
        valorTotalLbl = new JLabel("R$ 0,00");
        metodoPagamentoComboBox = new JComboBox<MetodoPagamento>();
        metodoPagamentoComboBox.addItem(MetodoPagamento.DINHEIRO);
        metodoPagamentoComboBox.addItem(MetodoPagamento.CREDITO);
        metodoPagamentoComboBox.addItem(MetodoPagamento.DEBITO);
        metodoPagamentoComboBox.addItem(MetodoPagamento.PIX);
        dataEntregaTxt = new JTextField(10);

        produtoComboBox = new JComboBox<Produto>();
        quantidadeTxt = new JTextField(10);
        precoUnitarioTxt = new JTextField(10);
        adicionarItemBtn = new JButton("Adicionar Item");

        salvarPedidoBtn = new JButton("Salvar Pedido");

        popUp = new MostrarPopUp();

        itensTableModel = new DefaultTableModel(new String[]{"Produto", "Quantidade", "Preço Unitário", "Subtotal"}, 0);
        itensTable = new JTable(itensTableModel);

        itensTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        itensTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        itensTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        itensTable.getColumnModel().getColumn(3).setPreferredWidth(60);

        carregarFornecedores();
        carregarProdutos();
        configurarEventos();
    }

    public JPanel criar() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel painelItensPedido = new JPanel(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        layout.gridx = 0;
        layout.gridwidth = 2;
        layout.weightx = 1.0;
        layout.fill = GridBagConstraints.BOTH;
        painelItensPedido.add(criarPainelTabela(), layout);

        layout.gridx = 2;
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.anchor = GridBagConstraints.NORTH;
        painelItensPedido.add(criarPainelAdicionarItemPedido(), layout);

        panel.add(criarPainelCadastroPedido(), BorderLayout.NORTH);
        panel.add(painelItensPedido, BorderLayout.CENTER);
        panel.add(criarPainelBotoes(), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel criarPainelCadastroPedido() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dados do pedido", TitledBorder.LEFT, TitledBorder.TOP));
        GridBagConstraints layout = new GridBagConstraints();

        layout.insets = new Insets(2, 5, 2, 5);
        layout.fill = GridBagConstraints.BOTH;
        layout.gridx = 0;
        layout.weightx = 1.0;
        layout.anchor = GridBagConstraints.EAST;

        // Campos de formulário
        layout.gridy = 0;
        panel.add(new JLabel("Fornecedor:"), layout);
        layout.gridy = 1;
        panel.add(new JLabel("Valor total:"), layout);
        layout.gridy = 2;
        panel.add(new JLabel("Metodo de pagamento:"), layout);
        layout.gridy = 3;
        panel.add(new JLabel("Data de entrega (DD-MM-YYYY):"), layout);

        layout.gridx = 1;
        layout.gridy = 0;
        panel.add(fornecedorComboBox, layout);
        layout.gridy = 1;
        valorTotalLbl.setBorder(BorderFactory.createEtchedBorder());
        panel.add(valorTotalLbl, layout);
        layout.gridy = 2;
        panel.add(metodoPagamentoComboBox, layout);
        layout.gridy = 3;
        panel.add(dataEntregaTxt, layout);

        return panel;
    }

    private JPanel criarPainelAdicionarItemPedido() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Adicionar Itens ao Pedido", TitledBorder.LEFT, TitledBorder.TOP));
        GridBagConstraints layout = new GridBagConstraints();

        layout.insets = new Insets(5, 5, 5, 5);
        layout.fill = GridBagConstraints.BOTH;
        layout.gridx = 0;
        layout.weightx = 1.0;
        layout.anchor = GridBagConstraints.EAST;

        // Campos de formulário
        layout.gridy = 0;
        panel.add(new JLabel("Produto:"), layout);
        layout.gridy = 1;
        panel.add(new JLabel("Quantidade:"), layout);
        layout.gridy = 2;
        panel.add(new JLabel("Preço Unitário:"), layout);

        layout.gridx = 1;
        layout.gridy = 0;
        panel.add(produtoComboBox, layout);
        layout.gridy = 1;
        panel.add(quantidadeTxt, layout);
        layout.gridy = 2;
        panel.add(precoUnitarioTxt, layout);

        // Botão adicionar item
        layout.gridx = 0;
        layout.gridy = 4;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.CENTER;
        panel.add(adicionarItemBtn, layout);

        return panel;
    }

    private JPanel criarPainelTabela() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Itens do Pedido", TitledBorder.LEFT, TitledBorder.TOP));

        JScrollPane scrollPane = new JScrollPane(itensTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        salvarPedidoBtn.setText("Salvar Pedido");
        panel.add(salvarPedidoBtn);

        return panel;
    }

    private void configurarEventos() {
        adicionarItemBtn.addActionListener(this::adicionarItem);
        salvarPedidoBtn.addActionListener(this::salvarPedido);
        fornecedorComboBox.addActionListener(this::atualizarProdutosFornecidos);
    }

    private void carregarFornecedores() {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        try {
            List<Fornecedor> fornecedores = fornecedorDAO.listar();
            for (Fornecedor fornecedor : fornecedores) {
                fornecedorComboBox.addItem(fornecedor);
            }
        } catch (Exception e) {
            popUp.mensagemDeErro("Erro ao carregar fornecedores: " + e.getMessage());
        }
    }

    private void carregarProdutos() {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();

        Fornecedor fornecedorSelecionado = (Fornecedor) fornecedorComboBox.getSelectedItem();

        produtoComboBox.removeAllItems();

        try {
            assert fornecedorSelecionado != null;
            List<Produto> produtos = fornecedorDAO.listarProdutosFornecidos(fornecedorSelecionado.getId());
            for (Produto produto : produtos) {
                produtoComboBox.addItem(produto);
            }
        } catch (Exception e) {
            popUp.mensagemDeErro("Erro ao carregar produtos: " + e.getMessage());
        }
    }

    private void atualizarProdutosFornecidos(ActionEvent e) {
        carregarProdutos();
    };

    private void adicionarItem(ActionEvent e) {
        Produto produto = (Produto) produtoComboBox.getSelectedItem();
        String quantidadeStr = quantidadeTxt.getText();
        String precoUnitarioStr = precoUnitarioTxt.getText();

        try {
            int quantidade = Integer.parseInt(quantidadeStr);
            double precoUnitario = Double.parseDouble(precoUnitarioStr);
            double subtotal = quantidade * precoUnitario;

            itensTableModel.addRow(new Object[]{produto, quantidade, precoUnitario, subtotal});

            // Limpa campos
            quantidadeTxt.setText("");
            precoUnitarioTxt.setText("");

            atualizarValorTotal();
        } catch (NumberFormatException ex) {
            popUp.mensagemDeErro("Quantidade e Preço devem ser números válidos.");
        }
    }

    private ArrayList<ItemProduto> obterItensDePedido() {
        ArrayList<ItemProduto> itens = new ArrayList<>();

        // Itera por todas as linhas da tabela
        for (int i = 0; i < itensTableModel.getRowCount(); i++) {
            ItemProduto item = new ItemProduto();

            Produto produto = (Produto) itensTableModel.getValueAt(i, 0);
            int quantidade = (int) itensTableModel.getValueAt(i, 1);
            double precoUnitario = (double) itensTableModel.getValueAt(i, 2);
            double subTotal = (double) itensTableModel.getValueAt(i, 3);

            item.setProduto(produto);
            item.setQuantidade(quantidade);
            item.setPrecoUnitario(precoUnitario);
            item.setSubTotal(subTotal);

            itens.add(item);
        }

        return itens;
    }

    private double obterValorTotal() {
        double valorTotal = 0.0;

        ArrayList<ItemProduto> itens = obterItensDePedido();

        for (ItemProduto item : itens) {
            valorTotal += item.getSubTotal();
        }

        return valorTotal;
    }

    private void atualizarValorTotal() {
        valorTotalLbl.setText("R$ " + String.format("%.2f", obterValorTotal()));
    }

    private void salvarPedido(ActionEvent e) {
        if (itensTableModel.getRowCount() == 0) {
            popUp.mensagemDeErro("Adicione ao menos um item ao pedido.");
            return;
        }

        String dataEntregaString = dataEntregaTxt.getText();

        if (dataEntregaString.isEmpty()) {
            popUp.mensagemDeErro("Preencha a data de entrega.");
            return;
        }

        try {
            Fornecedor fornecedor = (Fornecedor) fornecedorComboBox.getSelectedItem();
            ArrayList<ItemProduto> itensDoPedido = obterItensDePedido();
            Date dataEntrega = new SimpleDateFormat("dd-MM-yyyy").parse(dataEntregaString);

            Pagamento pagamento = new Pagamento();
            pagamento.setValor(obterValorTotal());
            pagamento.setMetodo((MetodoPagamento) metodoPagamentoComboBox.getSelectedItem());
            pagamento.setTipo(TipoPagamento.ENTRADA);

            Pedido pedido = new Pedido();
            pedido.setDataEntrega(dataEntrega);
            pedido.setFornecedor(fornecedor);
            pedido.setPagamento(pagamento);
            pedido.setItens(itensDoPedido);

            PedidoDAO pedidoDAO = new PedidoDAO();
            int pedidoId = pedidoDAO.criar(pedido);

            if (pedidoId == 0) {
                popUp.mensagemDeErro("Erro ao salvar o pedido.");
                return;
            }

            popUp.mensagemDeSucesso("Pedido salvo com sucesso!");

        } catch (Exception ex) {
            popUp.mensagemDeErro("Erro ao salvar pedido: " + ex.getMessage());
        }
    }
}
