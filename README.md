# Projeto Cantina Service

API REST para gerenciamento de uma cantina, permitindo operações como cadastro e consulta de produtos, categorias, usuários e carrinhos de compras.

---

## Tecnologias Utilizadas

* **Java 11**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **Swagger/OpenAPI 3**
* **Maven**

---

## Endereço da Documentação (Swagger)

A documentação da API está disponível em:

Localmente:
```
http://localhost:8097/swagger-ui/index.html
```
Via render (cloud) :
```
https://projeto-cantina-service.onrender.com/swagger-ui/index.html#/
```
> A porta pode ser alterada no arquivo `application.properties`.

---

## Funcionalidades Disponíveis

### Categoria

* Cadastro de categorias
* Consulta de categorias

### Produto

* Cadastro de produtos
* Consulta com paginação
* Filtro por categoria

### Usuário

* Cadastro e listagem de usuários

### Carrinho

* Criação de carrinho com itens
* Atualização e exclusão de carrinho

---

## Como executar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/VtOliv/Projeto-Cantina-Service.git
cd Projeto-Cantina-Service
```

### 2. Compilar o projeto

```bash
mvn clean install
```

### 3. Rodar a aplicação

```bash
mvn spring-boot:run
```

A aplicação será iniciada na porta `8097` (ou definida em `application.properties`).

---

## Estrutura do Projeto

```bash
src/
 ├── main/java/com/project/cantina/
 │   ├── controller/       # Controladores REST
 │   ├── service/          # Regras de negócio
 │   ├── repository/       # Interfaces JPA
 │   ├── domain/           # Entidades JPA
 │   └── configuration/    # Swagger e CORS
 └── resources/
     ├── application.properties
     └── data.sql (se houver)
```

---

## Contato

Desenvolvido por **Vitor Oliveira**

[LinkedIn](https://www.linkedin.com/in/vtoliv/) | [GitHub](https://github.com/VtOliv)

---

## Licença

Este projeto é licenciado sob os termos da licença MIT.
