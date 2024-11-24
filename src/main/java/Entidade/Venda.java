package Entidade;

import java.util.ArrayList;
import java.util.Date;

public class Venda {

    private long id;
    private Date data;
    private Cliente cliente;
    private Pagamento pagamento;
    private ArrayList<ItemProduto> itens;

    public Venda() {
    }

    public Venda(long id, Date data, Cliente cliente, Pagamento pagamento, ArrayList<ItemProduto> itens) {
        setId(id);
        setData(data);
        setCliente(cliente);
        setPagamento(pagamento);
        setItens(itens);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public ArrayList<ItemProduto> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemProduto> itens) {
        this.itens = itens;
    }

    public double calcularValorTotal() {
        double valorTotal = 0.0;

        for (ItemProduto item : itens) {
            valorTotal = +item.getSubTotal();
        }

        return valorTotal;
    }
}
