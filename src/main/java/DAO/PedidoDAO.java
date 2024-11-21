package DAO;

import Entidade.ItemPedido;
import Entidade.Pedido;

import java.sql.*;

public class PedidoDAO {
    private ConexaoMysql conexaoMySql;

    public PedidoDAO() {
        conexaoMySql = new ConexaoMysql();
    }

    public int criar(Pedido pedido) {
        Connection conn = conexaoMySql.conectar();
        PreparedStatement stmtPagamento = null;
        PreparedStatement stmtPedido = null;
        PreparedStatement stmtItemPedido = null;

        System.out.println(pedido.getFornecedor());
        System.out.println(pedido.getDataEntrega());
        for (ItemPedido item : pedido.getItens()) {
            System.out.println(item.getProduto());
        }

        try {
            conn.setAutoCommit(false);

            // Inserção do pagamento
            String sqlPagamento = "INSERT INTO pagamento (valor, metodo, tipo) VALUES (?, ?, ?)";
            stmtPagamento = conn.prepareStatement(sqlPagamento, Statement.RETURN_GENERATED_KEYS);
            stmtPagamento.setDouble(1, pedido.getPagamento().getValor());
            stmtPagamento.setString(2, pedido.getPagamento().getMetodo().name());
            stmtPagamento.setString(3, pedido.getPagamento().getTipo().name());

            int linhasAtualizadasPagamento = stmtPagamento.executeUpdate();

            if (linhasAtualizadasPagamento == 0) {
                throw new SQLException("Falha ao inserir pagamento. Nenhuma linha foi afetada.");
            }

            ResultSet generatedKeysPagamento = stmtPagamento.getGeneratedKeys();
            long pagamentoId = 0;

            if (generatedKeysPagamento.next()) {
                pagamentoId = generatedKeysPagamento.getLong(1);
            } else {
                throw new SQLException("Falha ao inserir pagamento. ID não foi retornado.");
            }


            // Inserção do pedido
            String sqlPedido = "INSERT INTO pedido (data_entrega, fornecedor_id, pagamento_id) VALUES (?, ?, ?)";
            stmtPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);

            stmtPedido.setDate(1, new Date(pedido.getDataEntrega().getTime()));
            stmtPedido.setLong(2, pedido.getFornecedor().getId());
            stmtPedido.setLong(3, pagamentoId);

            int linhasAtualizadasPedido = stmtPedido.executeUpdate();

            if (linhasAtualizadasPedido == 0) {
                throw new SQLException("Falha ao inserir pedido. Nenhuma linha foi afetada.");
            }

            ResultSet generatedKeysPedido = stmtPedido.getGeneratedKeys();
            long pedidoId = 0;

            if (generatedKeysPedido.next()) {
                pedidoId = generatedKeysPedido.getLong(1);
            } else {
                throw new SQLException("Falha ao inserir pedido. ID não foi retornado.");
            }

            // Inserção dos itens de pedido
            String sqlItemPedido = "INSERT INTO item_pedido (quantidade, preco_unitario, pedido_id, produto_id) VALUES (?, ?, ?, ?)";
            stmtItemPedido = conn.prepareStatement(sqlItemPedido);

            for (ItemPedido itemPedido : pedido.getItens()) {
                stmtItemPedido.setInt(1, itemPedido.getQuantidade());
                stmtItemPedido.setDouble(2, itemPedido.getPrecoUnitario());
                stmtItemPedido.setLong(3, pedidoId);
                stmtItemPedido.setLong(4, itemPedido.getProduto().getId());

                stmtItemPedido.addBatch();
            }

            stmtItemPedido.executeBatch();

            conn.commit();
            return (int) pedidoId;

        } catch (SQLException err) {
            try {
                if (conn != null) {
                    conn.rollback(); // Reverte a transação em caso de erro
                }
            } catch (SQLException rollbackErr) {
                System.err.println("Erro ao reverter a transação: " + rollbackErr.getMessage());
            }

            System.err.println("Erro ao cadastrar pedido: " + err.getMessage());
            return 0;

        } finally {
            conexaoMySql.desconectar();
        }
    }

}

