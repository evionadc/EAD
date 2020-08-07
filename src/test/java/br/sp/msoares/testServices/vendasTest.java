package br.sp.msoares.testServices;

import org.junit.Assert;
import org.junit.Test;

import br.sp.msoares.entities.Clientes;
import br.sp.msoares.entities.Produtos;
import br.sp.msoares.entities.Vendas;
import br.sp.msoares.services.VendasServices;

import br.sp.msoares.utils.DataUtils;

/**
 * Unit test for simple App.
 */
public class vendasTest 
{
 
    @Test
    public void deveVenderumProdutocomSucesso (){

        //cenário ou o DADO/Given
        Produtos produto = new Produtos("Notebook", 3,2500.00);
        Clientes cliente = new Clientes("Cliente 01");

        //ação ou QUANDO/When
        VendasServices services = new VendasServices();
        Vendas venda = services.realizarVenda(produto, cliente);

        //expectativa/afirmação ou Entao/Then
        Assert.assertEquals(venda.getProduto().getNomedoProduto(), "Notebook");
        boolean ismesmadata = DataUtils.isMesmaData(venda.getDatalimitedevolucao(),DataUtils.obterDataComDiferencaDias(3));
        Assert.assertTrue(ismesmadata);
    }

    @Test
    public void naoDeveVenderumProdutoSemEstoque(){

        //cenário ou o DADO/Given
        Produtos produto = new Produtos("Notebook", 0,2500.00);
        Clientes cliente = new Clientes("Cliente 01");

        //ação ou QUANDO/When
        VendasServices services = new VendasServices();
        Vendas venda = services.realizarVenda(produto, cliente);

        //expectativa/afirmação ou Entao/Then
        Assert.assertEquals(venda.getProduto().getNomedoProduto(), "Notebook");
        boolean ismesmadata = DataUtils.isMesmaData(venda.getDatalimitedevolucao(),DataUtils.obterDataComDiferencaDias(3));
        Assert.assertTrue(ismesmadata);
    }
    
}
