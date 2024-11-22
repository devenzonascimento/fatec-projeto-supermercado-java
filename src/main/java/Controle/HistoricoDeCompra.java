package Controle;

import java.util.Date;

public class HistoricoDeCompra {
    private long fornecedorId;
    private String fornecedorNome;
    private int quantidadeDeCompras;
    private double valorTotalGasto;
    private Date dataUltimaCompra;
    private int frequenciaDeCompraEmDias;

    public long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public String getFornecedorNome() {
        return fornecedorNome;
    }

    public void setFornecedorNome(String fornecedorNome) {
        this.fornecedorNome = fornecedorNome;
    }

    public int getQuantidadeDeCompras() {
        return quantidadeDeCompras;
    }

    public void setQuantidadeDeCompras(int quantidadeDeCompras) {
        this.quantidadeDeCompras = quantidadeDeCompras;
    }

    public double getValorTotalGasto() {
        return valorTotalGasto;
    }

    public void setValorTotalGasto(double valorTotalGasto) {
        this.valorTotalGasto = valorTotalGasto;
    }

    public Date getDataUltimaCompra() {
        return dataUltimaCompra;
    }

    public void setDataUltimaCompra(Date dataUltimaCompra) {
        this.dataUltimaCompra = dataUltimaCompra;
    }

    public int getFrequenciaDeCompraEmDias() {
        return frequenciaDeCompraEmDias;
    }

    public void setFrequenciaDeCompraEmDias(int frequenciaDeCompraEmDias) {
        this.frequenciaDeCompraEmDias = frequenciaDeCompraEmDias;
    }

    public void gerar() {

    }
}
