package Classes;

public class ItemPedido {
    private long id;
    private int quantidade;
    private double precoUnitario;
    private double subTotal;
    private Produto produto;

    public ItemPedido() {}

    public ItemPedido(long id, int quantidade, double precoUnitario, double subTotal, Produto produto) {
        setId(id);
        setQuantidade(quantidade);
        setPrecoUnitario(precoUnitario);
        setSubTotal(subTotal);
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

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
