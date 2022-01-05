# Desafio Exactaworks
Criação de  API no padrão Rest que permita o usuário salvar informções sobre Gastos.


### Pré requisitos

* Docker
* Docker CE
* Docker Compose

### Tecnologias

- Spring Boot 2.6.2
- Lombok 1.18
- Flyway 6.3.2
- Java 8
- Jpa 2.2
- Postgres 9.6
- Swagger 3.0

### Executando
- Baixe o projeto e extraia, depois entre na pasta criada.
- Dentro da pasta execute o comando abaixo:  
```
sudo docker-compose up --build -d
```
### Testando API
Após os passos anteriores vc está apto acessar a API:
- Para testar a API poder ser usado tanto o [Postman](https://www.postman.com/) ou qualquer outro cliente de sua preferência.

- Outra opção é acessar o console gráfico do [Swagger](http://localhost:8080/exacta/desafio/swagger-ui.html) onde está
  a documentação da API e clicar na aba ''Gasto Controller''.

#### Lista de End-Points
Abaixo  você encontra as Urls disponíveis.
* Salvar um gasto:
  * POST http://localhost:8080/exacta/desafio/api/V1/gastos-manager/save

  Exemplo de Requisição no formato Json:

```
  {
  "nomeUsuario": "Allan",
  "descricao": "um gasto com bobagem",
  "dataGasto": "02/01/2022 19:47:19",
  "valor": 0.1,
  "tags": "dinheiro poupança"
  }
```

* Buscar um gasto por ID:
  * GET http://localhost:8080/exacta/desafio/api/V1/gastos-manager/find/[ ID DO GASTO]
  
 ```
 http://localhost:8080/exacta/desafio/api/V1/gastos-manager/find/1
  ```

* Listar todos dos gastos:
  * GET http://localhost:8080/exacta/desafio/api/V1/gastos-manager/list
  