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

-- Criação da tabela de Movimentações de Estoque
CREATE TABLE IF NOT EXISTS movimentacoes_estoque (
    id SERIAL PRIMARY KEY,
    produto_id INT REFERENCES produtos(id) ON DELETE CASCADE,
    quantidade INT NOT NULL,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('entrada', 'saida'))
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

-- Inserindo dados de exemplo nas movimentações de estoque
INSERT INTO movimentacoes_estoque (produto_id, quantidade, tipo) VALUES
    (1, 50, 'entrada'),
    (2, 100, 'entrada'),
    (3, 30, 'saida'),
    (4, 20, 'saida')
ON CONFLICT DO NOTHING;