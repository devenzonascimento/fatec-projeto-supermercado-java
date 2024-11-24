package Entidade;

import java.util.Date;

public class HistoricoDeCompras {

    private long fornecedorId;
    private String fornecedorNome;
    private int quantidadeDeCompras;
    private double valorTotalGasto;
    private Date dataUltimaCompra;
    private int frequenciaDeCompraEmDias;

    public HistoricoDeCompras() {
    }

    public HistoricoDeCompras(long fornecedorId, String fornecedorNome, int quantidadeDeCompras, double valorTotalGasto, Date dataUltimaCompra, int frequenciaDeCompraEmDias) {
        this.fornecedorId = fornecedorId;
        this.fornecedorNome = fornecedorNome;
        this.quantidadeDeCompras = quantidadeDeCompras;
        this.valorTotalGasto = valorTotalGasto;
        this.dataUltimaCompra = dataUltimaCompra;
        this.frequenciaDeCompraEmDias = frequenciaDeCompraEmDias;
    }

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
}
