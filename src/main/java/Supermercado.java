import DAO.FornecedorDAO;
import Classes.Endereco;
import Classes.Fornecedor;

public class Supermercado {
    public static void main(String[] args) {
        Endereco endereco = new Endereco("Rua dos coquinhos", "157", "", "Vila dos Mato", "Cidade do Interior", "SP", "09777-070");

        Fornecedor fornecedor = new Fornecedor("Coca", "55.646.632/0001-19", "(11) 96333-3738", "email@example.com", endereco.converter());

        FornecedorDAO fornecedorDAO = new FornecedorDAO();

        fornecedorDAO.inserir(fornecedor);
    }
}