package com.sudoku.control;

import com.sudoku.model.Board;
import com.sudoku.model.Space;
import com.sudoku.util.GameStatus;

//classe responsável pelas regras e validações do jogo
public class GameController {
    private final Space[][] grid;

    public GameController(Board board){
        this.grid = board.getBoard();
    }

    //metodos publicos
    public GameStatus getGameStatus(){
        if(!started()) return GameStatus.NOT_STARTED;

        if(hasEmptySpace()) return GameStatus.INCOMPLETE;

        return GameStatus.COMPLETE;
    }

    public boolean hasError(){
        return hasConflict();
    }

    //metodos privados
    private boolean hasConflict(){
        int size = grid.length;
        Integer value;

        for(int line = 0; line < size; line++){
            for(int column = 0; column < size; column++){
                value = grid[line][column].getActual();
                
                    if(value != null){
                        boolean validLine = validateLine(grid, line, column, value);
                        boolean validColumn = validateColumn(grid, line, column, value);
                        boolean validQuadrant = validateQuadrant(grid, line, column, value);

                        if(!validLine || !validColumn || !validQuadrant){
                            return true;
                        }
                    }
            }
        }
        return false;
    }

    private boolean hasEmptySpace(){
        //Space[][] grid = board.getBoard();
        int size = grid.length;
        Space value;

        for (Space[] spaces : grid) {
            for (int j = 0; j < size; j++) {
                value = spaces[j];

                if (value.getActual() == null) return true;
            }
        }
        return false;
    }

    private boolean started(){
        //Space[][] grid = board.getBoard();
        int size = grid.length;
        Space value;

        for (Space[] spaces : grid) {
            for (int j = 0; j < size; j++) {
                value = spaces[j];

                if (value.getActual() != null && !value.getFixed()) return true;
            }
        }
        return false;
    }

    //metodos privados auxiliares
    private boolean validateLine(Space[][] grid, int line, int currentColumn, int value){
        //Space[][] grid = board.getBoard();     //pega a matriz de espacos e guarda em grid
        int size = grid.length;
        for(int x = 0; x < size; x++){
            if(x == currentColumn) continue;

            Integer currentValue = grid[line][x].getActual(); //armazena o valor atual ena variavel currentValue para fazer a verificacao
            if(currentValue != null && currentValue.equals(value)){
                return false;
            }
        }
        return true;
    }

    private boolean validateColumn(Space[][] grid, int currentLine, int column, int value){
        //Space[][] grid = board.getBoard();
        int size = grid.length;
        for(int x = 0; x < size; x++){
            if(x == currentLine) continue;

            Integer currentValue = grid[x][column].getActual(); //armazena o valor atual ena variavel currentValue para fazer a verificacao
            if(currentValue != null && currentValue.equals(value)){
                return false;
            }
        }
        return true;
    }

    private boolean validateQuadrant(Space[][] grid, int line, int column, int value){
        int subSize = (int) Math.sqrt(grid.length);
        int startLine = (line / subSize) * subSize;
        int startColumn = (column / subSize) * subSize;

        for(int i = startLine; i < startLine + subSize; i++){
            for(int j = startColumn; j < startColumn + subSize; j++){
                if(i == line && j == column) continue;

                Integer currentValue = grid[i][j].getActual();
                if(currentValue != null && currentValue.equals(value)){
                    return false;
                }
            }
        }
        return true;
    }




}
