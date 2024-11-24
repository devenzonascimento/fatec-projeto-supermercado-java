DROP DATABASE IF EXISTS supermercado;
CREATE DATABASE supermercado;
USE supermercado;

/* CRIAÇÃO DE TABELAS NO BANCO DE DADOS */

CREATE TABLE Produto (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  codigo_de_barras VARCHAR(50) NOT NULL,
  preco_venda DECIMAL(10, 2) NOT NULL,
  quantidade_minima INT NOT NULL,
  quantidade_atual INT NOT NULL,
  categoria VARCHAR(100)
);

CREATE TABLE Fornecedor (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  cnpj VARCHAR(18) NOT NULL,
  telefone VARCHAR(15) NOT NULL,
  email VARCHAR(255) unique NOT NULL,
  endereco VARCHAR(255) NOT NULL
);

CREATE TABLE ProdutoFornecido (
  fornecedor_id BIGINT NOT NULL,
  produto_id BIGINT NOT NULL,
  FOREIGN KEY (fornecedor_id) REFERENCES Fornecedor(id),
  FOREIGN KEY (produto_id) REFERENCES Produto(id)
);

CREATE TABLE Cliente (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  telefone VARCHAR(15) NOT NULL,
  email VARCHAR(255) UNIQUE NOT null,
  endereco VARCHAR(255) NOT NULL
);

CREATE TABLE Pagamento (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  data_pagamento DATETIME DEFAULT NOW(),
  valor DECIMAL(10, 2) NOT NULL,
  metodo ENUM('DINHEIRO', 'CREDITO', 'DEBITO', 'PIX') NOT NULL,
  tipo ENUM('ENTRADA', 'SAIDA') NOT NULL
);

CREATE TABLE Pedido (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  data_pedido DATETIME DEFAULT NOW(),
  data_entrega DATE,
  fornecedor_id BIGINT NOT NULL,
  pagamento_id BIGINT,
  status ENUM('PENDENTE', 'ENTREGUE', 'CANCELADO') DEFAULT 'PENDENTE',
  FOREIGN KEY (fornecedor_id) REFERENCES Fornecedor(id),
  FOREIGN KEY (pagamento_id) REFERENCES Pagamento(id)
);

CREATE TABLE Item_Pedido (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  quantidade INT NOT NULL,
  preco_unitario DECIMAL(10, 2) NOT NULL,
  subtotal DECIMAL(10, 2) GENERATED ALWAYS AS (quantidade * preco_unitario) stored,
  pedido_id BIGINT NOT NULL,
  produto_id BIGINT NOT NULL,
  FOREIGN KEY (pedido_id) REFERENCES Pedido(id),
  FOREIGN KEY (produto_id) REFERENCES Produto(id)
);

CREATE TABLE Venda (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  data_venda DATETIME DEFAULT NOW(),
  cliente_id BIGINT,
  pagamento_id BIGINT,
  FOREIGN KEY (cliente_id) REFERENCES Cliente(id),
  FOREIGN KEY (pagamento_id) REFERENCES Pagamento(id)
);

CREATE TABLE Item_Venda (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  quantidade INT NOT NULL,
  preco_unitario DECIMAL(10, 2) NOT NULL,
  subtotal DECIMAL(10, 2) GENERATED ALWAYS AS (quantidade * preco_unitario) stored,
  venda_id BIGINT NOT NULL,
  produto_id BIGINT NOT NULL,
  FOREIGN KEY (venda_id) REFERENCES Venda(id),
  FOREIGN KEY (produto_id) REFERENCES Produto(id)
);


/* CRIAÇÃO DE INDICES NO BANCO DE DADOS */

CREATE INDEX idx_codigo_de_barras ON Produto(codigo_de_barras);

CREATE INDEX idx_nome ON Produto(nome);


/* CRIAÇÃO DE GATILHOS NO BANCO DE DADOS */

DELIMITER //
CREATE TRIGGER atualizar_estoque_ao_receber_um_pedido
AFTER UPDATE ON Pedido
FOR EACH ROW
BEGIN
  -- Verifica se o status mudou de PENDENTE para ENTREGUE
  IF OLD.status = 'PENDENTE' AND NEW.status = 'ENTREGUE' THEN
    -- Atualiza o estoque de cada produto relacionado ao pedido
    UPDATE Produto p
    INNER JOIN Item_Pedido ip ON p.id = ip.produto_id
    SET p.quantidade_atual = p.quantidade_atual + ip.quantidade
    WHERE ip.pedido_id = NEW.id;
  END IF;
END;
// DELIMITER ;

DELIMITER //
CREATE TRIGGER atualizar_estoque_com_saidas_de_produtos
AFTER INSERT ON Item_Venda
FOR EACH ROW
BEGIN
  UPDATE Produto
  SET quantidade_atual = quantidade_atual - NEW.quantidade
  WHERE id = NEW.produto_id;
END;
// DELIMITER ;


/* CRIAÇÃO DE VIEWS NO BANCO DE DADOS */

CREATE OR REPLACE VIEW Relatorio_Vendas AS
SELECT 
  v.id AS ID_Venda,
  v.data_venda AS Data_Venda,
  c.nome AS Cliente,
  c.cpf AS CPF_Cliente,
  c.telefone AS Telefone_Cliente,
  p.nome AS Produto,
  iv.quantidade AS Quantidade,
  iv.preco_unitario AS Preco_Unitario,
  iv.subtotal AS Valor_Total,
  pg.metodo AS Forma_Pagamento
FROM Venda v
LEFT JOIN Cliente c ON v.cliente_id = c.id
INNER JOIN Item_Venda iv ON v.id = iv.venda_id
INNER JOIN Produto p ON iv.produto_id = p.id
LEFT JOIN Pagamento pg ON v.pagamento_id = pg.id
ORDER BY v.data_venda DESC;

CREATE OR REPLACE VIEW Relatorio_Estoque AS
SELECT 
  p.id AS ID_Produto,
  p.nome AS Produto,
  p.quantidade_atual AS Quantidade_Disponivel,
  p.quantidade_minima AS Estoque_Minimo,
  p.preco_venda AS Preco_Unitario,
  (p.quantidade_atual * p.preco_venda) AS Valor_Total_Estoque,
  CASE 
      WHEN p.quantidade_atual < p.quantidade_minima THEN 'Abaixo do Estoque Mínimo'
      ELSE 'Estoque OK'
  END AS Status
FROM Produto p;

CREATE OR REPLACE VIEW Relatorio_Compras AS
SELECT 
  p.id AS ID_Produto,
  p.nome AS Produto,
  f.nome AS Fornecedor,
  ip.quantidade AS Quantidade_Adquirida,
  ip.preco_unitario AS Preco_Unitario,
  ip.subtotal AS Valor_Total,
  pe.data_pedido AS Data_Compra
FROM Item_Pedido ip
INNER JOIN Pedido pe ON ip.pedido_id = pe.id
INNER JOIN Fornecedor f ON pe.fornecedor_id = f.id
INNER JOIN Produto p ON ip.produto_id = p.id
WHERE pe.status = 'ENTREGUE'
ORDER BY pe.data_pedido DESC;

CREATE OR REPLACE VIEW Relatorio_Desempenho_Produtos AS
SELECT 
  p.id AS ID_Produto,
  p.nome AS Produto,
  SUM(iv.quantidade) AS Total_Vendido,
  AVG(iv.preco_unitario - COALESCE(ip.preco_unitario, 0)) AS Margem_Media_Lucro,
  SUM(iv.subtotal) AS Receita_Gerada
FROM Item_Venda iv
INNER JOIN Produto p ON iv.produto_id = p.id
LEFT JOIN 
    (SELECT produto_id, AVG(preco_unitario) AS preco_unitario 
     FROM Item_Pedido 
     GROUP BY produto_id) ip ON p.id = ip.produto_id
GROUP BY p.id, p.nome
ORDER BY Total_Vendido DESC;
 
CREATE OR REPLACE VIEW Relatorio_Fluxo_Caixa AS
SELECT 
  pg.data_pagamento AS Data,
  pg.metodo AS Forma_Pagamento,
  SUM(CASE WHEN pg.tipo = 'ENTRADA' THEN pg.valor ELSE 0 END) AS Total_Entradas,
  SUM(CASE WHEN pg.tipo = 'SAIDA' THEN pg.valor ELSE 0 END) AS Total_Saidas,
  SUM(CASE WHEN pg.tipo = 'ENTRADA' THEN pg.valor ELSE 0 END) - SUM(CASE WHEN pg.tipo = 'SAIDA' THEN pg.valor ELSE 0 END) AS Total_Liquido
FROM Pagamento pg
GROUP BY pg.data_pagamento, pg.metodo
ORDER BY pg.data_pagamento DESC;

CREATE OR REPLACE VIEW Pedidos_Reposicao_Estoque AS
SELECT 
  p.id AS pedido_id,
  p.data_pedido,
  p.data_entrega,
  f.nome AS fornecedor,
  pr.nome AS produto,
  ip.quantidade,
  ip.preco_unitario,
  ip.subtotal,
  p.status
FROM Pedido p
INNER JOIN Fornecedor f ON p.fornecedor_id = f.id
INNER JOIN Item_Pedido ip ON p.id = ip.pedido_id
INNER JOIN Produto pr ON ip.produto_id = pr.id
WHERE p.status = 'PENDENTE' OR p.status = 'ENTREGUE'
ORDER BY p.data_pedido DESC;

CREATE OR REPLACE VIEW Historico_Vendas AS
SELECT
  v.id AS venda_id,
  v.data_venda,
  c.nome AS cliente_nome,
  p.nome AS produto_nome,
  iv.quantidade,
  iv.preco_unitario,
  iv.subtotal,
  pg.metodo AS metodo_pagamento,
  pg.valor AS valor_pagamento
FROM Venda v
LEFT JOIN Cliente c ON v.cliente_id = c.id
INNER JOIN Item_Venda iv ON v.id = iv.venda_id
INNER JOIN Produto p ON iv.produto_id = p.id
LEFT JOIN Pagamento pg ON v.pagamento_id = pg.id;

CREATE OR REPLACE VIEW Historico_Compras AS
SELECT 
  f.id AS fornecedor_id,
  f.nome AS fornecedor_nome,
  COUNT(p.id) AS quantidade_de_compras, 
  SUM(ip.subtotal) AS valor_total_gasto,  
  MAX(p.data_pedido) AS data_ultima_compra,  
  DATEDIFF(MAX(p.data_pedido), MIN(p.data_pedido)) AS frequencia_pedidos_dias  
FROM Fornecedor f
INNER JOIN Pedido p ON f.id = p.fornecedor_id
INNER JOIN Item_Pedido ip ON p.id = ip.pedido_id
WHERE p.status = 'ENTREGUE'
GROUP BY f.id, f.nome
ORDER BY valor_total_gasto DESC;