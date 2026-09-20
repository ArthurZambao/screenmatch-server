<div align="center">

# 🎬 ScreenMatch

**API de dados de filmes e séries, construída em Java com Spring e PostgreSQL.**

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow?style=for-the-badge)

</div>

---

## 📖 Sobre

O **ScreenMatch** consome a [API da OMDb](https://www.omdbapi.com/) para buscar informações de filmes e séries (títulos, temporadas, episódios, avaliações e sinopses), persiste tudo em um banco PostgreSQL e expõe esses dados de forma organizada.

O projeto foi desenhado em camadas bem definidas, com foco em **orientação a objetos**, **injeção de dependência** e **persistência com JPA/Hibernate**, mantendo o código simples de evoluir e fácil de testar.

## ✨ Funcionalidades

- 🔎 Busca de séries e filmes na OMDb
- 📺 Consulta de temporadas e episódios
- 💾 Persistência dos dados em PostgreSQL
- 🌐 Tradução de sinopses para português (pt-BR)
- 🧱 Arquitetura em camadas (Model, Repository e Service)

## 🛠️ Tecnologias

| Tecnologia | Uso |
|---|---|
| **Java** | Linguagem principal |
| **Spring Boot** | Framework e injeção de dependência |
| **Spring Data JPA / Hibernate** | Mapeamento objeto-relacional e acesso a dados |
| **PostgreSQL** | Banco de dados relacional |
| **OMDb API** | Fonte dos dados de filmes e séries |
| **Maven** | Gerenciamento de dependências e build |

## 🏗️ Arquitetura

O projeto segue uma separação clara de responsabilidades:

```
┌──────────────┐     ┌──────────────┐     ┌──────────────┐     ┌──────────────┐
│   OMDb API   │ ──▶ │   Service    │ ──▶ │  Repository  │ ──▶ │  PostgreSQL  │
└──────────────┘     └──────────────┘     └──────────────┘     └──────────────┘
                            │                     │
                            └──────── Model ──────┘
```

| Camada | Responsabilidade |
|---|---|
| **Model** | Entidades e DTOs que representam o domínio (séries, temporadas, episódios) |
| **Repository** | Acesso ao banco de dados via Spring Data JPA |
| **Service** | Regras de negócio e integração com a API externa |

### 📁 Estrutura de pastas

```
src/main/java/.../screenmatch
├── model/         # Entidades e DTOs
├── repository/    # Interfaces de acesso a dados (JPA)
├── service/       # Regras de negócio e consumo de APIs
└── ScreenmatchApplication.java
```

## 🚀 Como rodar

### Pré-requisitos

- Java 17+
- Maven
- PostgreSQL rodando localmente
- Uma chave da [OMDb API](https://www.omdbapi.com/apikey.aspx)

### Passo a passo

**1. Clone o repositório**

```bash
git clone https://github.com/seu-usuario/screenmatch.git
cd screenmatch
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

## 🗺️ Roadmap

- [x] Camada **Model**
- [x] Camada **Service**
- [ ] Camada **Repository** *(em andamento)*
- [ ] Endpoints REST
- [ ] Testes automatizados
- [ ] Documentação da API com Swagger

## 🤝 Contribuindo

Sugestões e melhorias são bem-vindas. Abra uma *issue* ou envie um *pull request*.

---

<div align="center">

Feito com ☕ e Java por **Arthur**

</div>
