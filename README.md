# 🛒 Sistema de Gerenciamento de Supermercado
![GitHub repo size](https://img.shields.io/github/repo-size/vitoriabarbosa/GerenciamentoSupermercado?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/vitoriabarbosa/GerenciamentoSupermercado?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/vitoriabarbosa/GerenciamentoSupermercado?style=for-the-badge)
![Bitbucket open issues](https://img.shields.io/bitbucket/issues/vitoriabarbosa/GerenciamentoSupermercado?style=for-the-badge)
![Bitbucket open pull requests](https://img.shields.io/github/issues-pr-closed/vitoriabarbosa/GerenciamentoSupermercado?style=for-the-badge)

<br>

## 📋 Introdução
>O projeto visa desenvolver um **Sistema de Gerenciamento de Supermercado**, uma ferramenta centralizada que ofereça uma solução abrangente para gerenciar e otimizar as operações diárias de um supermercado.

>### 🌟 Funcionalidades
>- 📦 **Gestão de Estoque**: Cadastro e controle de produtos, monitoramento de níveis de estoque, alertas de reposição, histórico de movimentações.
>- 🛍️ **Gestão de Vendas**: Registro de vendas, inclusão de produtos ao carrinho de compras, cálculo de valores.
>- 📑 **Gestão de Compras**: Realização de pedidos de reposição de estoque, cadastro de fornecedores, controle de entregas e pagamentos.
>- 💰 **Gestão Financeira**: Registro de entradas e saídas, controle de contas a pagar e a receber, relatórios financeiros.

<br>

## 🛠️ Implementação
- **Linguagem de Programação:** Java
- **Interface Gráfica:** Swing
- **Gerente de Dependência:** Maven
- **Banco de Dados:** PostgreSQL
- **Controle de Versão:** Git
- **Organização de Tarefas:** Trello
- **Protótipo do Design:** Figma

<br>

## 🚀 Começando

### Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [JDK 17 ou superior (Oracle JDK)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Git](https://git-scm.com/)

Softwares úteis:
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) ou outra IDE de sua preferência
- [PgAdmin4](https://www.pgadmin.org/download/) - Interface Gráfica para PostgreSQL

### Configuração Adicional
Para uma experiência de desenvolvimento mais eficiente, recomendamos o uso do **IntelliJ IDEA** como IDE principal para este projeto. 

O **PgAdmin4** também pode ser utilizado para gerenciar o banco de dados PostgreSQL de forma gráfica.

Certifique-se de configurar corretamente o PgAdmin4 para se conectar ao seu servidor PostgreSQL local onde o banco de dados `supermercado` está configurado.

### Instalação

1. #### Clone o repositório
   ```bash
   git clone https://github.com/vitoriabarbosa/GerenciamentoSupermercado.git

2. #### Navegar até o Diretório do Projeto:
   ```bash
   cd GerenciamentoSupermercado

3. #### Instalar o JDK 17 ou Superior.
   - Verifique se você tem o JDK 17 ou superior instalado.

4. #### Instalar o Maven:
   - Instale o Maven a partir do site oficial.
   - Adicione o Maven ao seu PATH conforme as instruções de instalação do site.

5. #### Instalar o PostgreSQL:
   - Se você ainda não tiver o PostgreSQL instalado, baixe a partir do site oficial.
   - Durante a instalação, configure o usuário e a senha do PostgreSQL.

6. #### Criar o Banco de Dados:
   - Abra o pgAdmin ou o terminal do PostgreSQL.
   - Crie um banco de dados chamado `supermercado`:
      ```sql
      CREATE DATABASE supermercado;

7. #### Configurar a Conexão com o Banco de Dados:
   - No arquivo `DatabaseConnection.java`, verifique se os detalhes da conexão estão corretos:
      ```java
      private static final String URL_SUPERMERCADO = "jdbc:postgresql://localhost:5432/supermercado";
      private static final String USER = "seu_usuario_aqui";
      private static final String PASSWORD = "sua_senha_aqui";

8. #### Compilar e Executar o Script de Configuração do Banco de Dados:
   - Compile e execute a classe `DatabaseSetup.java` para inicializar o banco de dados com as tabelas necessárias.
   - Certifique-se de que o arquivo `supermercado.sql` está no classpath.

      No terminal, execute:
      ```bash
      mvn compile exec:java -Dexec.mainClass="com.ijala.database.DatabaseSetup"

9. #### Executar a Aplicação Principal:
   - Execute a aplicação principal.
   - Certifique-se de que todas as dependências estão no classpath.
   
      No terminal, execute:
      ```bash
      mvn exec:java -Dexec.mainClass="com.ijala.Main"

### Observações
- Certifique-se de que o PostgreSQL está em execução e acessível antes de executar `DatabaseSetup`.
- Verifique as configurações de conexão no arquivo `DatabaseConnection.java` caso haja necessidade de ajustes nos parâmetros de conexão (`URL_SUPERMERCADO`, `USER`, `PASSWORD`).
- Caso haja algum problema de compilaçao exclua a pasta `target` e tente novamente.

<br>

## 🤝 Colaboradores
Agradecemos às seguintes pessoas que contribuíram para este projeto:
<table>
   <tr>
      <td align="center">
          <a href="https://www.linkedin.com/in/vitoriabarbosaa/" title="LinkedIn de Vitória">
            <img src="https://avatars.githubusercontent.com/u/93888309?v=4" width="100px;" alt="foto"/><br>
            <sub>
              <b>Vitória Barbosa</b>
            </sub>
          </a>
        </td>
      <td align="center">
          <a href="https://www.linkedin.com/in/j%C3%B4natas-ariel-futuredev/" title="LinkedIn de Jônatas">
            <img src="https://avatars.githubusercontent.com/u/113778501?v=4" width="100px;" alt="foto"/><br>
            <sub>
              <b>Jônatas Ariel</b>
            </sub>
          </a>
      </td>
      <td align="center">
          <a href="https://www.linkedin.com/in/willams-campos-05aaa11bb/" title="linkedIn de Williams">
            <img src="https://avatars.githubusercontent.com/u/114779612?v=4" width="100px;" alt="foto"/><br>
            <sub>
              <b>Williams Campos</b>
            </sub>
          </a>
      </td>
      <td align="center">
          <a href="#" title="LinkedIn de Bruno">
            <img src="#" width="100px;" alt="foto"/><br>
            <sub>
              <b>Bruno Reis</b>
            </sub>
          </a>
      </td>
   </tr>
</table><br>

## 📝 Licença
Este projeto é licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE.md) para mais detalhes.
