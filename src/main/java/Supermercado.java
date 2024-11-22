import Tela.*;

import javax.swing.*;

public class Supermercado {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Gerenciamento de Supermercado");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        JTabbedPane secoes = new JTabbedPane();

        CadastroDeFornecedor telaCadastroDeFornecedor = new CadastroDeFornecedor();
        ListagemDeFornecedores telaListagemDeFornecedores = new ListagemDeFornecedores();
        SolicitacaoDePedido telaSolicitacaoDePedido = new SolicitacaoDePedido();
        HistoricoDePedidos telaHistoricoDePedidos = new HistoricoDePedidos();

        JPanel cadastroDeFornecedor = telaCadastroDeFornecedor.criar();
        JPanel listagemDeFornecedores = telaListagemDeFornecedores.criar();
        JPanel solicitacaoDePedido = telaSolicitacaoDePedido.criar();
        JPanel historicoDePedidos = telaHistoricoDePedidos.criar();

        secoes.add("Cadastrar Fornecedor", cadastroDeFornecedor);
        secoes.add("Lista de Fornecedores", listagemDeFornecedores);
        secoes.add("Solicitar Pedido", solicitacaoDePedido);
        secoes.add("Historico de pedido", historicoDePedidos);

        frame.add(secoes);

        frame.setVisible(true);
    }
}