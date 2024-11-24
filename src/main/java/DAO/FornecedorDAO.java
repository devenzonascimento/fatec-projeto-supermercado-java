package DAO;

import Entidade.Fornecedor;
import Interface.IFornecedorDAO;

import java.sql.*;
import java.util.ArrayList;

public class FornecedorDAO implements IFornecedorDAO {

    private ConexaoMysql conexaoMySql;

    public FornecedorDAO() {
        conexaoMySql = new ConexaoMysql();
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

    public Fornecedor buscarPorId(long id) {
        Connection conn = conexaoMySql.conectar();

        Fornecedor fornecedor = new Fornecedor();

        try {
            String sql = "SELECT * FROM fornecedor WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                fornecedor.setId(resultado.getLong("id"));
                fornecedor.setNome(resultado.getString("nome"));
                fornecedor.setCnpj(resultado.getString("cnpj"));
                fornecedor.setTelefone(resultado.getString("telefone"));
                fornecedor.setEmail(resultado.getString("email"));
                fornecedor.setEndereco(resultado.getString("endereco"));
            }

        } catch (SQLException err) {
            System.err.println("Erro ao buscar fornecedor com o id (" + id + "): " + err.getMessage());
        } finally {
            conexaoMySql.desconectar();
        }

        return fornecedor;
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
            System.err.println("Erro ao pesquisar fornecedores com o nome (" + nome + "): " + err.getMessage());
        } finally {
            conexaoMySql.desconectar();
        }

        return fornecedores;
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

    public int atualizar(Fornecedor fornecedor) {
        Connection conn = conexaoMySql.conectar();

        try {
            String sql = "UPDATE fornecedor SET nome = ?, cnpj = ?, telefone = ?, email = ?, endereco = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEmail());
            stmt.setString(5, fornecedor.getEndereco());
            stmt.setLong(6, fornecedor.getId());

            return stmt.executeUpdate();

        } catch (SQLException err) {
            System.err.println("Erro ao atualizar fornecedor: " + err.getMessage());
            return 0;
        } finally {
            conexaoMySql.desconectar();
        }
    }

    public int remover(long id) {
        Connection conn = conexaoMySql.conectar();

        try {
            String sql = "DELETE FROM fornecedor WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            return stmt.executeUpdate();

        } catch (SQLException err) {
            System.err.println("Erro ao remover fornecedor com o id (" + id + "): " + err.getMessage());
            return 0;
        } finally {
            conexaoMySql.desconectar();
        }
    }
}
