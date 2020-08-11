package br.sp.msoares.services;

import java.util.Date;
import java.util.List;

import br.sp.msoares.entities.Clientes;
import br.sp.msoares.entities.Produtos;
import br.sp.msoares.entities.Vendas;
import br.sp.msoares.utils.DataUtils;

public class VendasServices{

    public Vendas realizarVenda(List<Produtos> produtos, Clientes cliente) throws Exception {

        for (Produtos produto : produtos) {
            if(produto.getEstoque() == 0){
                throw new Exception("Produto sem Estoque");
            }
        }

        Vendas venda = new Vendas();        

        venda.setProduto(produtos);
        venda.setCliente(cliente);
        venda.setDataVenda(new Date());
        Date datadevolucao = new Date();
        datadevolucao = DataUtils.adicionarDias(datadevolucao, 3);
        venda.setDatalimitedevolucao(datadevolucao);

        /*for (Produtos produto : produtos) {
            venda.setValorVenda(venda.getValorVenda() + produto.getValor());
        }*/

        venda.setValorVenda(calcularValor(produtos));

        //TODO Salvar a venda

        return venda;

    }

    private double calcularValor(List<Produtos> produtos){
        double valorvenda = 0.0;
        if(produtos.size() >2){
            int produtomin = 0;
            double minValue = produtos.get(0).getValor();
            for(int i = 0;i< produtos.size(); i++){
                if(produtos.get(i).getValor()< minValue){
                    minValue = produtos.get(i).getValor();
                    produtomin = i;
                }
            }

            produtos.get(produtomin).setValor(0.0);

            for (Produtos produto : produtos) {
                valorvenda += produto.getValor();
            }

            return valorvenda;

        }
        else{
            for (Produtos produto : produtos) {
                valorvenda += produto.getValor();
            }

            return valorvenda;
        }


    }
}