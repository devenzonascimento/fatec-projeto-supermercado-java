package Entidade;

import java.util.ArrayList;

public class Fornecedor extends Contato {

    private String cnpj;
    private ArrayList<Produto> produtosFornecidos;

    public Fornecedor() {}

    public Fornecedor(String nome, String cnpj, String telefone, String email, String endereco, ArrayList<Produto> produtosFornecidos) {
        super();
        setNome(nome);
        setCnpj(cnpj);
        setTelefone(telefone);
        setEmail(email);
        setEndereco(endereco);
        setProdutosFornecidos(produtosFornecidos);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public ArrayList<Produto> getProdutosFornecidos() {
        return produtosFornecidos;
    }

    public void setProdutosFornecidos(ArrayList<Produto> produtosFornecidos) {
        this.produtosFornecidos = produtosFornecidos;
    }
}
