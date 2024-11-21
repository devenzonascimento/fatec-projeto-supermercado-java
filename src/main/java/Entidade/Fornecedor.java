package Entidade;

public class Fornecedor {
    private long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private String endereco;

    public Fornecedor() {}

    public Fornecedor(String nome, String cnpj, String telefone, String email, String endereco) {
        setNome(nome);
        setCnpj(cnpj);
        setTelefone(telefone);
        setEmail(email);
        setEndereco(endereco);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
