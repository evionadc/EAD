package br.sp.msoares.entities;

import java.util.Date;

public class Vendas {

    private Produtos produto;
    private Clientes cliente;
    private Date dataVenda;
    private Date datalimitedevolucao;
    

    /**
     * @return Produtos return the produto
     */
    public Produtos getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    /**
     * @return Clientes return the cliente
     */
    public Clientes getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }


    /**
     * @return Date return the dataVenda
     */
    public Date getDataVenda() {
        return dataVenda;
    }

    /**
     * @param dataVenda the dataVenda to set
     */
    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }


    /**
     * @return Date return the datalimitedevolucao
     */
    public Date getDatalimitedevolucao() {
        return datalimitedevolucao;
    }

    /**
     * @param datalimitedevolucao the datalimitedevolucao to set
     */
    public void setDatalimitedevolucao(Date datalimitedevolucao) {
        this.datalimitedevolucao = datalimitedevolucao;
    }

}