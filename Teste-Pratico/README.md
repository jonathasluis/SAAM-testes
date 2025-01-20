# SAAM - TESTE PRÁTICO – VAGA PROGRAMADOR

## Descrição
Teste prático para a vaga de programador, aplicação desenvolvida em Java com interface gráfica usando Swing e integração com um banco de dados PostgreSQL. O sistema permite o cadastro e gerenciamento de usuários e funcionários, com recursos de autenticação e visualização de dados em tabelas.

Este projeto foi desenvolvido como parte de um teste prático para demonstrar habilidades em Java, arquitetura MVC, e integração com bancos de dados.

## Funcionalidades
- Tela de Login: Autenticação de usuários cadastrados no sistema.
- Cadastro de Usuarios;
- Cadastro de Funcionários: Tela para adicionar, editar e excluir funcionários, com uma visão em tabela.
- Conexão com PostgreSQL: Gerenciamento de dados dos funcionários usando SQL.
- Arquitetura MVC: Separação entre a lógica de negócios, apresentação e controle.
  
## Tecnologias Utilizadas
- Java 17: Linguagem principal para o desenvolvimento.
- Swing: Biblioteca para construção da interface gráfica.
- PostgreSQL: Banco de dados relacional para armazenamento de dados.
- NetBeans: IDE utilizada para desenvolvimento.
- Maven: Gerenciador de pacotes.
  
## Pré-requisitos
- Java 8 ou superior
- PostgreSQL (com um banco de dados configurado para a aplicação)
- NetBeans (opcional, mas recomendado para editar o projeto)
- Maven.
  
## Instalação
- Clone o repositório:

```bash
git clone https://github.com/jonathasluis/SAAM-teste-pratico.git
```
- Importe o projeto no NetBeans ou em sua IDE de preferência.
- Configure o banco de dados PostgreSQL:
- Crie um banco de dados.
- Importe o script SQL  para criação das tabelas necessárias.
- Atualize as configurações de conexão no código, se necessário.
- Compile e execute o projeto:

No NetBeans, use a opção "Run" para iniciar o sistema.
Acesse a tela de login para começar a utilizar o sistema.

## Estrutura do Projeto
- src/controller: Contém as classes responsáveis pela lógica de controle da aplicação.
- src/model: Define as classes de modelo para representação dos dados.
- src/view: Inclui as telas e interfaces gráficas da aplicação.
- src/dao: Contem as classes e acesso ao dados das models
- src/util: Contem classes com funcoes auxiliares.

## Autor
Jonathas Sousa 
