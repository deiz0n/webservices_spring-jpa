# Gerenciador de Pedidos - API

## Descrição
A API tem como objetivo principal simplificar o gerenciamento de pedidos, oferecendo uma variedade de recursos essenciais. 
Com ela, você pode listar pedidos, obtendo detalhes sobre o cliente, os produtos e as quantidades em cada pedido, 
bem como o preço total. Importante ressaltar que tanto a inserção de novos dados quanto a obtenção de informações 
são realizadas por meio do formato JSON, garantindo uma integração fácil e flexível.

## Pré-requisitos
Certifique-se de ter os seguintes requisitos instalados:
- Java 17
- Maven
- Spring Framework 3
- PostgreSQL (para a base de dados)
- IDE de desenvolvimento de sua escolha (por exemplo, IntelliJ IDEA ou Eclipse)

## Configuração

### 1. Clonar o Repositório

Clone este repositório para o seu ambiente de desenvolvimento:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
```

## Configuração do Banco de Dados
Certifique-se de que o PostgreSQL esteja instalado e configurado corretamente. Edite o arquivo 
```src/main/resources/application.properties``` para configurar as informações de conexão com o banco de dados:
```spring.datasource.url=jdbc:postgresql://localhost:5432/seu-banco-de-dados``` <br>
```spring.datasource.username=seu-usuario```<br>
```spring.datasource.password=sua-senha```

## Compilar e Executar o Projeto
Navegue até o diretório do projeto:
```bash
cd seu-repositorio
```

Compile o projeto com Maven:
```bash
mvn clean install
```

Execute a aplicação:
```bash
mvn spring-boot:run
```
A API estará disponível em `http://localhost:8080`.

## Documentação

A documentação completa da API, incluindo todos os endpoints, parâmetros e exemplos de uso, 
pode ser encontrada [aqui](https://webservices-spring-jpa-fce4ec38f89c.herokuapp.com/swagger-ui/index.html).

## Contribuindo

Se você deseja contribuir com este projeto, siga estas etapas:

1. Faça um fork do repositório. <br>
2. Crie uma branch para sua feature: `git checkout -b minha-feature` <br>
3. Faça commit das suas alterações: `git commit -m 'Adicionar nova feature` <br>
4. Faça push para a branch: `git push origin minha-feature` <br>
5. Abra um pull request. <br>

## Contribuindo

Para entrar em contato, envie um email para doardo.ns@gmail.com ou visite meu perfil no Linkedln [aqui](https://www.linkedin.com/in/carlos-eduardo-ns/).
