package br.sp.msoares.testServices;

import static br.sp.msoares.builders.ClienteBuilder.umCliente;
import static br.sp.msoares.builders.ProdutosBuilder.umProduto;
import static br.sp.msoares.matchers.MatchersProprios.caiEm;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.sp.msoares.dao.locacaoDAO;
import br.sp.msoares.entities.Clientes;
import br.sp.msoares.entities.Produtos;
import br.sp.msoares.entities.Vendas;
import br.sp.msoares.exceptions.produtoSemEstoqueException;
import br.sp.msoares.exceptions.vendasServicesException;
import br.sp.msoares.services.VendasServices;
import br.sp.msoares.services.spcService;
import br.sp.msoares.utils.DataUtils;

public class vendasTest {

    @Mock
    locacaoDAO dao;

    @Mock
    spcService spc;

    @InjectMocks @Spy
    VendasServices services;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveVenderumProdutocomSucesso() throws Exception {

        // cenário ou o DADO/Given
        List<Produtos> produtos = Arrays.asList(umProduto().build());
        Clientes cliente = umCliente().build();

        Mockito.doReturn(DataUtils.obterData(12, 8,2020)).when(services).obterData();

        // ação ou QUANDO/When

        Vendas venda = services.realizarVenda(produtos, cliente);

        // expectativa/afirmação ou Entao/Then
        assertThat(venda.getValorVenda(), is(equalTo(2500.00)));
        boolean ismesmadata = DataUtils.isMesmaData(venda.getDatalimitedevolucao(),
              DataUtils.obterData(15,8,2020));
        assertTrue(ismesmadata);

        Mockito.verify(services, times(2)).obterData();
    }

    @Test(expected = produtoSemEstoqueException.class)
    public void naoDeveVenderumProdutoSemEstoque() throws Exception {

        // cenário ou o DADO/Given
        List<Produtos> produto = Arrays.asList(umProduto().umProdutoSemEstoque().build());
        Clientes cliente = umCliente().build();

        // ação ou QUANDO/When
        services.realizarVenda(produto, cliente);

    }

    @Test
    public void naoDeveVenderSemProduto() throws Exception {

        Clientes cliente = umCliente().build();
        try {

            services.realizarVenda(null, cliente);
            Assert.fail();
        } catch (vendasServicesException e) {
            Assert.assertEquals(e.getMessage(), "Produto indefinido");
        }

    }

    @Test
    public void naoDeveVenderSemcliente() throws Exception {
        List<Produtos> produtos = Arrays.asList(umProduto().build());
        try {

            services.realizarVenda(produtos, null);
            Assert.fail();
        } catch (vendasServicesException e) {
            Assert.assertEquals(e.getMessage(), "Cliente indefinido");
        }
    }
    @Test
    public void naoPodeDevolvernumDomingo() throws Exception {
        
      
        List<Produtos> produtos = Arrays.asList(umProduto().build());
        Clientes cliente = umCliente().build();
        
        Mockito.doReturn(DataUtils.obterData(06, 8,2020)).when(services).obterData();

        Vendas venda = services.realizarVenda(produtos, cliente);

        assertThat(venda.getDatalimitedevolucao(), caiEm(Calendar.MONDAY));

     }

     @Test
     public void naoDeveVenderparaUsuarionegativadoNoSPC() throws Exception {

        List<Produtos> produtos = Arrays.asList(umProduto().build());
        Clientes cliente = umCliente().build();

        Mockito.when(spc.consultarnegativacao(Mockito.any(Clientes.class))).thenReturn(true);

        try{
            services.realizarVenda(produtos, cliente);
            Assert.fail();

        }catch(Exception e){
            Assert.assertEquals(e.getMessage(), "Cliente negativado!");

        }
        Mockito.verify(spc).consultarnegativacao(cliente);
        


     }

     @Test
     public void deveTratarErronoSPC() throws Exception {
        List<Produtos> produtos = Arrays.asList(umProduto().build());
        Clientes cliente = umCliente().build();

        Mockito.when(spc.consultarnegativacao(Mockito.any(Clientes.class))).thenThrow(new Exception("ERRO NO SPC!!!"));

        try{
            services.realizarVenda(produtos, cliente);
            Assert.fail();

        }catch(Exception e){
            Assert.assertEquals(e.getMessage(), "ERRO NO SPC!!!");

        }
        Mockito.verify(spc).consultarnegativacao(cliente);

     }
  
    
}
