package Interface;

import Entidade.Pedido;

import java.util.ArrayList;

public interface IPedidoDAO {

    ArrayList<Pedido> listarTodos();

    Pedido buscarPorId(long id);

    int inserir(Pedido pedido);

    boolean atualizar(Pedido pedido);

    boolean remover(long id);
}
