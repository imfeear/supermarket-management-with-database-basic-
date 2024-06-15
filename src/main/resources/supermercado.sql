-- schema.sql

-- Criação da tabela de Categorias
CREATE TABLE IF NOT EXISTS categorias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- Criação da tabela de Fornecedores
CREATE TABLE IF NOT EXISTS fornecedores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    contato VARCHAR(100)
);

-- Criação da tabela de Produtos
CREATE TABLE IF NOT EXISTS produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    quantidade INT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    categoria_id INT REFERENCES categorias(id) ON DELETE SET NULL,
    fornecedor_id INT REFERENCES fornecedores(id) ON DELETE SET NULL
);

-- Criação da tabela de Estoque
CREATE TABLE IF NOT EXISTS estoque (
    id SERIAL PRIMARY KEY,
    produto_id INT REFERENCES produtos(id) ON DELETE CASCADE,
    estoque_inicial INT NOT NULL,
    entrada TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    saida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estoque_final INT NOT NULL
);

-- Criação da tabela de Movimentações de Estoque
CREATE TABLE IF NOT EXISTS movimentacoes_estoque (
    id SERIAL PRIMARY KEY,
    produto_id INT REFERENCES produtos(id) ON DELETE CASCADE,
    quantidade INT NOT NULL,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('entrada', 'saida'))
);

-- Criação da tabela de Receitas
CREATE TABLE IF NOT EXISTS receitas (
    id SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    valor DECIMAL(10, 2) NOT NULL
);

-- Criação da tabela de Despesas
CREATE TABLE IF NOT EXISTS despesas (
    id SERIAL PRIMARY KEY,
    categoria TEXT NOT NULL,
    valor DECIMAL(10, 2) NOT NULL
);

-- Criação da tabela de Usuário
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

-- Inserindo dados de exemplo nas categorias
INSERT INTO categorias (nome) VALUES
    ('Bebidas'),
    ('Alimentos'),
    ('Higiene'),
    ('Limpeza')
ON CONFLICT DO NOTHING;

-- Inserindo dados de exemplo nos fornecedores
INSERT INTO fornecedores (nome, contato) VALUES
    ('Fornecedor A', 'contato@fornecedora.com'),
    ('Fornecedor B', 'contato@fornecedorb.com')
ON CONFLICT DO NOTHING;

-- Inserindo dados de exemplo nos produtos
INSERT INTO produtos (nome, descricao, quantidade, preco, categoria_id, fornecedor_id) VALUES
    ('Refrigerante', 'Refrigerante sabor cola', 100, 3.50, 1, 1),
    ('Arroz', 'Arroz branco tipo 1', 200, 5.00, 2, 2),
    ('Sabonete', 'Sabonete líquido', 150, 2.75, 3, 1),
    ('Detergente', 'Detergente para louça', 180, 1.50, 4, 2)
ON CONFLICT DO NOTHING;

-- Inserindo dados de exemplo no estoque
INSERT INTO estoque (produto_id, estoque_inicial, entrada, saida, estoque_final) VALUES
    (1, 100, '2024-04-04 10:00:00', '2024-05-10 17:00:00', 80),
    (2, 200, '2024-04-10 10:00:00', '2024-05-20 17:00:00', 50),
    (3, 50, '2024-05-04 10:00:00', '2024-06-05 16:00:00', 20),
    (2, 50, '2024-05-20 10:00:00', '2024-06-15 16:00:00', 30)
ON CONFLICT DO NOTHING;

-- Inserindo dados de exemplo nas movimentações de estoque
INSERT INTO movimentacoes_estoque (produto_id, quantidade, tipo) VALUES
    (1, 50, 'entrada'),
    (2, 100, 'entrada'),
    (3, 30, 'saida'),
    (4, 20, 'saida')
ON CONFLICT DO NOTHING;

-- Inserindo dados de exemplo nas receitas
INSERT INTO receitas (data, valor) VALUES
    ('2023-01-15', 1200.00),
    ('2023-02-20', 2300.75),
    ('2023-03-05', 1800.50),
    ('2023-04-10', 2500.30)
ON CONFLICT DO NOTHING;

-- Inserindo dados de exemplo nas despesas
INSERT INTO despesas (categoria, valor) VALUES
    ('Compra de Estoque', 900.00),
    ('Pagamento de Funcionários', 2200.00),
    ('Manutenção de Equipamentos', 350.45),
    ('Contas de Utilidades', 450.75)
ON CONFLICT DO NOTHING;
