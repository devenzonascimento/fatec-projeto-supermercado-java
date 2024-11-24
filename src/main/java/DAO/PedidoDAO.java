package DAO;

import Entidade.*;
import Interface.IPedidoDAO;
import Enum.*;

import java.sql.*;
import java.util.ArrayList;

public class PedidoDAO implements IPedidoDAO {
    private ConexaoMysql conexaoMySql;

    public PedidoDAO() {
        conexaoMySql = new ConexaoMysql();
    }

    @Override
    public ArrayList<Pedido> listarTodos() {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        Connection conn = conexaoMySql.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT p.id, p.data_pedido, p.data_entrega, p.status, p.fornecedor_id, p.pagamento_id, " + "f.nome AS fornecedor_nome, f.cnpj, f.telefone, f.email, f.endereco, " + "pay.valor AS pagamento_valor, pay.metodo AS pagamento_metodo, pay.tipo AS pagamento_tipo " + "FROM pedido p " + "JOIN fornecedor f ON p.fornecedor_id = f.id " + "JOIN pagamento pay ON p.pagamento_id = pay.id";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                Date dataPedido = rs.getDate("data_pedido");
                Date dataEntrega = rs.getDate("data_entrega");
                StatusPedido status = StatusPedido.valueOf(rs.getString("status"));

                // Fornecedor
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getLong("fornecedor_id"));
                fornecedor.setNome(rs.getString("fornecedor_nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setEndereco(rs.getString("endereco"));

                // Pagamento
                Pagamento pagamento = new Pagamento();
                pagamento.setId(rs.getLong("pagamento_id"));
                pagamento.setValor(rs.getDouble("pagamento_valor"));
                pagamento.setMetodo(MetodoPagamento.valueOf(rs.getString("pagamento_metodo")));
                pagamento.setTipo(TipoPagamento.valueOf(rs.getString("pagamento_tipo")));

                // Criando o pedido
                Pedido pedido = new Pedido(id, dataPedido, dataEntrega, status, fornecedor, pagamento, new ArrayList<>());

                // Agora, buscar os itens do pedido
                String sqlItens = "SELECT ip.id, ip.quantidade, ip.preco_unitario, ip.pedido_id, ip.produto_id, " + "prod.nome AS produto_nome, prod.codigo_de_barras, prod.preco_venda " + "FROM item_pedido ip " + "JOIN produto prod ON ip.produto_id = prod.id " + "WHERE ip.pedido_id = ?";
                PreparedStatement stmtItens = conn.prepareStatement(sqlItens);
                stmtItens.setLong(1, id);
                ResultSet rsItens = stmtItens.executeQuery();

                while (rsItens.next()) {
                    // Criando os itens do pedido
                    ItemProduto item = new ItemProduto();
                    item.setId(rsItens.getLong("id"));
                    item.setQuantidade(rsItens.getInt("quantidade"));
                    item.setPrecoUnitario(rsItens.getDouble("preco_unitario"));

                    Produto produto = new Produto();

                    rsItens.getLong("produto_id");
                    rsItens.getString("produto_nome");
                    rsItens.getString("codigo_de_barras");
                    rsItens.getDouble("preco_venda");

                    item.setProduto(new Produto());

                    pedido.getItens().add(item);
                }

                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                conexaoMySql.desconectar();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pedidos;
    }

    @Override
    public Pedido buscarPorId(long id) {
        Connection conn = conexaoMySql.conectar();
        Pedido pedido = null;

        try {
            String sql = "SELECT * FROM pedido WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                pedido = new Pedido();
                pedido.setId(resultado.getLong("id"));
                pedido.setDataEntrega(resultado.getDate("data_entrega"));
                // Atribuir outros campos conforme necessário
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar pedido por ID: " + e.getMessage());
        } finally {
            conexaoMySql.desconectar();
        }

        return pedido;
    }

    @Override
    public int inserir(Pedido pedido) {
        Connection conn = conexaoMySql.conectar();
        PreparedStatement stmtPagamento = null;
        PreparedStatement stmtPedido = null;
        PreparedStatement stmtItemPedido = null;

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

            for (ItemProduto itemProduto : pedido.getItens()) {
                stmtItemPedido.setInt(1, itemProduto.getQuantidade());
                stmtItemPedido.setDouble(2, itemProduto.getPrecoUnitario());
                stmtItemPedido.setLong(3, pedidoId);
                stmtItemPedido.setLong(4, itemProduto.getProduto().getId());

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

    @Override
    public boolean atualizar(Pedido pedido) {
        Connection conn = conexaoMySql.conectar();

        try {
            String sql = "UPDATE pedido SET data_entrega = ?, status = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new Date(pedido.getDataEntrega().getTime()));
            stmt.setString(2, pedido.getStatus().name());
            stmt.setLong(3, pedido.getId());

            int linhasAtualizadas = stmt.executeUpdate();

            return linhasAtualizadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pedido: " + e.getMessage());
            return false;
        } finally {
            conexaoMySql.desconectar();
        }
    }

    @Override
    public boolean remover(long id) {
        Connection conn = conexaoMySql.conectar();

        try {
            String sql = "DELETE FROM pedido WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover pedido: " + e.getMessage());
            return false;
        } finally {
            conexaoMySql.desconectar();
        }
    }
}
