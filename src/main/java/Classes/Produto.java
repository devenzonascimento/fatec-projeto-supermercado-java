package Classes;

public class Produto {
    private long id;
    private String nome;
    private String codigoDeBarras;
    private double preco;
    private int quantidadeAtual;
    private int quantidadeMinima;
    private String categoria;

    public Produto() {}

    public Produto(long id, String nome, String codigoDeBarras, double preco, int quantidadeAtual, int quantidadeMinima, String categoria) {
        setId(id);
        setNome(nome);
        setCodigoDeBarras(codigoDeBarras);
        setPreco(preco);
        setQuantidadeAtual(quantidadeAtual);
        setQuantidadeMinima(quantidadeMinima);
        setCategoria(categoria);
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

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return this.nome;
    }
}
