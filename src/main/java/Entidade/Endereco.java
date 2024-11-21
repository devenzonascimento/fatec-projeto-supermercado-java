package Entidade;

import java.util.Objects;

public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco() {
    }

    public Endereco(String logradouro, String numero, String complemento, String bairro, String cidade, String uf, String cep) {
        setLogradouro(logradouro);
        setNumero(numero);
        setComplemento(complemento);
        setBairro(bairro);
        setCidade(cidade);
        setUf(uf);
        setCep(cep);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String converter() throws Exception {
        validarEndereco();

        if (Objects.equals(complemento, "")) {
            return converterEnderecoSemComplemento();
        }

        return converterEnderecoCompleto();
    }

    private boolean validarEndereco() throws Exception {
        if (Objects.equals(logradouro, "")) {
            throw new Exception("Logradouro é obrigatório.");
        }

        if (Objects.equals(numero, "")) {
            throw new Exception("Numero é obrigatório.");
        }

        if (Objects.equals(bairro, "")) {
            throw new Exception("Bairro é obrigatório.");
        }

        if (Objects.equals(cidade, "")) {
            throw new Exception("Cidade é obrigatório.");
        }

        if (Objects.equals(uf, "")) {
            throw new Exception("UF é obrigatório.");
        }

        if (Objects.equals(cep, "")) {
            throw new Exception("CEP é obrigatório.");
        }

        return true;
    }

    private String converterEnderecoCompleto() {
        StringBuilder construtorDeEndereco = new StringBuilder();

        construtorDeEndereco.append(logradouro);
        construtorDeEndereco.append(", ");
        construtorDeEndereco.append(numero);
        construtorDeEndereco.append(", ");
        construtorDeEndereco.append(complemento);
        construtorDeEndereco.append(" - ");
        construtorDeEndereco.append(bairro);
        construtorDeEndereco.append(" - ");
        construtorDeEndereco.append(cidade);
        construtorDeEndereco.append(" - ");
        construtorDeEndereco.append(uf);
        construtorDeEndereco.append(" - ");
        construtorDeEndereco.append(cep);

        return construtorDeEndereco.toString();
    }

    private String converterEnderecoSemComplemento() {
        StringBuilder construtorDeEndereco = new StringBuilder();

        construtorDeEndereco.append(logradouro);
        construtorDeEndereco.append(", ");
        construtorDeEndereco.append(numero);
        construtorDeEndereco.append(" - ");
        construtorDeEndereco.append(bairro);
        construtorDeEndereco.append(" - ");
        construtorDeEndereco.append(cidade);
        construtorDeEndereco.append(" - ");
        construtorDeEndereco.append(uf);
        construtorDeEndereco.append(" - ");
        construtorDeEndereco.append(cep);

        return construtorDeEndereco.toString();
    }
}
