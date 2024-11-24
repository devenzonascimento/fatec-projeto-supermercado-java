package Entidade;

public class ItemProduto {

    private long id;
    private int quantidade;
    private double precoUnitario;
    private Produto produto;

    public ItemProduto() {}

    public ItemProduto(long id, int quantidade, double precoUnitario, Produto produto) {
        setId(id);
        setQuantidade(quantidade);
        setPrecoUnitario(precoUnitario);
        setProduto(produto);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getSubTotal() {
        return quantidade * precoUnitario;
    }
}
