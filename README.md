# Projeto Mottu Spring Boot

Aplicação web para gestão de motos da Mottu, com foco em autenticação, controle de acesso e gerenciamento de motos e usuários.

INTEGRANTES:

Eduardo do Nascimento Barriviera - RM555309

Thiago Lima de Freitas - RM556795

Bruno centurion Fernandes - RM556531

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Security**
- **Thymeleaf** (camada de visualização)
- **Flyway** (controle de versão do banco)
- **H2 Database** (desenvolvimento)
- **Maven** (gerenciamento de dependências)
- **Git** (controle de versão)

---

## Estrutura do Projeto

mottu-springboot
├── src/main/java/com/mottu
│ ├── config
│ │ └── SecurityConfig.java
│ ├── controller
│ │ ├── MotoController.java
│ │ └── UsuarioController.java
│ ├── model
│ │ ├── Moto.java
│ │ └── Usuario.java
│ ├── repository
│ │ ├── MotoRepository.java
│ │ └── UsuarioRepository.java
│ ├── service
│ │ ├── MotoService.java
│ │ └── UsuarioService.java
│ └── MottuApplication.java
├── src/main/resources
│ ├── templates
│ │ ├── fragments
│ │ │ ├── header.html
│ │ │ └── footer.html
│ │ ├── moto
│ │ │ ├── listar.html
│ │ │ ├── criar.html
│ │ │ └── editar.html
│ │ └── usuario
│ │ ├── login.html
│ │ └── cadastrar.html
│ ├── static
│ │ ├── css
│ │ └── js
│ └── db/migration
│ ├── V1__create_usuario_table.sql
│ ├── V2__create_moto_table.sql
│ ├── V3__insert_usuarios.sql
│ └── V4__insert_motos.sql
├── application.properties
├── pom.xml
└── README.md


---

## Funcionalidades

### Thymeleaf (Frontend)
- Listar, criar, editar e excluir motos e usuários.
- Fragmentos reutilizáveis (cabeçalho, rodapé, menu) para reduzir repetição de código.

### Flyway (Banco de Dados)
- Controle de versão do banco de dados.
- Scripts de migração para criação de tabelas e inserção de dados iniciais.

### Spring Security (Autenticação)
- Login e logout via formulário.
- Perfis de usuário: `ADMIN` e `USER`.
- Proteção de rotas com base no perfil do usuário.

### Funcionalidades do Sistema
- CRUD completo de motos e usuários.
- Fluxos completos: 
  1. Cadastro de moto e atribuição de status.
  2. Controle de acesso por tipo de usuário.
- Validações básicas nos formulários e dados (ex: campos obrigatórios, formato de senha).

---

## Requisitos

- Java 17 ou superior
- Maven 3.x
- Git (opcional para versionamento)
- Navegador web moderno

---

## Configuração e Execução

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/mottu-springboot.git
cd mottu-springboot

mvn clean spring-boot:run

http://localhost:8080/h2-console
