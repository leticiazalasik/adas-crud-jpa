# adas-crud-jpa

Este repositório é uma prática feita em sala de aula para exercitar o CRUD com spring e JPA. 

##O que esse projeto contém? 

:diamond_shape_with_a_dot_inside:Classe categoria com seu CRUD e método buscar por Id. Essa classe possui todos os endpoints de acesso a cada método. 
:diamond_shape_with_a_dot_inside:Classe Produto com seu CRUD e método buscar por Id, venderProduto, comprarProduto².Essa classe possui todos os endpoints de acesso a cada método. 

1- venderProduto: verifica se o caixa está ativo, se a quantidade que se quer comprar é menor ou igual que a quatidade do produto no estoque, se sim o estoque diminui, o saldo do caixa é atualizado e é realizada uma movimentaçao do tipo entrada. 

2- comprarProduto:vê se o caixa existe e se ele está ativo(aberto) e verifica se o produto existe na lista de produtos. Se sim, o estoque do produto aumenta, o saldo do caixa é atualizado e é realizada uma movimentaçao do tipo saída. 

:diamond_shape_with_a_dot_inside:Classe Caixa com seu CRUD e método buscar por Id, , abrirCaixa³, fecharCaixa⁴, mostrarEntradas⁵, mostrarSaidas⁶. Essa classe possui todos os endpoints de acesso a cada método. Na service foi feito o método realizarMovimentacao⁷. 
3-abrirCaixa: Verifica se o caixa existe e se ele está aberto, então atualiza o status para false. 

4- fecharCaixa: Verifica se o caixa existe e se ele está fechado, então atualiza o status para true. 

5- mostrarEntradas: Retorna a somatória de todas as entradas do caixa. 

6- mostrarSaidas: Retorna a somatória de todas as saídas do caixa

7- realizarMovimentacao: verifica se o caixa existe pelo id recebido, recebe por parametro tambem a acao - se é entrada ou saída - aqui que é mudado o saldo do caixa e somada as entradas ou saidas o valor dessa movimentação. 



## 👨‍💻️ Tecnologias Utilizadas
Esse projeto foi criado utilizando as tecnologias:
#### :small_blue_diamond: IntelliJ - JAVA, Spring Boot. 
#### :small_blue_diamond: H2 - banco de dados relacional <br><br>
