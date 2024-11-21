package DAO;

import Classes.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {
    private ConexaoMysql conexaoMySql;

    public ProdutoDAO() {
        conexaoMySql = new ConexaoMysql();
    }

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
}
