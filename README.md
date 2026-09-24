<div align="center">

# 🎬 ScreenMatch

**API REST de dados de filmes e séries, construída em Java com Spring e PostgreSQL.**

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)

</div>

---

## 📖 Sobre

O **ScreenMatch** consome a [API da OMDb](https://www.omdbapi.com/) para buscar informações de filmes e séries (títulos, temporadas, episódios, avaliações e sinopses), traduz as sinopses para português com a API do Gemini, persiste tudo em um banco PostgreSQL e expõe os dados através de uma API REST.

O projeto foi desenhado em camadas bem definidas, com foco em **orientação a objetos**, **injeção de dependência** e **persistência com JPA/Hibernate**, mantendo o código simples de evoluir e fácil de testar.

## ✨ Funcionalidades

- 🔎 Busca de séries e filmes na OMDb
- 📺 Consulta de temporadas e episódios
- 💾 Persistência dos dados em PostgreSQL via JPA/Hibernate
- 🌐 Tradução de sinopses para português (pt-BR) com a API do Gemini
- 🧱 Arquitetura em camadas (Model, Repository, Service e Controller)
- 🌍 API REST com endpoints para consultar séries, temporadas e episódios
- 🔀 CORS configurado para consumo por um front-end

## 🛠️ Tecnologias

| Tecnologia | Uso |
|---|---|
| **Java** | Linguagem principal |
| **Spring Boot** | Framework, injeção de dependência e API REST |
| **Spring Data JPA / Hibernate** | Mapeamento objeto-relacional e acesso a dados |
| **PostgreSQL** | Banco de dados relacional |
| **OMDb API** | Fonte dos dados de filmes e séries |
| **Gemini API** | Tradução das sinopses para pt-BR |
| **Maven** | Gerenciamento de dependências e build |

## 🏗️ Arquitetura

O projeto segue uma separação clara de responsabilidades:

```
┌──────────────┐      ┌──────────────┐      ┌──────────────┐      ┌──────────────┐     ┌──────────────┐
│   Cliente    │ ──▶ │  Controller   │ ──▶ │   Service    │ ──▶ │  Repository  │ ──▶ │  PostgreSQL  │
└──────────────┘      └──────────────┘      └──────────────┘      └──────────────┘     └──────────────┘
                                                  │
                                            OMDb / Gemini API
```

| Camada | Responsabilidade |
|---|---|
| **Controller** | Endpoints REST expostos pela aplicação |
| **DTO** | Objetos de transferência de dados (série e episódio) |
| **Model** | Entidades que representam o domínio (séries, temporadas, episódios) |
| **Repository** | Acesso ao banco de dados via Spring Data JPA, incluindo queries JPQL customizadas |
| **Service** | Regras de negócio e integração com as APIs externas |
| **Config** | Configurações da aplicação (CORS, etc.) |

### 📁 Estrutura de pastas

```
src/main/java/.../screenmatch
├── config/        # Configurações (CORS, etc.)
├── controller/     # Endpoints REST
├── dto/            # Objetos de transferência de dados
├── model/          # Entidades JPA
├── repository/     # Interfaces de acesso a dados (JPA)
├── service/        # Regras de negócio e consumo de APIs
└── ScreenmatchApplication.java
```

## 🌐 Endpoints

Todos os endpoints estão disponíveis sob o prefixo `/series`.

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/series` | Lista todas as séries |
| `GET` | `/series/{id}` | Busca uma série pelo id |
| `GET` | `/series/top5` | Retorna as 5 séries mais bem avaliadas |
| `GET` | `/series/lancamentos` | Retorna os lançamentos mais recentes |
| `GET` | `/series/categoria/{nomeGenero}` | Lista séries por gênero/categoria |
| `GET` | `/series/{id}/temporadas/todas` | Lista todos os episódios de todas as temporadas de uma série |
| `GET` | `/series/{id}/temporadas/{numero}` | Lista os episódios de uma temporada específica |
| `GET` | `/series/{id}/temporadas/top` | Retorna os 5 episódios mais bem avaliados de uma série |

## 🚀 Como rodar

### Pré-requisitos

- Java 17+
- Maven
- PostgreSQL rodando localmente
- Uma chave da [OMDb API](https://www.omdbapi.com/apikey.aspx)
- Uma chave da [Gemini API](https://ai.google.dev/)

### Passo a passo

**1. Clone o repositório**

```bash
git clone https://github.com/ArthurZambao/screenmatch-server.git
cd screenmatch-server
```

**2. Crie o banco de dados**

```sql
CREATE DATABASE screenmatch;
```

**3. Configure as variáveis de ambiente**

```bash
export OMDB_API_KEY=sua_chave_aqui
export GEMINI_API_KEY=sua_chave_aqui   # usada na tradução das sinopses
export DB_URL=jdbc:postgresql://localhost:5432/screenmatch
export DB_USER=seu_usuario
export DB_PASSWORD=sua_senha
```

**4. Execute o projeto**

```bash
./mvnw spring-boot:run
```

**5. Teste a API**

```bash
curl http://localhost:8080/series
```

## 🗺️ Roadmap

- [x] Camada **Model**
- [x] Camada **Service**
- [x] Camada **Repository**
- [x] Endpoints REST
- [ ] Testes automatizados
- [ ] Documentação da API com Swagger

## 🤝 Contribuindo

Sugestões e melhorias são bem-vindas. Abra uma *issue* ou envie um *pull request*.

---

<div align="center">

Feito com ☕ e Java por **Arthur**

</div>
