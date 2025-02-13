# Template API Spring (arquitetura em camadas)

Projeto template com apenas uma entidade (User), com estrutura de:
* Entity
* Repository
* Model
  * Request
  * Response
* Services
* Controllers

# Dependências

* Spring Web
* Spring Data JPA
* Beans Validation
* Banco H2 console para testes
* Lombok
* Mapsctruct


# Anotações

Pequena POC com arquitetura em camadas que possui uma entidade User com seus respectivos 
repositories, services e controllers.

Contém também um teste com CompletableFuture, que é um recurso do Java para processamento 
paralelo de chamadas de API, fazendo com que o serviço aguarde a execução de todas as APIs
chamadas para juntar em um response único. Pode ser útil quando para seguir um fluxo 
seja necessário a resposta de duas ou mais APIs que devem ser executadas em paralelo pois 
a composição do response final tem que ser única.

