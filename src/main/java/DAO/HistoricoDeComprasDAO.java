package DAO;

import Entidade.HistoricoDeCompras;
import Interface.IHistoricoDeCompras;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoricoDeComprasDAO implements IHistoricoDeCompras {
    private ConexaoMysql conexaoMySql;

    public HistoricoDeComprasDAO() {
        conexaoMySql = new ConexaoMysql();
    }

    @Override
    public ArrayList<HistoricoDeCompras> gerar() {
        Connection conn = conexaoMySql.conectar();
        ArrayList<HistoricoDeCompras> historico = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Historico_Compras";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                HistoricoDeCompras registroDoHistorico = new HistoricoDeCompras();

                registroDoHistorico.setFornecedorId(resultado.getLong("fornecedor_id"));
                registroDoHistorico.setFornecedorNome(resultado.getString("fornecedor_nome"));
                registroDoHistorico.setQuantidadeDeCompras(resultado.getInt("quantidade_de_compras"));
                registroDoHistorico.setValorTotalGasto(resultado.getDouble("valor_total_gasto"));
                registroDoHistorico.setDataUltimaCompra(resultado.getDate("data_ultima_compra"));
                registroDoHistorico.setFrequenciaDeCompraEmDias(resultado.getInt("frequencia_pedidos_dias"));

                historico.add(registroDoHistorico);
            }

        } catch (SQLException err) {
            System.err.println("Erro ao listar produtos: " + err.getMessage());
        } finally {
            conexaoMySql.desconectar();
        }

        return historico;
    }
}
