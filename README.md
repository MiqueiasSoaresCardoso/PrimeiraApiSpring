# API Biblioteca IFPB (Spring Boot + Java 17)

API REST simples para gerenciar **Livros** e **Empréstimos** de uma biblioteca, desenvolvida com **Java 17** e **Spring Boot**.

---

## ✅ Funcionalidades

### 📖 Livros
- Cadastrar livro
- Listar livros
- Buscar livro por ID
- Atualizar livro
- Remover livro
- Bloqueio de ISBN duplicado (**409 Conflict**)

### 📦 Empréstimos
- Criar empréstimo
- Listar empréstimos
- Buscar empréstimo por ID
- Realizar devolução
- Livro fica **indisponível** quando emprestado
- Define **dataDevolucaoFinal = dataEmprestimo + 30 dias**

---

## - Tecnologias
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database

---

## ▶️ Como executar
1. Abra o projeto no **IntelliJ**
2. Rode a classe principal (`ApiDeLivrosApplication` ou equivalente)
3. A API ficará disponível em:  
   `http://localhost:8080`

---

## 🗄️ Banco de Dados (H2 Console)
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:biblioteca` *(ou conforme seu `application.properties`)*
- User: `sa`
- Password: *(vazio)*

---

# 📌 Endpoints

## 🔹 LIVROS

### ✅ 1) Cadastrar livro
**POST** `/livros`  
Header: `Content-Type: application/json`

**Body (exemplo):**
```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "isbn": "9780132350884",
  "anoPublicacao": 2008
}
```

**Respostas esperadas:**
- `201 Created` (sucesso)
- `409 Conflict` (ISBN duplicado)

---

### ✅ 2) Listar livros
**GET** `/livros`

---

### ✅ 3) Buscar livro por ID
**GET** `/livros/{id}`  
Exemplo: `/livros/1`

**Respostas esperadas:**
- `200 OK` (encontrado)
- `404 Not Found` (não encontrado)

---

### ✅ 4) Atualizar livro
**PUT** `/livros/{id}`  
Header: `Content-Type: application/json`

**Body (exemplo):**
```json
{
  "titulo": "Clean Code (Edição Atualizada)",
  "autor": "Robert C. Martin",
  "isbn": "9780132350884",
  "anoPublicacao": 2009,
  "disponivel": true
}
```

---

### ✅ 5) Remover livro
**DELETE** `/livros/{id}`

**Respostas esperadas:**
- `204 No Content` (removido)
- `404 Not Found` (não encontrado)

---

## 🔹 EMPRÉSTIMOS

### ✅ 1) Criar empréstimo
**POST** `/emprestimos`  
Header: `Content-Type: application/json`

**Body (exemplo):**
```json
{
  "matriculaAluno": "202612345",
  "livroId": 1
}
```

**Respostas esperadas:**
- `201 Created` (sucesso)
- `409 Conflict` (livro indisponível)

---

### ✅ 2) Listar empréstimos
**GET** `/emprestimos`

---

### ✅ 3) Buscar empréstimo por ID
**GET** `/emprestimos/{id}`

---

### ✅ 4) Realizar devolução
**POST** `/emprestimos/{id}/devolucao`  
Exemplo: `POST /emprestimos/1/devolucao`

**Respostas esperadas:**
- `200 OK` (devolução realizada)
- `409 Conflict` (empréstimo já encerrado)

---

# 🧪 JSON prontos para teste

[LINK COM O ARQUIVO DE TESTES PRONTO NO INSOMINIA] [Arquivo no Google Drive](https://drive.google.com/file/d/10DaAXnqqCYoovcrRCRw-CEFwTOtmnRWP/view?usp=sharing)

## ✅ Lista de livros (cadastre 1 por vez em POST /livros)
```json
[
  {
    "titulo": "Clean Code",
    "autor": "Robert C. Martin",
    "isbn": "9780132350884",
    "anoPublicacao": 2008
  },
  {
    "titulo": "O Pequeno Príncipe",
    "autor": "Antoine de Saint-Exupéry",
    "isbn": "9780156013987",
    "anoPublicacao": 1943
  },
  {
    "titulo": "Dom Casmurro",
    "autor": "Machado de Assis",
    "isbn": "9788535902775",
    "anoPublicacao": 1899
  }
]
```

## ✅ Lista de empréstimos (cadastre 1 por vez em POST /emprestimos)
> Ajuste os `livroId` conforme os IDs do seu banco.

```json
[
  { "matriculaAluno": "202612345", "livroId": 1 },
  { "matriculaAluno": "202612346", "livroId": 2 },
  { "matriculaAluno": "202612347", "livroId": 3 }
]
```

---

# ✅ Roteiro rápido de testes (checklist)
1. `POST /livros` (cadastrar livros)
2. `GET /livros` (listar)
3. `GET /livros/{id}` (buscar existente e inexistente)
4. `POST /livros` com ISBN repetido → `409`
5. `POST /emprestimos` (emprestar um livro)
6. `GET /livros/{id}` → `disponivel: false`
7. Tentar emprestar o mesmo livro → `409`
8. `POST /emprestimos/{id}/devolucao`
9. `GET /livros/{id}` → `disponivel: true`

---

## Autor
Projeto desenvolvido para estudos de **Backend Web** (IFPB) usando **Java 17 + Spring Boot**.
