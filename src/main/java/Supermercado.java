import Tela.*;

import javax.swing.*;
import java.awt.*;

public class Supermercado {

    private static final int LARGURA_DA_TELA = 1000;
    private static final int ALTURA_DA_TELA = 700;

    public static void main(String[] args) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamanhoDaJanela = kit.getScreenSize();

        Point localizacaoCentralizada = new Point((tamanhoDaJanela.width / 2) - LARGURA_DA_TELA / 2, (tamanhoDaJanela.height / 2) - ALTURA_DA_TELA / 2);

        JFrame frame = new JFrame("Sistema de Gerenciamento de Supermercado");
        frame.setSize(LARGURA_DA_TELA, ALTURA_DA_TELA);
        frame.setLocation(localizacaoCentralizada);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane secoes = new JTabbedPane();

        CadastroDeFornecedor telaCadastroDeFornecedor = new CadastroDeFornecedor();
        ListagemDeFornecedores telaListagemDeFornecedores = new ListagemDeFornecedores();
        SolicitacaoDePedido telaSolicitacaoDePedido = new SolicitacaoDePedido();
        HistoricoDeCompras telaHistoricoDeCompras = new HistoricoDeCompras();

        JPanel cadastroDeFornecedor = telaCadastroDeFornecedor.criar();
        JPanel listagemDeFornecedores = telaListagemDeFornecedores.criar();
        JPanel solicitacaoDePedido = telaSolicitacaoDePedido.criar();
        JPanel historicoDePedidos = telaHistoricoDeCompras.criar();

        secoes.add("Cadastrar Fornecedor", cadastroDeFornecedor);
        secoes.add("Lista de Fornecedores", listagemDeFornecedores);
        secoes.add("Solicitar Pedido", solicitacaoDePedido);
        secoes.add("Historico de Compras", historicoDePedidos);

        frame.add(secoes);

        frame.setVisible(true);
    }
}