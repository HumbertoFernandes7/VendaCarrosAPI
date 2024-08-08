# API de Estoque de Mercado

Esta é uma simples API desenvolvida em Spring Boot para gerenciar uma pagina de venda de carros. A API permite realizar operações CRUD para carros

## Tecnologias Utilizadas

- Spring Boot
- MySQL
- Maven (para gerenciamento de dependências)
- Spring Data JPA (para integração com o banco de dados)
- Spring Web (para exposição dos endpoints REST)
- Spring Security (para segurança da API)

## Pré-requisitos

- Java JDK 8 ou superior instalado

## Configuração

1. Clone este repositório:
2. importe em uma IDE de sua preferencia
3. Algum aplicativo pode ser usado para testar as requisições, como o Postman.

## Requisições

- Login: http://localhost:8080/auth

Administrador
body: {
"login": "admin",
"senha": "admin"
}

Usuario
body: {
"login": "user",
"senha": "user"
}

- Cadastrar carro: http://localhost:8080/carros/cadastrar

body: {
"nome": "nomeCarro",
"marca": "marcaCarro",
"modelo": "modeloCarro",
"valor": "00.000"
"foto": "fotoConvertidaEmBase64"
}

- Alterar carro: http://localhost:8080/carros/atualizar

body: {
"nome": "nomeCarro",
"marca": "marcaCarro",
"modelo": "modeloCarro",
"valor": "00.000"
"foto": "fotoConvertidaEmBase64"
}

- Deletar carro: http://localhost:8080/carros/deletar/0

No lugar de 0 coloque o ID do carro que deseja deletar.

- Listar todos os carros: http://localhost:8080/carros/lista-todos

- Listar carro pelo ID: http://localhost:8080/carros/busca/0

No lugar de 0 coloque o ID do carro que deseja listar.




