package DAO;

import Entidade.Produto;
import Interface.IProdutoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO implements IProdutoDAO {

    private ConexaoMysql conexaoMySql;

    public ProdutoDAO() {
        conexaoMySql = new ConexaoMysql();
    }

    @Override
    public ArrayList<Produto> listar() {
        Connection conn = conexaoMySql.conectar();

        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produto";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto();

                produto.setId(resultado.getLong("id"));
                produto.setNome(resultado.getString("nome"));
                produto.setCodigoDeBarras(resultado.getString("codigo_de_barras"));
                produto.setPreco(resultado.getDouble("preco_venda"));
                produto.setQuantidadeAtual(resultado.getInt("quantidade_atual"));
                produto.setQuantidadeMinima(resultado.getInt("quantidade_minima"));
                produto.setCategoria(resultado.getString("categoria"));

                produtos.add(produto);
            }

        } catch (SQLException err) {
            System.err.println("Erro ao listar produtos: " + err.getMessage());
        } finally {
            conexaoMySql.desconectar();
        }

        return produtos;
    }

    @Override
    public Produto buscarPorId(long id) {
        Connection conn = conexaoMySql.conectar();

        Produto produto = new Produto();

        try {
            String sql = "SELECT * FROM produto WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                produto.setId(resultado.getLong("id"));
                produto.setNome(resultado.getString("nome"));
                produto.setCodigoDeBarras(resultado.getString("codigo_de_barras"));
                produto.setPreco(resultado.getDouble("preco_venda"));
                produto.setQuantidadeAtual(resultado.getInt("quantidade_atual"));
                produto.setQuantidadeMinima(resultado.getInt("quantidade_minima"));
                produto.setCategoria(resultado.getString("categoria"));
            }

        } catch (SQLException err) {
            System.err.println("Erro ao buscar produto com o id (" + id + "): " + err.getMessage());
        } finally {
            conexaoMySql.desconectar();
        }

        return produto;
    }

    @Override
    public ArrayList<Produto> listarPorFornecedor(long fornecedorId) {
        Connection conn = conexaoMySql.conectar();

        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            String sql = "SELECT p.* FROM produto p " +
                    "INNER JOIN produtoFornecido pf ON p.id = pf.produto_id " +
                    "WHERE pf.fornecedor_id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, fornecedorId);

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto();

                produto.setId(resultado.getLong("id"));
                produto.setNome(resultado.getString("nome"));
                produto.setCodigoDeBarras(resultado.getString("codigo_de_barras"));
                produto.setPreco(resultado.getDouble("preco_venda"));
                produto.setQuantidadeAtual(resultado.getInt("quantidade_atual"));
                produto.setQuantidadeMinima(resultado.getInt("quantidade_minima"));
                produto.setCategoria(resultado.getString("categoria"));

                produtos.add(produto);
            }

        } catch (SQLException err) {
            System.err.println("Erro ao listar produtos por fornecedor (" + fornecedorId + "): " + err.getMessage());
        } finally {
            conexaoMySql.desconectar();
        }

        return produtos;
    }

    @Override
    public int inserir(Produto produto) {
        Connection conn = conexaoMySql.conectar();

        try {
            String sql = "INSERT INTO produto(nome, codigo_de_barras, preco_venda, quantidade_atual, quantidade_minima, categoria) VALUES(?,?,?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCodigoDeBarras());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidadeAtual());
            stmt.setInt(5, produto.getQuantidadeMinima());
            stmt.setString(6, produto.getCategoria());

            return stmt.executeUpdate();

        } catch (SQLException err) {
            System.err.println("Erro ao cadastrar produto: " + err.getMessage());
            return 0;
        } finally {
            conexaoMySql.desconectar();
        }
    }

    @Override
    public int atualizar(Produto produto) {
        Connection conn = conexaoMySql.conectar();

        try {
            String sql = "UPDATE produto SET nome = ?, codigo_de_barras = ?, preco_venda = ?, quantidade_atual = ?, quantidade_minima = ?, categoria = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCodigoDeBarras());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidadeAtual());
            stmt.setInt(5, produto.getQuantidadeMinima());
            stmt.setString(6, produto.getCategoria());
            stmt.setLong(7, produto.getId());

            return stmt.executeUpdate();

        } catch (SQLException err) {
            System.err.println("Erro ao atualizar produto: " + err.getMessage());
            return 0;
        } finally {
            conexaoMySql.desconectar();
        }
    }

    @Override
    public int remover(long id) {
        Connection conn = conexaoMySql.conectar();

        try {
            String sql = "DELETE FROM produto WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            return stmt.executeUpdate();

        } catch (SQLException err) {
            System.err.println("Erro ao remover produto com o id (" + id + "): " + err.getMessage());
            return 0;
        } finally {
            conexaoMySql.desconectar();
        }
    }
}
