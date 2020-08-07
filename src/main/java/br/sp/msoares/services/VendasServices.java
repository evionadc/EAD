package br.sp.msoares.services;

import java.util.Date;

import br.sp.msoares.entities.Clientes;
import br.sp.msoares.entities.Produtos;
import br.sp.msoares.entities.Vendas;
import br.sp.msoares.utils.DataUtils;

public class VendasServices{

    public Vendas realizarVenda(Produtos produto, Clientes cliente){

        Vendas venda = new Vendas();

        venda.setProduto(produto);
        venda.setCliente(cliente);
        venda.setDataVenda(new Date());
        Date datadevolucao = new Date();
        datadevolucao = DataUtils.adicionarDias(datadevolucao, 3);
        venda.setDatalimitedevolucao(datadevolucao);

        //TODO Salvar a venda

        return venda;

    }
}