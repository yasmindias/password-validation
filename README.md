# Password Validation
Esse projeto é uma API REST para validação de senhas. 
Desenvolvido em Kotlin + SpringBoot. 

## Rodando o projeto

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

> Esse projeto não utiliza SSL/TLS pois foge do escopo proposto mas a implementação dessa camada de segurança se faz necessária sempre que passamos dados sensíveis em um request.

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


## Discutindo a solução
Decidi fazer a validação utilizando Regex porque deixa o código mais enxuto e a execução mais eficiente do que validar cada caso separadamente utilizando métodos de string.
Essa validação é feita em duas etapas:

- Verifica se existem caracteres repetidos

> Regex utilizada: `(?=^[\w\d!@#$%^&*()-+]+$)(.)+?.*\1`
>
> 1. A expressão `(?=^[\w\d!@#$%^&*()-+]+$)` verifica se a string possui os caracteres permitidos 
> 2. A expressão `(.)+?` captura um grupo de caracteres repetidos
> 3. A expressão `.*\1` retorna o primeiro grupo de caracteres encontrados

Nesse passo letras minúsculas e maiúsculas são consideradas caracteres diferentes pois na tabela ASCII elas possuem valores diferentes.
É possível adicionar a flag `RegexOption.IGNORE_CASE` para tratar letras maiúsculas e minúsculas como iguais.

- Verifica os outros pontos de validação
  
> Regex utilizada: `^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#\$%^&*()-+])([\w\d!@#\$%^&*()-+]{9,})\$`
> 
> 1. `(?=.*[a-z])` verifica se possui no mínimo uma letra minúscula
> 2. `(?=.*[A-Z])` verifica se possui no mínimo uma letra maiúscula
> 3. `(?=.*\d)` verifica se possui no mínimo um dígito
> 4. `(?=.*\d)(?=.*[!@#\$%^&*()-+])` verifica se possui no mínimo um caracter especial
> 5. `([\w\d!@#\$%^&*()-+]{9,})` verifica se possui nove ou mais caracteres
