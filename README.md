## 📦 Sistema de Gerenciamento de Estoque (Projeto U3)
Este projeto é uma aplicação em Java desenvolvida para a avaliação da Unidade 3. Trata-se de um sistema de gerenciamento de inventário que integra estruturas de dados avançadas, algoritmos de ordenação e conceitos modernos de Orientação a Objetos.
O sistema permite o cadastro, busca, remoção e análise de produtos variados (Eletrônicos, Livros e Vestuário), utilizando uma Árvore Binária de Busca (ABB) para indexação e MergeSort para relatórios ordenados.

## 🚀 Funcionalidades Principais
### 1. Gerenciamento de Produtos (CRUD)

- Adicionar: Cadastro de produtos com validações de regra de negócio (ID único, preço não negativo, nome obrigatório).
- Buscar por ID: Recuperação imediata de um produto específico (Complexidade O(1) via HashMap).
- Buscar por Nome: Busca textual que encontra produtos contendo o termo pesquisado.
- Remover: Exclusão de itens do repositório.

### 2. Relatórios e Estruturas de Dados

- Relatório em Árvore: Exibe os produtos ordenados naturalmente pelo ID utilizando o caminhamento In-Order da Árvore Binária.
- Relatório Ordenado: Permite listar o estoque ordenado por Preço (Crescente) ou Nome (A-Z) utilizando o algoritmo MergeSort.
- Dashboard Gerencial: Exibe estatísticas em tempo real:

Total de itens.
Valor total do patrimônio em estoque.
Média de preços.
Produto mais caro e mais barato.

### 3. Diferenciais Técnicos

- Carga Inicial de Dados: O sistema inicia com 8 produtos pré-carregados para facilitar os testes.
- Menu Interativo: Interface via console robusta e tolerante a falhas de digitação.


## 🛠️ Tecnologias e Conceitos Aplicados
O projeto cumpre todos os requisitos técnicos solicitados:
### Estrutura de Dados

- HashMap: Armazenamento principal para acesso rápido.
- Árvore Binária de Busca (ABB): Estrutura auxiliar para manter índices de IDs.

### Algoritmos

- MergeSort: Implementação manual para ordenação customizada (Comparator).

### POO Avançada

- Polimorfismo: Classes Eletronico, Livro e Vestuario herdam de Produto.
- Generics: Repositório (RepositorioHash<T>) e Árvore (Arvore<T>) genéricos.
- Interfaces: Contratos definidos para Arvore e Ordenador.

### Java Moderno

- Reflection: Leitura da anotação personalizada @InfoAutor em tempo de execução.
- Exception Handling: Hierarquia de exceções próprias (NegocioException, ProdutoJaCadastradoException, etc.).

## ▶️ Como Compilar e Rodar
## Pré-requisitos
+
Java JDK 8 ou superior instalado. 
### Opção 1: Via IntelliJ IDEA (Recomendado)

- Abra a pasta do projeto no IntelliJ.
Navegue até src/br/com/projeto/app/Main.java.
Clique no ícone de Play verde ao lado da classe Main.

### Opção 2: Via Terminal (Linha de Comando)

- Abra o terminal na raiz da pasta src. Compile todos os arquivos.
- Comando: ```javac br/com/projeto/app/Main.java```
- Nota: O compilador Java irá compilar automaticamente as dependências.
- Depois Rode o programa com ```java br.com.projeto.app.Main ```

