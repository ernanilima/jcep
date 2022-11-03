# jCEP (EM ALTERAÇÃO)
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/ernanilima/jcep/blob/main/LICENSE)
[![TESTs](https://img.shields.io/github/workflow/status/ernanilima/jcep/Java%20CI)](https://github.com/ernanilima/jcep/actions/workflows/jcep_ci.yml)


# Sobre o projeto
Microsserviço desenvolvido com a linguagem Java.

jCep foi construído com Java utilizando clean architecture, o objetivo desse microsserviço é ser consumido pela API [jMercado](https://github.com/ernanilima/jmercado-backend).

# Tecnologias utilizadas
- Java
- Spring Boot
- Spring Data
- Swagger
- ViaCEP
- Docker
- H2
- PostgreSQL
- Maven
- I18n (Internacionalização)
- JUnit 5
- Mockito

## AWS
- EC2
- VPC
- RDS

Disponível no [link](http://18.209.186.137/swagger-ui/index.html), acesso ao microsserviço com a interface do swagger

## Validações
* CEP não encontrado  
  - Busca: `http://18.209.186.137/endereco/cep/12345678`
  - Resultado: `"message": "Não localizado o CEP 12345678"`
* CEP incompleto
  - Busca: `http://18.209.186.137/endereco/cep/1234`
  - Resultado: `"message": "O CEP deve ter 8 caracteres numéricos"`
* Remover caracteres não numéricos
  - Busca: `http://18.209.186.137/endereco/cep/0a1b0c0d1e0f0g0h`
  - Resultado: `Status 200 OK`
* Internacionalização para erros
  - Busca pt_BR: `http://18.209.186.137/endereco/cep/12345678?language=pt_BR`
  - Resultado pt_BR: `"message": "Não localizado o CEP 12345678"`
  - Busca en_US: `http://18.209.186.137/endereco/cep/12345678?language=en_US`
  - Resultado en_US: `"message": "Zip code 12345678 not found"`
* Internacionalização com valor inválido
  - Busca: `http://18.209.186.137/endereco/cep/01001000?language=es_AR`
  - Resultado: `message": "Idioma inválido, use 'en_US' ou 'pt_BR'"`
