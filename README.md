# 🧩 SudoKU - Jogo Clássico para Terminal em Java

Bem-vindo ao **Sudoku**! 👋 

Este é um jogo de Sudoku totalmente interativo jogado diretamente no terminal (linha de comando). O projeto foi desenvolvido em **Java**, aplicando conceitos sólidos de **Orientação a Objetos**.


---

## 🎮 Como o jogo funciona?

O jogo roda através de um menu interativo no terminal. Nele, você pode:

1. **Iniciar um Novo Jogo:** Limpa todas as suas jogadas e recomeça o tabuleiro.
2. **Adicionar Número:** Escolha as coordenadas (linha e coluna de 0 a 8) e insira um número de 1 a 9.
3. **Remover Número:** Você pode remover suas jogadas, mas o sistema protege os "números fixos" (o gabarito inicial do jogo) para que você não os delete acidentalmente.
4. **Visualizar o Tabuleiro:** Imprime o tabuleiro formatado na tela com divisórias 3x3.
5. **Verificar Status:** O jogo te avisa se está INCOMPLETO, COMPLETO, NÃO INICIADO e se possui ERROS.
6. **Limpar Tabuleiro:** Remove todas as suas jogadas de uma só vez.
7. **Finalizar o Jogo:** Tenta validar a sua vitória. O jogo só encerra se todo o tabuleiro estiver preenchido corretamente.
8. **Sair:** Encerra a aplicação com segurança.

---

## 🏗️ Arquitetura do Projeto (MVC)

O código foi em pacotes para separar responsabilidades:

* 🧠 **Model (`com.sudoku.model`):** Contém a estrutura de dados. O `Space` representa cada quadradinho (com regras de números fixos e gabarito) e o `Board` gerencia a matriz 9x9.
* ⚙️ **Control (`com.sudoku.control`):** O "cérebro" do jogo. O `GameController` possui algoritmos matemáticos que varrem linhas, colunas e os quadrantes 3x3 para evitar conflitos e repetições.
* 🖥️ **View (`com.sudoku.view`):** Onde a mágica acontece para o usuário. O `MenuTerminal` possui um loop robusto que blinda o sistema contra "dedos escorregadios" (se o usuário digitar uma letra em vez de número, o jogo não quebra!).
* 🚀 **App (`com.sudoku.App`):** O ponto de partida. Ele lê a configuração do tabuleiro inicial e injeta as dependências para o jogo começar.

---

## 🚀 Como Executar na Sua Máquina

Para rodar o jogo, é necessário compilar as classes e executar a classe principal `App.java`. 

**Atenção:** A classe `App` foi programada para receber exatamente **81 argumentos** no momento da execução. Esses argumentos montam o tabuleiro inicial!

O formato de cada argumento segue o padrão: `coluna,linha;valorEsperado,éFixo` (Exemplo: `0,0;4,false`).

### Rodando com o Maven (Pelo Terminal)
Se você estiver usando o Maven, pode rodar o comando abaixo na raiz do projeto, passando a string com os 81 argumentos:

```bash
mvn compile exec:java -Dexec.mainClass="com.sudoku.App" -Dexec.args="0,0;4,false 1,0;7,false 2,0;9,true 3,0;5,false 4,0;8,true 5,0;6,true 6,0;2,true 7,0;3,false 8,0;1,false 0,1;1,false 1,1;3,true 2,1;5,false 3,1;4,false 4,1;7,true 5,1;2,false 6,1;8,false 7,1;9,true 8,1;6,true 0,2;2,false 1,2;6,true 2,2;8,false 3,2;9,false 4,2;1,true 5,2;3,false 6,2;7,false 7,2;4,false 8,2;5,true 0,3;5,true 1,3;1,false 2,3;3,true 3,3;7,false 4,3;6,false 5,3;4,false 6,3;9,false 7,3;8,true 8,3;2,false 0,4;8,false 1,4;9,true 2,4;7,false 3,4;1,true 4,4;2,true 5,4;5,true 6,4;3,false 7,4;6,true 8,4;4,false 0,5;6,false 1,5;4,true 2,5;2,false 3,5;3,false 4,5;9,false 5,5;8,false 6,5;1,true 7,5;5,false 8,5;7,true 0,6;7,true 1,6;5,false 2,6;4,false 3,6;2,false 4,6;3,true 5,6;9,false 6,6;6,false 7,6;1,true 8,6;8,false 0,7;9,true 1,7;8,true 2,7;1,false 3,7;6,false 4,7;4,true 5,7;7,false 6,7;5,false 7,7;2,true 8,7;3,false 0,8;3,false 1,8;2,false 2,8;6,true 3,8;8,true 4,8;5,true 5,8;1,false 6,8;4,true 7,8;7,false 8,8;9,false"