# gra-api
Golden Raspberry Awards

API RESTful para possibilitar a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.
Ao executar a aplicação é carregado um arquivo de csv de filmes que se encontra no diretório resources da aplicação.

# Requisitos
Para execução do projeto é necessário a instalação do JDK 11;

A aplicação utiliza o SGBD embarcado H2, sendo assim nenhuma instalação externa é necessária;

Para o desenvolvimento da solução foi utilizando a IDE STS - Spring Tool 4.11.1.RELEASE, porém não é obrigatória a sua instalação para executar a aplicação

A aplicação Backend consiste em um sistema multi-camadas desenlvolvido em Java com base no Framework Spring Boot

Foi utilizado o gerenciador de dependencias Maven 3.6.3 para baixar as bibliotecas utilizadas na aplicação, portanto é necessário sua instalação

# Executando o projeto
O projeto pode ser baixado do repositório github com o seguinte comando (Necessário instalar o github):

```
git clone https://github.com/marconato/gra-api.git
```

Após baixar o projeto do repositório entre no diretório baixado e compile a aplicação com o seguinte código para baixar as dependências:

```
mvn clean install
```

Baixado o projeto e as suas dependências, execute a aplicação com o seguinte comando maven:

```
mvn spring-boot:run
```

A aplicação estará disponível para acesso no seguinte endereço: <a href="http://localhost:8080/">http://localhost:8080</a>

# Serviços disponíveis

Para melhor visualizar os serviços disponíveis nesta aplicação acesse: <a href="http://localhost:8080/swagger-ui.html">http://localhost:8080/swagger-ui.html</a>

# Heroku

A Heroku é uma plataforma nuvem que faz deploy de várias aplicações back-end seja para hospedagem, testes em produção ou escalar as suas aplicações. Também tem integração com o GitHub, deixando o uso mais fácil e com containers denominados Dyno.

Para facilitar o acesso a aplicação esta disponível no heroku através do seguinte link: <a href="https://gra-api.herokuapp.com/swagger-ui.html#/">https://gra-api.herokuapp.com/swagger-ui.html#/</a>

O plano utilizado para deploy no Heroku é free e limitado, mas ao mesmo tempo poderoso: ele permite criar quantas apps forem necessário, mas só uma pode rodar 24x7. O default é a aplicação desligar depois de 30 min sem acesso, caso a aplicação demore para acessar na primeira vez aguarde um instante pois o Heroky este reestabelecendo o serviço;

# Testes

Alguns testes de integração foram implementados na aplicação no pacote de teste e pode ser executado com o comando maven: 

```
mvn test
```
