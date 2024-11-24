package Entidade;

import Enum.MetodoPagamento;
import Enum.TipoPagamento;

import java.util.Date;

public class Pagamento {

    private long id;
    private double valor;
    private Date data;
    private Enum<MetodoPagamento> metodo;
    private Enum<TipoPagamento> tipo;

    public Pagamento() {
    }

    public Pagamento(long id, double valor, Date data, Enum<MetodoPagamento> metodo, Enum<TipoPagamento> tipo) {
        setId(id);
        setValor(valor);
        setData(data);
        setMetodo(metodo);
        setTipo(tipo);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Enum<MetodoPagamento> getMetodo() {
        return metodo;
    }

    public void setMetodo(Enum<MetodoPagamento> metodo) {
        this.metodo = metodo;
    }

    public Enum<TipoPagamento> getTipo() {
        return tipo;
    }

    public void setTipo(Enum<TipoPagamento> tipo) {
        this.tipo = tipo;
    }
}
