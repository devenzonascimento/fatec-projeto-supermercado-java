package Interface;

import Entidade.Produto;

import java.util.ArrayList;

public interface IProdutoDAO {

    ArrayList<Produto> listar();

    Produto buscarPorId(long id);

    ArrayList<Produto> listarPorFornecedor(long fornecedorId);

    int inserir(Produto produto);

    int atualizar(Produto produto);

    int remover(long id);
}
