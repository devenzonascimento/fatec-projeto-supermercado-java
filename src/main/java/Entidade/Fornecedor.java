package Entidade;

public class Fornecedor extends Contato {

    private String cnpj;

    public Fornecedor() {}

    public Fornecedor(String nome, String cnpj, String telefone, String email, String endereco) {
        super();
        setNome(nome);
        setCnpj(cnpj);
        setTelefone(telefone);
        setEmail(email);
        setEndereco(endereco);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
