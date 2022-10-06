# Password Validation
Esse projeto é uma API REST para validação de senhas.

## Instalação
### Requisitos
- JDK 8 ou superior

## Rodando o projeto
```
./gradlew clean bootRun
```

### Utilizando a API
A API pode ser acessada pela url `localhost:8080`.

**Requisição**

`POST /validate`
```http
{
  "password": "password"
}
```

**Resposta**
```
HTTP/1.1 200 OK
Date: Thu, 24 Feb 2011 12:36:31 GMT
Status: 200 OK
Connection: close
Content-Type: application/json
Body: 

{
  false
}
```

## Rodando os testes

### Testes unitários
`./gradlew clean test`


### Testes de Integração
`./gradlew clean integrationTest`