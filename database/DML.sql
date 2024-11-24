USE supermercado;


/* INSERÇÕES NO BANCO DE DADOS */

-- Inserções na tabela Produto
INSERT INTO Produto (nome, codigo_de_barras, preco_venda, quantidade_minima, quantidade_atual, categoria) VALUES
('Arroz', '7891234567890', 20.50, 10, 50, 'Alimentos'),
('Feijão', '7891234567891', 8.90, 15, 30, 'Alimentos'),
('Detergente', '7891234567892', 2.75, 5, 20, 'Limpeza');

-- Inserções na tabela Fornecedor
INSERT INTO Fornecedor (nome, cnpj, telefone, email, endereco) VALUES
('Fornecedor A', '12.345.678/0001-90', '11987654321', 'contato@fornecedora.com', 'Rua A, 123'),
('Fornecedor B', '98.765.432/0001-65', '21987654321', 'contato@fornecedorb.com', 'Rua B, 456'),
('Fornecedor C', '11.222.333/0001-88', '31987654321', 'contato@fornecedorc.com', 'Rua C, 789');

-- Inserções na tabela ProdutoFornecido
INSERT INTO ProdutoFornecido (fornecedor_id, produto_id) VALUES
(1, 1),
(2, 2),
(3, 3);

-- Inserções na tabela Cliente
INSERT INTO Cliente (nome, cpf, telefone, email, endereco) VALUES
('João Silva', '123.456.789-00', '11987654321', 'joao@email.com', 'Rua 1, 100'),
('Maria Oliveira', '987.654.321-11', '21987654321', 'maria@email.com', 'Rua 2, 200'),
('Carlos Santos', '111.222.333-44', '31987654321', 'carlos@email.com', 'Rua 3, 300');

-- Inserções na tabela Pagamento
INSERT INTO Pagamento (data_pagamento, valor, metodo, tipo) VALUES
(NOW(), 100.00, 'DINHEIRO', 'ENTRADA'),
(NOW(), 250.50, 'PIX', 'ENTRADA'),
(NOW(), 50.75, 'CREDITO', 'SAIDA');

-- Inserções na tabela Pedido
INSERT INTO Pedido (data_pedido, data_entrega, fornecedor_id, pagamento_id, status) VALUES
(NOW(), '2024-11-25', 1, 1, 'PENDENTE'),
(NOW(), '2024-11-26', 2, 2, 'ENTREGUE'),
(NOW(), '2024-11-27', 3, 3, 'CANCELADO');

-- Inserções na tabela Item_Pedido
INSERT INTO Item_Pedido (quantidade, preco_unitario, pedido_id, produto_id) VALUES
(10, 20.00, 1, 1),
(5, 8.50, 2, 2),
(2, 3.00, 3, 3);

-- Inserções na tabela Venda
INSERT INTO Venda (data_venda, cliente_id, pagamento_id) VALUES
(NOW(), 1, 1),
(NOW(), 2, 2),
(NOW(), 3, 3);

-- Inserções na tabela Item_Venda
INSERT INTO Item_Venda (quantidade, preco_unitario, venda_id, produto_id) VALUES
(2, 20.50, 1, 1),
(1, 8.90, 2, 2),
(3, 2.75, 3, 3);


/* UPDATES NO BANCO DE DADOS */

-- Atualizações na tabela Produto
UPDATE Produto
SET preco_venda = 21.00, quantidade_atual = 45
WHERE id = 1;

UPDATE Produto
SET preco_venda = 9.50, quantidade_atual = 25
WHERE id = 2;

-- Atualizações na tabela Fornecedor
UPDATE Fornecedor
SET telefone = '11999998888'
WHERE id = 1;

UPDATE Fornecedor
SET email = 'novoemail@fornecedorb.com'
WHERE id = 2;

-- Atualizações na tabela Cliente
UPDATE Cliente
SET endereco = 'Rua 10, 150'
WHERE id = 1;

UPDATE Cliente
SET telefone = '31999997777'
WHERE id = 3;

-- Atualizações na tabela Pedido
UPDATE Pedido
SET status = 'ENTREGUE'
WHERE id = 1;

-- Atualizações na tabela Venda
UPDATE Venda
SET cliente_id = 2
WHERE id = 1;


/* DELETES NO BANCO DE DADOS */

-- Remoções na tabela Produto
DELETE FROM Produto
WHERE id = 3;

-- Remoções na tabela Fornecedor
DELETE FROM Fornecedor
WHERE id = 3;

-- Remoções na tabela ProdutoFornecido
DELETE FROM ProdutoFornecido
WHERE fornecedor_id = 3 AND produto_id = 3;

-- Remoções na tabela Cliente
DELETE FROM Cliente
WHERE id = 3;

-- Remoções na tabela Pagamento
DELETE FROM Pagamento
WHERE id = 3;

-- Remoções na tabela Pedido
DELETE FROM Pedido
WHERE id = 3;

-- Remoções na tabela Item_Pedido
DELETE FROM Item_Pedido
WHERE id = 3;

-- Remoções na tabela Venda
DELETE FROM Venda
WHERE id = 3;

-- Remoções na tabela Item_Venda
DELETE FROM Item_Venda
WHERE id = 3;