import Telas.Pedidos.TelaPedidos;
import Telas.TelaFornecedor;

public class Supermercado {
    public static void main(String[] args) {
        TelaFornecedor telaFornecedor = new TelaFornecedor();

        telaFornecedor.mostrar();

        TelaPedidos telaPedidos = new TelaPedidos();

        telaPedidos.mostrar();
    }
}