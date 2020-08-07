package br.sp.msoares;

import br.sp.msoares.entities.Clientes;
import br.sp.msoares.entities.Produtos;
import br.sp.msoares.entities.Vendas;
import br.sp.msoares.services.VendasServices;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args )
    {
       Produtos produto = new Produtos("Notebook", 3, 5000.00 );
       Clientes cliente = new Clientes("Cliente 1");
       VendasServices service  = new VendasServices();

       Vendas venda = service.realizarVenda(produto, cliente);
    
       System.out.println(venda.getProduto().getValor());
       System.out.println(venda.getDataVenda());
       System.out.println(venda.getDatalimitedevolucao());
    }
}
