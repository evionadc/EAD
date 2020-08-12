package br.sp.msoares.testServices;

import static br.sp.msoares.builders.ClienteBuilder.umCliente;
import static br.sp.msoares.builders.ProdutosBuilder.umProduto;
import static br.sp.msoares.matchers.MatchersProprios.caiEm;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import br.sp.msoares.entities.Clientes;
import br.sp.msoares.entities.Produtos;
import br.sp.msoares.entities.Vendas;
import br.sp.msoares.exceptions.produtoSemEstoqueException;
import br.sp.msoares.exceptions.vendasServicesException;
import br.sp.msoares.services.VendasServices;
import br.sp.msoares.utils.DataUtils;

public class vendasTest {

    
    private VendasServices services;

    @Before
    public void setup() {
        services = new VendasServices();
    }

    @Test
    public void deveVenderumProdutocomSucesso() throws Exception {

        // cenário ou o DADO/Given
        List<Produtos> produtos = Arrays.asList(umProduto().build());
        Clientes cliente = umCliente().build();

        // ação ou QUANDO/When

        Vendas venda = services.realizarVenda(produtos, cliente);

        // expectativa/afirmação ou Entao/Then
        assertThat(venda.getValorVenda(), is(equalTo(2500.00)));
        boolean ismesmadata = DataUtils.isMesmaData(venda.getDatalimitedevolucao(),
                DataUtils.obterDataComDiferencaDias(3));
        assertTrue(ismesmadata);
    }

    @Test(expected = produtoSemEstoqueException.class)
    public void naoDeveVenderumProdutoSemEstoque() throws produtoSemEstoqueException, vendasServicesException {

        // cenário ou o DADO/Given
        List<Produtos> produto = Arrays.asList(umProduto().umProdutoSemEstoque().build());
        Clientes cliente = umCliente().build();

        // ação ou QUANDO/When
        services.realizarVenda(produto, cliente);

    }

    @Test
    public void naoDeveVenderSemProduto() throws vendasServicesException, produtoSemEstoqueException {

        Clientes cliente = umCliente().build();
        try {

            services.realizarVenda(null, cliente);
            Assert.fail();
        } catch (vendasServicesException e) {
            Assert.assertEquals(e.getMessage(), "Produto indefinido");
        }

    }

    @Test
    public void naoDeveVenderSemcliente() throws produtoSemEstoqueException {
        List<Produtos> produtos = Arrays.asList(umProduto().build());
        try {

            services.realizarVenda(produtos, null);
            Assert.fail();
        } catch (vendasServicesException e) {
            Assert.assertEquals(e.getMessage(), "Cliente indefinido");
        }
    }
/*
    @Test
    public void naoPodeDevolvernumDomingo() throws produtoSemEstoqueException, vendasServicesException {
        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.THURSDAY));

        List<Produtos> produtos = Arrays.asList(umProduto().build());
        Clientes cliente = umCliente().build();

        Vendas venda = services.realizarVenda(produtos, cliente);

        assertThat(venda.getDatalimitedevolucao(), caiEm(Calendar.MONDAY));

     }*/
  
    
}
