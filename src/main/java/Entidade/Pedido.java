package Entidade;

import Enum.StatusPedido;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {

    private long id;
    private Date dataPedido;
    private Date dataEntrega;
    private Enum<StatusPedido> status;
    private Fornecedor fornecedor;
    private Pagamento pagamento;
    private ArrayList<ItemProduto> itens;

    public Pedido() {}

    public Pedido(long id, Date dataPedido, Date dataEntrega, Enum<StatusPedido> status, Fornecedor fornecedor, Pagamento pagamento, ArrayList<ItemProduto> itens) {
        setId(id);
        setDataPedido(dataPedido);
        setDataEntrega(dataEntrega);
        setStatus(status);
        setFornecedor(fornecedor);
        setPagamento(pagamento);
        setItens(itens);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Enum<StatusPedido> getStatus() {
        return status;
    }

    public void setStatus(Enum<StatusPedido> status) {
        this.status = status;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
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
            valorTotal =+ item.calcularSubTotal();
        }

        return valorTotal;
    }
}
