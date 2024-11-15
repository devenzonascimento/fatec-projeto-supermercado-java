package DAO;

import Classes.Fornecedor;

import java.sql.*;

public class FornecedorDAO {
    private ConexaoMysql conexaoMySql = new ConexaoMysql();

    public FornecedorDAO() {
    }

    public int inserir(Fornecedor fornecedor) {
        Connection conn = conexaoMySql.conectar();

        PreparedStatement stmtFornecedor = null;

        try {
            String sqlFornecedor = "INSERT INTO fornecedor(nome, cnpj, telefone, email, endereco) VALUES(?,?,?,?,?)";
            stmtFornecedor = conn.prepareStatement(sqlFornecedor);
            stmtFornecedor.setString(1, fornecedor.getNome());
            stmtFornecedor.setString(2, fornecedor.getCnpj());
            stmtFornecedor.setString(3, fornecedor.getTelefone());
            stmtFornecedor.setString(4, fornecedor.getEmail());
            stmtFornecedor.setString(5, fornecedor.getEndereco());

            return stmtFornecedor.executeUpdate();

        } catch (SQLException err) {
            System.err.println(err.getMessage());
            return 0;
        } finally {
            conexaoMySql.descontecar();
        }
    }
}
