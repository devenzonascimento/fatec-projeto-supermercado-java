package Entidade;

public class Cliente extends Contato {
    
    private String cpf;
    public Cliente() {}

    public Cliente(String nome, String cpf, String telefone, String email, String endereco) {
        super();
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setEmail(email);
        setEndereco(endereco);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
