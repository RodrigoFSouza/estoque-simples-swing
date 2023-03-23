# Estoque Simples Swing

Este projeto é um exemplo de um sistema de estoque simples, utilizando Java Swing. Com esta aplicação, você pode gerenciar os produtos do seu estoque, adicionando, editando e removendo-os.

## Requisitos
* Java 8 ou superior
* Maven 3.6.3 ou superior

## Tecnologias utilizadas
* Java 8
* Maven
* Swing
* Hibernate
* PostgreSQL

## Executando a aplicação
Para executar a aplicação, siga os seguintes passos:

1. Instale o Postgresql se já não estiver instalado, Crie um banco de dados no PostgreSQL com o nome `estoque_simples_swing`
```
CREATE DATABASE estoque_simples_swing;
```

2. Clone o repositório
```
git clone https://github.com/RodrigoFSouza/estoque-simples-swing.git
```

3. Acesse o diretório do projeto:
```
cd estoque-simples-swing
```
4. Acesse a pasta script e execute no banco `criacao_tabelas.sql` no banco de dados criado no passo 1. 
O script está localizado em `src/main/resources/script/criacao_tabelas.sql
```
psql -d estoque_simples_swing -f src/main/resources/script/criacao_tabelas.sql
```

5. Compile o projeto com o Maven:
```
mvn compile
```

6. Execute o projeto com o Maven:
``` 
mvn exec:java
```

## Utilizando a aplicação
Ao executar a aplicação, você será apresentado a uma tela principal, onde pode visualizar todos os produtos em seu estoque. A partir daí, você pode adicionar, editar e remover produtos conforme necessário.

## Licença
Este projeto está licenciado sob a licença MIT - consulte o arquivo [LICENSE](LICENSE) para obter detalhes.
