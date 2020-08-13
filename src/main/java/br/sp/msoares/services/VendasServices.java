package br.sp.msoares.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.sp.msoares.dao.locacaoDAO;
import br.sp.msoares.entities.Clientes;
import br.sp.msoares.entities.Produtos;
import br.sp.msoares.entities.Vendas;
import br.sp.msoares.exceptions.produtoSemEstoqueException;
import br.sp.msoares.exceptions.vendasServicesException;
import br.sp.msoares.utils.DataUtils;

public class VendasServices{

    private locacaoDAO dao;
    private spcService spc;
    public Vendas realizarVenda(List<Produtos> produtos, Clientes cliente) throws Exception {
                
        if(cliente == null){

            throw new vendasServicesException("Cliente indefinido");
        }
                
        if(produtos == null){
            throw new vendasServicesException("Produto indefinido");
        }

        boolean negativado;

        try {
            negativado = spc.consultarnegativacao(cliente);
        } catch (Exception e) {
            throw new Exception("ERRO NO SPC!!!");
        }

        if(negativado){
            throw new vendasServicesException("Cliente negativado!");
        }

        for (Produtos produto : produtos) {
            if(produto.getEstoque() == 0){
                throw new produtoSemEstoqueException();
            }
        }


        Vendas venda = new Vendas();        

        venda.setProduto(produtos);
        venda.setCliente(cliente);
        venda.setDataVenda(obterData());
        Date datadevolucao = obterData();
        datadevolucao = DataUtils.adicionarDias(datadevolucao, 3);
        if(DataUtils.verificarDiaSemana(datadevolucao, Calendar.SUNDAY)){
            datadevolucao = DataUtils.adicionarDias(datadevolucao, 1);
        }
        venda.setDatalimitedevolucao(datadevolucao);

        venda.setValorVenda(calcularValor(produtos));


        
        dao.salvar();

        return venda;

    }

    public Date obterData(){
        return new Date();
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