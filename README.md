# Sudoku - Java

Jogo de Sudoku clássico com interface gráfica (Swing) e terminal, desenvolvido em Java seguindo o padrão MVC.

## Recursos

- Tabuleiro 9x9 com divisórias visuais dos quadrantes 3x3
- Três níveis de dificuldade: Fácil, Médio e Difícil
- Interface gráfica Swing com botões interativos
- Interface alternativa via terminal
- Validação de conflitos em linhas, colunas e quadrantes
- Destaque visual de acertos (verde) e erros (vermelho) com timer de 2s
- Células fixas (não editáveis) destacadas em cinza
- Limpeza rápida do tabuleiro

## Estrutura do Projeto (MVC)

```
com.sudoku
├── App.java                     # Ponto de entrada
├── model/
│   ├── Board.java               # Tabuleiro 9x9 (matriz de Space)
│   └── Space.java               # Célula individual (valor atual, esperado, fixo)
├── control/
│   └── GameController.java      # Lógica de validação e estado do jogo
├── util/
│   ├── BoardPreset.java         # Enum com 3 tabuleiros pré-configurados
│   └── GameStatus.java          # Enum de estados (NOT_STARTED, INCOMPLETE, COMPLETE)
└── view/
    ├── PanelSudokuUI.java       # Grade do tabuleiro (Swing JPanel)
    ├── WindowSudokuUI.java      # Janela principal com botões
    └── MenuTerminal.java        # Interface via terminal
```

## Imagem do Tabuleiro nível Fácil

<img width="500" height="500" alt="image" src="https://github.com/user-attachments/assets/e413478f-151b-4689-805d-63a3dbb080ab" />


## Como Executar

### Interface Gráfica (Swing)

```bash
mvn package
java -cp target/projeto-sudoku-1.0.jar com.sudoku.App
```

Dificuldades disponiveis: `easy`, `medium`, `hard`.

Sem argumento, abre um dialogo para escolher a dificuldade.

## Terminal

Para a versão de terminal, basta configurar a classe App.java para chamar a classe MenuTerminal.java e passar os 
argumentos necessários

- 1: New Game
- 2: Add number
- 3: Remove number
- 4: View board
- 5: Check status
- 6: Clean board
- 7: Finish game
- 8: Exit

## Dependencias

Apenas JDK 21+ e Maven. Nenhuma biblioteca externa.
