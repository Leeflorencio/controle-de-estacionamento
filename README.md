# Controle de estacionamento

<p> Sistema de gerenciamento de vagas de um estacionamento, permitindo o controle de disponibilidade, 
reserva e informações dos veículos.</p>

<<<<<<< HEAD
<p> Sistema de gerenciamento de vagas de um estacionamento, permitindo o controle de disponibilidade, 
reserva e informações dos veículos.</p>
=======
### Features

<p>Endpoint para adicionar, atualizar, listar e remover vagas de estacionamento.

Informações das vagas incluem identificação da vaga, informações sobre o carro (modelo, placa, cor, marca). E informações sobre o responsável, como nome, apartamento e bloco. 
</p>

`Cadastrar | POST /vaga-estacionamento/`

`Listar vagas| GET /vaga-estacionamento/page=2&size=1`

`Listar por ID | GET /vaga-estacionamento/{id}`

`Deletar | DELETE /vaga-estacionamento/{id}`

`Atualizar | PUT /vaga-estacionamento/{id}`
>>>>>>> 8ad50101c0d759f5f56eb19befc9c2b56b09f586


### Features
- Cadastro de vagas

### 🛠 Tecnologias
Java 17 - Postgres - Spring Data JPA - Hibernate - Spring Boot - Postman