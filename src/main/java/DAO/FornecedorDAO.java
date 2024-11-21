package DAO;

import Classes.Fornecedor;

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
}
