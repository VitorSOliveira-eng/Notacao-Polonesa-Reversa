# Avaliador de Expressões Matemáticas (Notação Polonesa Reversa)

Trabalho 2 da disciplina Lab. AED I (Algoritmos e Estruturas de Dados I), Engenharia de Computação, CEFET-MG Campus Timóteo, Prof. Luciano Moreira.

O programa lê uma expressão matemática na forma infixa, valida a sintaxe, converte a expressão para a forma pós-fixa (notação polonesa reversa) e calcula o valor numérico. Todas as estruturas de dados são implementadas no próprio projeto; não são usadas classes prontas do Java como `Stack`, `ArrayList` ou `LinkedList`.

## Funcionalidades

- Operadores binários: `+`, `-`, `*`, `/` e `^` (exponenciação, associativa à direita).
- Parênteses para alterar a precedência.
- Operandos de um único algarismo (0 a 9).
- Validação sintática antes de qualquer conversão ou cálculo.
- Repetição do processo enquanto o usuário quiser.

## Estrutura do projeto

```
Main/
├── Main.java               Ponto de entrada; orquestra leitura, conversão e avaliação
├── LeitorExpressao.java    Lê a expressão do teclado e valida a sintaxe
├── ConversorPosFixa.java   Converte a expressão infixa para pós-fixa
├── AvaliadorPosFixa.java   Calcula o valor de uma expressão pós-fixa
└── TadPilha.java           TAD Pilha genérica (usada pelos demais módulos)
```

## Exemplo de uso

```
=== AVALIADOR DE EXPRESSÕES MATEMÁTICAS ===

-----------------------------------------
Digite a expressão matemática (sem espaços): 2+5*8/(4+3)

[Sucesso] Expressão Infixa Válida: 2+5*8/(4+3)
Expressão Pós-fixa (NPI):       258*43+/+
Valor Numérico Avaliado:       7.71

Deseja testar outra expressão? (s/n): n
-----PROGRAMA ENCERRADO------
```

Outros exemplos:

| Infixa    | Pós-fixa | Valor |
| --------- | -------- | ----- |
| `3^2`     | `32^`    | 9.00  |
| `(3+4)*2` | `34+2*`  | 14.00 |
| `8/3`     | `83/`    | 2.67  |

## Como funciona

### 1. `LeitorExpressao`

Lê uma linha do teclado e só devolve a expressão quando ela passa por todas as validações, na ordem:

1. Não está vazia.
2. Contém apenas dígitos, operadores e parênteses.
3. Os parênteses estão balanceados. Usa a `TadPilha` para isso: cada `(` é empilhado e cada `)` desempilha um `(`. Se faltar `(` ao encontrar um `)`, ou sobrar `(` no final, a expressão é inválida.
4. A posição dos símbolos é válida. Não começa nem termina com operador, não há operador seguido de operador ou de `)`, não há `(` seguido de operador ou de `)`, não há `)` seguido de dígito ou `(`, e não há operando seguido de `(` ou de outro operando.

Se alguma validação falhar, a mensagem de erro é exibida e a expressão é solicitada novamente.

### 2. `ConversorPosFixa`

Converte infixa para pós-fixa pelo algoritmo _Shunting-yard_ (Dijkstra). Usa uma `TadPilha<Character>` para guardar operadores e parênteses pendentes. A saída é construída em uma `String`.

A ideia central: operandos vão direto para a saída, e operadores esperam na pilha até que possam ser escritos na ordem correta. Os parênteses fazem o papel de "barreira" que impede operadores de dentro deles de saírem antes do que está fora.

Precedência usada por `obterPrecedencia`:

| Operador | Precedência |
| -------- | ----------- |
| `^`      | 3           |
| `*`, `/` | 2           |
| `+`, `-` | 1           |
| `(`, `)` | 0           |

As regras aplicadas a cada caractere:

- **Dígito:** vai para a saída.
- **`(`:** empilha.
- **`)`:** desempilha e envia para a saída todos os operadores até encontrar o `(`. Depois descarta o `(`.
- **Operador:** enquanto o topo da pilha não for `(` e tiver precedência maior, ou igual com o operador atual não sendo `^`, desempilha o topo para a saída. Depois empilha o operador atual. A condição de igualdade faz operadores iguais associarem da esquerda para a direita, e `^` associar da direita para a esquerda.
- **Fim da expressão:** desempilha tudo que sobrou para a saída.

Exemplo com `2+5*8/(4+3)`:

| Caractere | Ação                                                           | Pilha (topo à direita) | Saída       |
| --------- | -------------------------------------------------------------- | ---------------------- | ----------- |
| `2`       | vai para a saída                                               |                        | `2`         |
| `+`       | pilha vazia, empilha                                           | `+`                    | `2`         |
| `5`       | vai para a saída                                               | `+`                    | `25`        |
| `*`       | `*` tem precedência maior que `+`, empilha                     | `+ *`                  | `25`        |
| `8`       | vai para a saída                                               | `+ *`                  | `258`       |
| `/`       | `*` tem a mesma precedência, desempilha `*`; `+` é menor, para | `+ /`                  | `258*`      |
| `(`       | empilha                                                        | `+ / (`                | `258*`      |
| `4`       | vai para a saída                                               | `+ / (`                | `258*4`     |
| `+`       | topo é `(`, empilha                                            | `+ / ( +`              | `258*4`     |
| `3`       | vai para a saída                                               | `+ / ( +`              | `258*43`    |
| `)`       | desempilha `+`, descarta `(`                                   | `+ /`                  | `258*43+`   |
| fim       | desempilha `/` e `+`                                           |                        | `258*43+/+` |

### 3. `AvaliadorPosFixa`

Percorre a pós-fixa da esquerda para a direita, com uma `TadPilha<Double>`. Dígitos são convertidos para `double` e empilhados. Ao encontrar um operador, desempilha dois valores (o segundo desempilhado é o da esquerda da operação), calcula e empilha o resultado. O último valor restante na pilha é o resultado.

### 4. `TadPilha<T>`

Pilha genérica com capacidade fixa de 100 elementos, implementada sobre um `Object[]`. Oferece `push`, `pop`, `peek`, `estaVazio` e `estaCheio`. `push` em pilha cheia e `pop`/`peek` em pilha vazia lançam `IllegalStateException`. Por ser genérica, a mesma classe serve para parênteses, operadores (`Character`) e valores (`Double`).

## Limitações

- Operandos de um único algarismo, como exige o enunciado. Números com mais de um dígito não são suportados.
- Cada pilha suporta no máximo 100 elementos ao mesmo tempo. Expressões com muitos parênteses aninhados ou muitos operadores pendentes podem ultrapassar esse limite.
- A divisão usa `double`, então `8/3` resulta em `2.67`, e não em uma divisão inteira.
