package br.sp.msoares.builders;

import br.sp.msoares.entities.Produtos;

public class ProdutosBuilder {
    
    private Produtos produto;
    private ProdutosBuilder(){}

    public static ProdutosBuilder umProduto(){
        ProdutosBuilder builder = new ProdutosBuilder();
        builder.produto = new Produtos();
        builder.produto.setEstoque(3);
        builder.produto.setNomedoProduto("Notebook");
        builder.produto.setValor(2500.00);
        return builder;


    }

    public  ProdutosBuilder umProdutoSemEstoque(){
        produto.setEstoque(0);
        return this;
    }
    
    public ProdutosBuilder umProdutocomValor(Double valor){
        produto.setValor(valor);
        return this;
    }
    public Produtos build(){

        return produto;
    };
}