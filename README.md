# adas-crud-jpa

Este repositório é uma prática feita em sala de aula para exercitar o CRUD com spring e JPA. 

O que esse projeto contém? 
:diamond_shape_with_a_dot_inside:Classe categoria com seu CRUD e método buscar por Id. Essa classe possui todos os endpoints de acesso a cada método. 
:diamond_shape_with_a_dot_inside:Classe Produto com seu CRUD e método buscar por Id, venderProduto, comprarProduto.Essa classe possui todos os endpoints de acesso a cada método. 

*venderProduto: verifica se o caixa está ativo, se a quantidade que se quer comprar é menor ou igual que a quatidade do produto no estoque, se sim o estoque diminui, o saldo do caixa é atualizado e é realizada uma movimentaçao do tipo entrada. 
**comprarProduto:vê se o caixa existe e se ele está ativo(aberto) e verifica se o produto existe na lista de produtos. Se sim, o estoque do produto aumenta, o saldo do caixa é atualizado e é realizada uma movimentaçao do tipo saída. 

:diamond_shape_with_a_dot_inside:Classe Caixa com seu CRUD e método buscar por Id, abrirCaixa, fecharCaixa, mostrarEntradas, mostrarSaidas. Essa classe possui todos os endpoints de acesso a cada método. Na service foi feito o método realizarMovimentacao. 
*abrirCaixa: Verifica se o caixa existe e se ele está aberto, então atualiza o status para false. 
**fecharCaixa: Verifica se o caixa existe e se ele está fechado, então atualiza o status para true. 
***mostrarEntradas: Retorna a somatória de todas as entradas do caixa. 
***mostrarSaidas: Retorna a somatória de todas as saídas do caixa
****



## 👨‍💻️ Tecnologias Utilizadas
Esse projeto foi criado utilizando as tecnologias:
#### :small_blue_diamond: IntelliJ - JAVA, Spring Boot. 
#### :small_blue_diamond: H2 - banco de dados relacional <br><br>



Caixa caixa = caixaService.findById(idCaixa);
        if (!caixa.isStatus()) {
            return ResponseEntity.ok("Não foi possível realizar a venda pois o caixa "+ idCaixa + " está fechado.");

        }
        if (produtoAtual.getQuantidade()<quantidade ) {
            return ResponseEntity.ok("Estoque insuficiente para venda.");
        }
        produtoAtual.setQuantidade(produtoAtual.getQuantidade()-quantidade);
        produtoService.save(produtoAtual);

        //Valor entrada
        double valorVenda= produto.getPreco()*quantidade;

        //atualizar o saldo do caixa
        caixa = caixaService.realizarMovimentacao(idCaixa, valorVenda, "entrada");

        String recibo = "" +
                "Produto vendido: "+ produtoAtual.getNome() +
                "\n Valor Total da venda: R$ " + valorVenda +
                "\n Caixa atualizado: " + idCaixa +
