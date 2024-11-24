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
        secoes.add("Historico de pedido", historicoDePedidos);

        frame.add(secoes);

        frame.setVisible(true);
    }
}
// TODO: Implementar melhorias de codigo

/*
Cliente

    Atributos:
    id (long)
    nome (String)
    cpf (String)
    email (String)
    telefone (String)
    endereco (Endereco)

    Métodos:
    toString(): Exibir as informações do cliente.
    isValidCpf(): Validar o CPF do cliente.


Contato

    Atributos:
    id (long)
    telefone (String)
    email (String)
    endereco (Endereco)


Fornecedor

    Atributos:
    id (long)
    nome (String)
    cnpj (String)
    telefone (String)
    email (String)
    endereco (Endereco)
    produtos (List<Produto>)

    Métodos:
    getProdutosFornecidos(): Retornar a lista de produtos fornecidos.
    toString(): Exibir as informações básicas do fornecedor.


ItemProduto

    Atributos:
    id (long)
    quantidade (int)
    preco (double)
    produto (Produto)

    Métodos:
    getSubtotal(): Retornar o subtotal do item (quantidade × preço).


Pagamento

    Atributos:
    id (long)
    metodo (MetodoPagamento - Enum)
    valor (double)
    dataPagamento (Date)

    Métodos:
    toString(): Exibir os detalhes do pagamento.


Pedido

    Atributos:
    id (long)
    dataPedido (Date)
    dataEntrega (Date)
    status (StatusPedido - Enum)
    fornecedor (Fornecedor)
    itens (List<ItemProduto>)
    pagamento (Pagamento)

    Métodos:
    getValorTotal(): Calcular o valor total do pedido.
    getStatus(): Retornar o status atual do pedido.


Produto

    Atributos:
    id (long)
    nome (String)
    codigoDeBarras (String)
    precoVenda (double)
    quantidadeAtual (int)
    quantidadeMinima (int)
    categoria (String)

    Métodos:
    isEstoqueBaixo(): Verificar se o estoque está abaixo da quantidade mínima.
    toString(): Exibir detalhes do produto.


Venda

    Atributos:
    id (long)
    dataVenda (Date)
    cliente (Cliente)
    itens (List<ItemProduto>)
    pagamento (Pagamento)

    Métodos:
    getValorTotal(): Calcular o valor total da venda.
    toString()
*/