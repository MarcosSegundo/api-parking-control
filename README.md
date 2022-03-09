<h1 align="center">🚙 Parking Spot Control API</h1>
<h3 align="center">Api para controle de vagas de estacionamento.</h3>

<p align="center">

<img src="https://img.shields.io/static/v1?label=Spring&message=2.6.3&color=sucess&style=for-the-badge&logo=spring boot"/>

<img src="https://img.shields.io/static/v1?label=Maven&message=3.8.1&color=red&style=for-the-badge&logo=apache-maven&logoColor=red"/>

<img src="https://img.shields.io/static/v1?label=Docker&message=20.10.7&color=blue&style=for-the-badge&logo=docker"/>

<img src="https://img.shields.io/static/v1?label=Postgres&message=14.2&color=blue&style=for-the-badge&logo=PostgreSQL&logoColor=white"/>

<img src="https://img.shields.io/static/v1?label=License&message=MIT&color=sucess&style=for-the-badge"/>

</p>

<h4 align="center">🚧 Concluído 🚧 </h4>

### ⚙ Funcionalidades

- [x] Cadastro de vaga de estacionamento.
- [x] Listar todas as vagas cadastradas.
- [x] Listar todas as vagas cadastradas paginadas.
- [x] Recuperar um registro de uma vaga por id.
- [x] Deletar um registro de uma vaga por id.
- [x] Atualizar um registro de uma vaga por id.

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:

- [Git](https://git-scm.com)
- [Java 11](https://www.oracle.com/java/technologies/downloads/#java11)
- [Intellij IDEA Community Edition](https://www.jetbrains.com/pt-br/idea/download/#section=linux)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker-Compose](https://docs.docker.com/compose/install/)

### 🎲 Rodando a Aplicação

- [Clone o projeto do github no Intellij Idea.](https://blog.jetbrains.com/idea/2020/10/clone-a-project-from-github/)
- Configure as [variáveis de ambiente](https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html) do arquivo application.yml.
- Crie um arquivo .env para as [variáveis de ambiente do docker-compose](https://docs.docker.com/compose/environment-variables/).

Com tudo configurado o próximo passo é subir o serviço do banco de dados postgres com o docker compose.

```bash
# Subindo o serviço do postgres
$ docker-compose up
```

Agora, com o cursor dentro do arquivo que contém o método main digite o comando: <kbd>Ctrl</kbd> + <kbd>Shift</kbd> + <kbd>F10</kbd> ou clique no ícone de &#9654; no canto superior direito da tela.

O servidor vai rodar na porta padrão do spring.

### 🧪 Rodando os testes

Para rodar os testes você precisará ir em: ***src/test/java/com/api/parkingcontrol*** e escolher se quer rodar os testes da camada service ou controller.

Para rodá-los você precisa clicar no ícone de &#9654; imediatamente ao lado da classe de teste. Esta ação executará todos os testes da camada selecionada.

### 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Java 11](https://www.oracle.com/java/technologies/)
- [Spring-Boot](https://spring.io/projects/spring-boot)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker-Compose](https://docs.docker.com/compose/install/)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Maven](https://maven.apache.org/)
- [Map-Struct](https://mapstruct.org/)
- [Lombok](https://projectlombok.org/)
- [JUnit 5](https://junit.org/junit5/)
- [AssertJ-core](https://assertj.github.io/doc/)
- [Mockito](https://site.mockito.org/)

### Autor

---
Feito por Marcos Segundo 👋🏽 Entre em contato!

[![Gmail Badge](https://img.shields.io/badge/-marcos.segundo.cz@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:tgmarinho@gmail.com)](mailto:tgmarinho@gmail.com)

