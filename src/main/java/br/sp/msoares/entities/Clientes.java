package br.sp.msoares.entities;

public class Clientes{

    private String nomeCliente;

    public Clientes(){}
    public Clientes(String nomeCliente){
        this.nomeCliente =  nomeCliente;
    }

    /**
     * @return String return the nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * @param nomeCliente the nomeCliente to set
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

}