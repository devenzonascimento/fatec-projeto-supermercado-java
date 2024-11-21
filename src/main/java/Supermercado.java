import Tela.HistoricoPedidos.TelaHistoricoPedidos;
import Tela.Pedidos.TelaPedidos;
import Tela.TelaFornecedor;

public class Supermercado {
    public static void main(String[] args) {
        TelaFornecedor telaFornecedor = new TelaFornecedor();

        telaFornecedor.mostrar();

        TelaPedidos telaPedidos = new TelaPedidos();

        telaPedidos.mostrar();

        TelaHistoricoPedidos telaHistoricoPedidos = new TelaHistoricoPedidos();

        telaHistoricoPedidos.mostrar();
    }
}