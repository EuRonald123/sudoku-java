package com.sudoku.control;

import com.sudoku.model.Board;
import com.sudoku.model.Space;
import com.sudoku.util.GameStatus;

//classe responsável pelas regras e validações do jogo
public class GameController {
    private final Board board;

    public GameController(Board board){
        this.board = board;
    }

    //metodos publicos
    public GameStatus getGameStatus(){
        if(!started()) return GameStatus.NOT_STARTED;

        //if(hasConflict()) return GameStatus.ERROR;

        if(hasEmptySpace()) return GameStatus.INCOMPLETE;

        return GameStatus.COMPLETE;
    }

    public boolean hasError(){
        return hasConflict();
    }

    //metodos privados
    private boolean hasConflict(){
        Space[][] gride = board.getBoard();
        int size = gride.length;
        Integer value;

        for(int line = 0; line < size; line++){
            for(int column = 0; column < size; column++){
                value = gride[line][column].getActual();
                
                    if(value != null){
                        boolean validLine = validateLine(line, column, value);
                        boolean validColumn = validateColumn(line, column, value);
                        boolean validQuadrant = validateQuadrant(line, column, value);

                        if(!validLine || !validColumn || !validQuadrant){
                            return true;
                        }
                    }
            }
        }
        return false;
    }

    private boolean hasEmptySpace(){
        Space[][] gride = board.getBoard();
        int size = gride.length;
        Space value;

        for(int i=0; i< size; i++){
            for(int j=0; j<size; j++){
                value = gride[i][j];

                if(value.getActual() == null) return true;
            }
        }
        return false;
    }

    private boolean started(){
        Space[][] gride = board.getBoard();
        int size = gride.length;
        Space value;

        for(int i=0; i< size; i++){
            for(int j=0; j<size; j++){
                value = gride[i][j];

                if(value.getActual() !=null && !value.getFixed()) return true;
            }
        }
        return false;
    }

    //metodos privados auxiliares
    private boolean validateLine(int line, int currentColumn, int value){
        Space[][] gride = board.getBoard();     //pega a matriz de espacos e guarda em gride
        int size = gride.length;
        for(int x = 0; x < size; x++){
            if(x == currentColumn) continue;

            Integer currentValue = gride[line][x].getActual(); //armazena o valor atual ena variavel currentValue para fazer a verificacao
            if(currentValue != null && currentValue.equals(value)){
                return false;
            }
        }
        return true;
    }

    private boolean validateColumn(int currentLine, int column, int value){
        Space[][] gride = board.getBoard();     //pega a matriz de espacos e guarda em gride
        int size = gride.length;
        for(int x = 0; x < size; x++){
            if(x == currentLine) continue;

            Integer currentValue = gride[x][column].getActual(); //armazena o valor atual ena variavel currentValue para fazer a verificacao
            if(currentValue != null && currentValue.equals(value)){
                return false;
            }
        }
        return true;
    }

    private boolean validateQuadrant(int line, int column, int value){
        Space[][] gride = board.getBoard();

        //aqui, caso eu queira um tabuleiro de tamanho diferente o codigo quebra -> preciso resolver para torna-lo escalável
        int startLine = (line/3) * 3;
        int starColumn = (column/3) * 3;

        for(int i = startLine; i < startLine + 3; i++){
            for(int j = starColumn; j < starColumn + 3; j++){
                if(i == line && j == column) continue;

                Integer currentValue = gride[i][j].getActual();
                if(currentValue != null && currentValue.equals(value)){
                    return false;
                }
            }
        }
        return true;
    }




}
