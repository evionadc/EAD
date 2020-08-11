package br.sp.msoares.testServices;

import static br.sp.msoares.builders.ClienteBuilder.umCliente;
import static br.sp.msoares.builders.ProdutosBuilder.umProduto;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.sp.msoares.entities.Clientes;
import br.sp.msoares.entities.Produtos;
import br.sp.msoares.entities.Vendas;
import br.sp.msoares.services.VendasServices;
import br.sp.msoares.utils.DataUtils;

public class vendasTest {

    @Test
    public void deveVenderumProdutocomSucesso() throws Exception {

        // cenário ou o DADO/Given
        List<Produtos> produtos = Arrays.asList(umProduto().build());
        Clientes cliente = umCliente().build();

        // ação ou QUANDO/When
        VendasServices services = new VendasServices();
        Vendas venda = services.realizarVenda(produtos, cliente);

        // expectativa/afirmação ou Entao/Then
        assertThat(venda.getValorVenda(), is(equalTo(2500.00)));
        boolean ismesmadata = DataUtils.isMesmaData(venda.getDatalimitedevolucao(),
                DataUtils.obterDataComDiferencaDias(3));
        assertTrue(ismesmadata);
    }

    @Test
    public void naoDeveVenderumProdutoSemEstoque(){

        //cenário ou o DADO/Given
        List<Produtos> produto = Arrays.asList(umProduto().umProdutoSemEstoque().build());
        Clientes cliente = umCliente().build();

        //ação ou QUANDO/When
        try {
            VendasServices services = new VendasServices();
            Vendas venda = services.realizarVenda(produto, cliente);
            Assert.fail();

        //expectativa/afirmação ou Entao/Then
            
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Produto sem Estoque");
        }
    }
    
}
