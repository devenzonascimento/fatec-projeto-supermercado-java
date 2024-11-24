package Interface;

import Entidade.Fornecedor;

import java.util.ArrayList;

public interface IFornecedorDAO {

    ArrayList<Fornecedor> listar();

    Fornecedor buscarPorId(long id);

    ArrayList<Fornecedor> pesquisarPorNome(String nome);

    int inserir(Fornecedor fornecedor);

    int atualizar(Fornecedor fornecedor);

    int remover(long id);
}
