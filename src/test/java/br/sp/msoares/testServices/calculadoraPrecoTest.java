package br.sp.msoares.testServices;

import static br.sp.msoares.builders.ClienteBuilder.umCliente;
import static br.sp.msoares.builders.ProdutosBuilder.umProduto;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.sp.msoares.dao.locacaoDAO;
import br.sp.msoares.entities.Clientes;
import br.sp.msoares.entities.Produtos;
import br.sp.msoares.entities.Vendas;
import br.sp.msoares.services.VendasServices;
import br.sp.msoares.services.spcService;
import br.sp.msoares.utils.DataUtils;

@RunWith(Parameterized.class)
public class calculadoraPrecoTest {

    @Mock
    locacaoDAO dao;

    @Mock
    spcService spc;

    @Spy @InjectMocks
    VendasServices service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Parameter
    public List<Produtos> produtos;

    @Parameter(value = 1)
    public Double valorvenda;

    @Parameter(value=2)
     public String cenario;

    private static Produtos produto1 = umProduto().build();
    private static Produtos produto2 = umProduto().umProdutocomValor(400.00).build();
    private static Produtos produto3 = umProduto().umProdutocomValor(200.00).build();
    private static Produtos produto4 = umProduto().umProdutocomValor(1500.00).build();
    private static Produtos produto5 = umProduto().umProdutocomValor(350.00).build();
    private static Produtos produto6 = umProduto().umProdutocomValor(800.00).build();
    private static Produtos produto7 = umProduto().umProdutocomValor(3000.00).build();

    @Parameters(name = "{2}")
    public static Collection<Object[]> getParametros() {
        return Arrays.asList(new Object[][] { { Arrays.asList(produto1, produto2), 2900.0, "Não deve ter desconto" },
                { Arrays.asList(produto1, produto2, produto3), 2900.0, "O valor do produto 3 deve ser zerado" },
                { Arrays.asList(produto1, produto2, produto4, produto6), 4800.0,
                        "O valor do produto 2 deve ser zerado" },
                { Arrays.asList(produto1, produto2, produto3, produto4, produto5, produto6, produto7), 8150.00,
                        "7 produtos com 200 de desconto do produto 3" } });
    }

    @Test
    public void deveCalcularoValordaVenda() throws Exception {
        // cenario
        Clientes cliente = umCliente().build();

        Mockito.doReturn(DataUtils.obterData(12, 8,2020)).when(service).obterData();

        // acao 
        Vendas venda = service.realizarVenda(produtos, cliente);

        // verificacao
        Assert.assertThat(venda.getValorVenda(), is(valorvenda));
	}
    
}