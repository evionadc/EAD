package br.sp.msoares.entities;

public class Produtos{

    private String nomedoProduto;
    private Integer estoque;
    private Double valor;

    public Produtos(){}

    public Produtos(String nomeProduto, Integer estoque, Double valor){
        this.nomedoProduto = nomeProduto;
        this.estoque = estoque;
        this.valor = valor;
    }
    

    /**
     * @return String return the nomedoProduto
     */
    public String getNomedoProduto() {
        return nomedoProduto;
    }

    /**
     * @param nomedoProduto the nomedoProduto to set
     */
    public void setNomedoProduto(String nomedoProduto) {
        this.nomedoProduto = nomedoProduto;
    }

    /**
     * @return Integer return the estoque
     */
    public Integer getEstoque() {
        return estoque;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    /**
     * @return Double return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

}