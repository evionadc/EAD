Funcionalidade: Vender Produtos​

Eu, enquanto vendendor, quero realizar uma venda, registrando o produto vendido e o cliente.​

Critérios de aceite​

//Feito 
Não pode ser vendido produtos sem estoque​
//Feito
Todos os campos são obrigatórios.​
//Feito
O cliente tem até 3 dias para devolver o produto.​

Se a compra for feita em um sexta, a o dia de devolução deve cair numa segunda.

//Feito
Um cliente pode levar mais de um produto.

Uma venda é a soma de todos os valores de todos os produtos

O cliente levar 3 produtos ou mais, o de menor valor sai de graça.



​

Cenario: Vender um produto​

Dado que eu venda o produto com "Notebook"​

E com o valor 2500.00 reais e com 3 em estoque​

E para o cliente "Cliente 01"​

Quando realizar a venda​

Entao a venda deve ser registrada com sucesso.



Cenario: Não deve vender produtos sem estoque

Dado que eu venda o produto com "Notebook"​

E com o valor 2500.00 reais e com 0 em estoque​

E para o cliente "Cliente 01"​

Quando realizar a venda​

Entao a venda não deve ser realizada



Cenario: Não deve vender produtos sem cliente

Dado que eu venda o produto com "Notebook"​

E com o valor 2500.00 reais e com 0 em estoque​

E para o cliente Null

Quando realizar a venda​

Entao a venda não deve ser realizada



Cenario: Não deve vender sem um produto

Dado que eu venda o produto com Null

E com o valor 2500.00 reais e com 0 em estoque​

E para o cliente "Cliente 01"​

Quando realizar a venda​

Entao a venda não deve ser realizada