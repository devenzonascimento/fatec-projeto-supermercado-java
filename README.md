# Projeto Supermercado

### Este projeto foi desenvolvido com as matérias de Labooratório de Banco de Dados e Programação Orientada a Objetos

## Segue abaixo o case usado para a construção do projeto:

Visão Geral
O sistema para supermercado será desenvolvido em Java, utilizando princípios de Programação Orientada a Objetos (POO) para garantir organização e manutenibilidade do código. A base de dados será gerida por um banco de dados MySQL, que armazenará informações sobre produtos, vendas, estoque e relatórios gerenciais.

O sistema deve ser capaz de controlar o estoque, realizar vendas, emitir relatórios gerenciais e fornecer dados de maneira simples e eficaz tanto para os gestores quanto para os operadores de caixa. A seguir, será detalhado o escopo, as funcionalidades e os relatórios esperados.

Funcionalidades Principais do Sistema

1)Controle de Estoque

- Cadastro de Produtos: Cadastro de novos produtos com informações como nome, código de barras, preço de venda, preço de compra, categoria, fornecedor e quantidade inicial em estoque.

- Ajuste de Estoque: Permite o ajuste manual de quantidades de produtos no estoque (por exemplo, em caso de erros de contagem, perdas ou devoluções).

- Entrada de Produtos: Registro de entradas de produtos no estoque a partir de compras de fornecedores.

- Saída de Produtos: Redução automática do estoque durante o processo de venda.

- Alerta de Estoque Baixo: Notificação quando o estoque de um produto atingir um nível mínimo configurado.


2)Gestão de Vendas
   
- Venda de Produtos: Interface para realizar vendas no caixa, onde é possível buscar produtos por código de barras ou nome. Durante a venda, o sistema reduz automaticamente o estoque.

- Pagamento: Possibilidade de registrar diferentes formas de pagamento, como dinheiro, cartão de crédito/débito, PIX, etc.

- Histórico de Vendas: Acompanhamento de todas as transações realizadas, com filtros por período, cliente e produto.


3)Relatórios Gerenciais

- Relatório de Vendas: Relatório detalhado com informações sobre todas as vendas realizadas, incluindo data, produto, quantidade, valor total, forma de pagamento e informações do cliente (se disponível).
  
- Relatório de Estoque: Relatório do estoque atual de todos os produtos, com informações de quantidade disponível, valor total em estoque, e o status (se o produto está abaixo do estoque mínimo).
  
- Relatório de Compras: Relatório detalhado das compras realizadas junto aos fornecedores, com informações sobre quantidade adquirida, preço de compra, e data de compra.
  
- Relatório de Desempenho de Produtos: Relatório sobre os produtos mais vendidos, menos vendidos e com maior margem de lucro.
  
- Relatório de Fluxo de Caixa: Resumo financeiro das entradas e saídas de dinheiro, detalhando as formas de pagamento e o lucro líquido.


4)Gestão de Fornecedores

- Cadastro de Fornecedores: Cadastro de informações dos fornecedores, como nome, CNPJ, endereço, e informações de contato.

- Compras e Pedidos: Registro de pedidos realizados aos fornecedores, com quantidade e preço, para reposição de estoque.

- Histórico de Compras: Histórico de compras realizadas a cada fornecedor, permitindo uma visão clara dos gastos e frequência de pedidos.

Instruções

Parte de POO:
Faça o modelo de classe UML e implemente todas as classes de regra de negocio
Escolha um modulo 1-controle de estoque, 2-gestão de vendas  3-relatorio gerenciais ou 4-gestão de fornecedores para criar a integração com o banco de dados e criação das telas usando java SWING

Parte de LBD:
Deverá ser entregue o MER os scripts DDL e DML assim como as consultas DQL 

## Modelagem do Banco de Dados
![MER-SUPERMERCADO-LBD-P2](https://github.com/user-attachments/assets/1bd8e290-d23a-4e4b-b9a0-a9c4f4fc0cfa)

## Diagrama de classe UML
![Diagrama-de-classes-UML-final](https://github.com/user-attachments/assets/ec97f7e5-538e-49f1-8d89-c87a02613498)
