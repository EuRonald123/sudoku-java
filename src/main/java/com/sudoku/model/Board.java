package com.sudoku.model;


public class Board {
    
    private final Space[][] board;

    public Board(Space[][] board){     // recebe uma matriz com todos os espaços e suas regras, e preenche o nosso tabuleiro de spaces.
        this.board = board;           
    }

    public Space[][] getBoard(){
        return board;
    }

    public boolean addNumber(int lin, int col, int number){
        if(board[lin][col].getFixed() || board[lin][col].getActual() != null){
            return false;
        }
        board[lin][col].setActual(number);
        return true;
    }

    public boolean removeNumber(int lin, int col){
        if(board[lin][col].getFixed()){
            return false;
        }
        board[lin][col].clearSpace();
        return true;
    }

    public void cleanBoard(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j].getFixed()){
                    continue;
                }
                removeNumber(i, j);
            }
        }
    }


}   
