package DAO;

import Entidade.Fornecedor;
import Entidade.Produto;

import java.sql.*;
import java.util.ArrayList;

public class FornecedorDAO {
    private ConexaoMysql conexaoMySql;

    public FornecedorDAO() {
        conexaoMySql = new ConexaoMysql();
    }

    public int inserir(Fornecedor fornecedor) {
        Connection conn = conexaoMySql.conectar();

        try {
            String sql = "INSERT INTO fornecedor(nome, cnpj, telefone, email, endereco) VALUES(?,?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEmail());
            stmt.setString(5, fornecedor.getEndereco());

            return stmt.executeUpdate();

        } catch (SQLException err) {
            System.err.println("Erro ao cadastrar fornecedor: " + err.getMessage());
            return 0;
        } finally {
            conexaoMySql.desconectar();
        }
    }

    public ArrayList<Fornecedor> listar() {
        Connection conn = conexaoMySql.conectar();

        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try {
            String sql = "SELECT * FROM fornecedor";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setId(resultado.getLong("id"));
                fornecedor.setNome(resultado.getString("nome"));
                fornecedor.setCnpj(resultado.getString("cnpj"));
                fornecedor.setTelefone(resultado.getString("telefone"));
                fornecedor.setEmail(resultado.getString("email"));
                fornecedor.setEndereco(resultado.getString("endereco"));

                fornecedores.add(fornecedor);
            }

        } catch (SQLException err) {
            System.err.println("Erro ao listar fornecedores: " + err.getMessage());
        } finally {
            conexaoMySql.desconectar();
        }

        return fornecedores;
    }

    public ArrayList<Fornecedor> pesquisarPorNome(String nome) {
        Connection conn = conexaoMySql.conectar();

        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try {
            String sql = "SELECT * FROM fornecedor WHERE nome LIKE ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, "%" + nome + "%");

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setId(resultado.getLong("id"));
                fornecedor.setNome(resultado.getString("nome"));
                fornecedor.setCnpj(resultado.getString("cnpj"));
                fornecedor.setTelefone(resultado.getString("telefone"));
                fornecedor.setEmail(resultado.getString("email"));
                fornecedor.setEndereco(resultado.getString("endereco"));

                fornecedores.add(fornecedor);
            }

        } catch (SQLException err) {
            System.err.println("Erro ao buscar fornecedores com o nome " + nome + ": " + err.getMessage());
        } finally {
            conexaoMySql.desconectar();
        }

        return fornecedores;
    }

    public ArrayList<Produto> listarProdutosFornecidos(long fornecedorId) {
        Connection conn = conexaoMySql.conectar();

        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            String sql = new StringBuilder()
                    .append("SELECT p.id, p.nome, p.codigo_de_barras, p.preco_venda, p.quantidade_minima, p.quantidade_atual, p.categoria FROM fornecedor f ")
                    .append("INNER JOIN produtofornecido pf ON pf.fornecedor_id = f.id ")
                    .append("INNER JOIN produto p ON pf.produto_id = p.id ")
                    .append("WHERE f.id = ?")
                    .toString();

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
            System.err.println("Erro ao listar produtos fornecidos: " + err.getMessage());
        } finally {
            conexaoMySql.desconectar();
        }

        return produtos;
    }
}
