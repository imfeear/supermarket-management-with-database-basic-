# 🛒 Sistema de Gerenciamento de Supermercado

<!-- ![Supermarket](https://via.placeholder.com/150) --> 

## 📋 Introdução

O projeto visa desenvolver um **Sistema de Gerenciamento de Supermercado** que ofereça uma solução abrangente para otimizar as operações diárias de um supermercado. O sistema será uma ferramenta centralizada para gerenciar eficientemente o estoque, as vendas, as compras e as finanças.

## 🌟 Funcionalidades

### 📦 Gestão de Estoque
- Cadastro e controle de produtos
- Monitoramento de níveis de estoque
- Alertas de reposição
- Histórico de movimentações

### 🛍️ Gestão de Vendas
- Registro de vendas
- Inclusão de produtos ao carrinho de compras
- Cálculo de valores

### 📑 Gestão de Compras
- Realização de pedidos de reposição de estoque
- Cadastro de fornecedores
- Controle de entregas e pagamentos

### 💰 Gestão Financeira
- Registro de entradas e saídas
- Controle de contas a pagar e a receber
- Relatórios financeiros

## 🛠️ Implementação

- **Linguagem de Programação:** Java
- **Interface Gráfica:** Swing
- **Banco de Dados:** PostgreSQL
- **Controle de Versão:** Git

## 🚀 Começando

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [JDK 17 ou superior (Oracle JDK)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Git](https://git-scm.com/)

### Instalação

1. Clone o repositório
   ```bash
   git clone https://github.com/vitoriabarbosa/GerenciamentoSupermercado.git
   ```
   
### Configuração do Banco de Dados

1. **Criação do Banco de Dados**:
   - Crie um banco de dados vazio chamado `supermercado` no PostgreSQL.

2. **Execução do Script de Configuração**:
   - Antes de executar a aplicação, é necessário configurar o banco de dados. Utilize o script `DatabaseSetup.java` fornecido para configurar a conexão com o banco e adicionar as tabelas necessárias.
   - Abra o projeto no seu ambiente de desenvolvimento.
   - Compile e execute a classe `DatabaseSetup.java` para inicializar o esquema do banco de dados. Certifique-se de que o arquivo `supermercado.sql` está presente no classpath da aplicação.


Exemplo de execução via linha de comando:
   ```bash
   javac DatabaseSetup.java
   java DatabaseSetup
   ```

Isso irá configurar o banco de dados e criar as tabelas necessárias para o sistema.

3. **Execução da Aplicação**:
- Após configurar o banco de dados, você pode executar a aplicação principal que utilizará a conexão configurada (`DatabaseConnection.java`) para interagir com o banco de dados.

### Observações

- Certifique-se de que o PostgreSQL está em execução e acessível antes de executar `DatabaseSetup`.
- Verifique as configurações de conexão no arquivo `DatabaseConnection.java` caso haja necessidade de ajustes nos parâmetros de conexão (`URL_SUPERMERCADO`, `USER`, `PASSWORD`).
