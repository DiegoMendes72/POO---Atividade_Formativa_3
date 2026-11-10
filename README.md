# POO---Atividade_Formativa_3
Desenvolver um pequeno Sistema de Gerenciamento de Biblioteca em java


# 📚 Sistema de Gerenciamento de Biblioteca

Projeto Java desenvolvido como atividade prática envolvendo **interfaces,
pacotes, tratamento de exceções e coleções com Generics**.

## 🗂️ Estrutura do projeto

    src/
    ├── app/          → classe Main (menu interativo)
    ├── exception/    → exceções personalizadas
    ├── interfaces/   → interface Emprestavel
    ├── model/        → classe Livro
    └── service/      → regras de negócio (BibliotecaService)

## ✅ Pré-requisitos

- **JDK 17** ou superior instalado (`java -version` para conferir)

## ▶️ Como compilar

Na pasta raiz do projeto (onde está a pasta `src`):

**Linux / macOS:**
    javac -d out $(find src -name "*.java")

**Windows (CMD):**
    dir /s /B src\*.java > fontes.txt
    javac -d out @fontes.txt

**Windows (PowerShell):**
    javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })

## ▶️ Como executar

    java -cp out app.Main

## 🎮 Funcionalidades do menu

1. Cadastrar livro
2. Listar livros
3. Buscar livro por código
4. Emprestar livro
5. Devolver livro
6. Carregar 3 livros de exemplo
7. **Executar testes obrigatórios** (demonstra todos os cenários)
0. Sair

## 🧪 Testes obrigatórios

Selecione a opção **7** no menu para executar automaticamente:

- Cadastro de 3 livros
- Listagem dos livros
- Busca por código existente
- Empréstimo de um livro
- Tentativa de empréstimo duplicado (exceção tratada)
- Devolução de um livro
- Busca por código inexistente (exceção tratada)
