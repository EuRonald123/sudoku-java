package com.sudoku;

import com.sudoku.control.GameController;
import com.sudoku.model.Board;
import com.sudoku.model.Space;
import com.sudoku.view.MenuTerminal;

public class App {

    public static void main(String[] args) {
        
        if (args.length != 81) {
            System.out.println("Error: The game needs exactly 81 arguments to start.");
            return; 
        }

        Space[][] grid = new Space[9][9];

        for (String arg : args) {
            
            String[] partes = arg.split(";");            
            String[] coordenadas = partes[0].split(","); 
            String[] dados = partes[1].split(",");       

            //converte
            int coluna = Integer.parseInt(coordenadas[0]); 
            int linha = Integer.parseInt(coordenadas[1]);  
            
            //gabarito
            int expectedValue = Integer.parseInt(dados[0]); 
            boolean fixo = Boolean.parseBoolean(dados[1]);

            grid[linha][coluna] = new Space(expectedValue, fixo);
        }

        Board board = new Board(grid);
        GameController controller = new GameController(board);
        MenuTerminal menu = new MenuTerminal(board, controller);

        menu.start();
    }
}