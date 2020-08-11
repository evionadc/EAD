package br.sp.msoares.builders;

import br.sp.msoares.entities.Clientes;

public class ClienteBuilder {

    private Clientes cliente;

    private ClienteBuilder(){}

    public static ClienteBuilder umCliente(){
        ClienteBuilder builder = new ClienteBuilder();
        builder.cliente = new Clientes();
        builder.cliente.setNomeCliente("Cliente 01");
        return builder;
    }

    public Clientes build(){
        return cliente;
    }
    
}