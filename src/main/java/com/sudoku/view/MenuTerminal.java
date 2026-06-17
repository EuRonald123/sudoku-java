package com.sudoku.view;

import java.util.Scanner;

import com.sudoku.control.GameController;
import com.sudoku.model.Board;
import com.sudoku.model.Space;

public class MenuTerminal {
    private final Board board;
    private final GameController controller;
    private final Scanner scanner;
    

    public MenuTerminal(Board board, GameController controller){
        this.board = board;
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        int option = 0;

        System.out.println("\n*** Game sudoku ***");

        while (option != 8) {
            viewOptions();
            option = readNumber("\nChoose an option:", 1, 8);
            if(option == 7 && tryToFinish()){
                break;
            }
            processOption(option);
        }
    }

    private void viewOptions(){
        System.out.println("\n1: New Game");
        System.out.println("\n2: Add a new number");
        System.out.println("\n3: Remove a number");
        System.out.println("\n4: View board");
        System.out.println("\n5: Check game status");
        System.out.println("\n6: Clean Board");
        System.out.println("\n7: Finish the game");
        System.out.println("\n8: Exit");
    }

    private void processOption(int option){
        switch (option) {
            case 1:
                board.cleanBoard();
                System.out.println("\nGame started");
                break;
            case 2:
                addNewNumber();
                break;
            case 3:
                removeNumber();
                break;
            case 4:
                viewBoard();
                break;
            case 5:
                checkStatus();
                break;
            case 6:
                board.cleanBoard();
                System.out.println("\nThe board has been cleaned");
                break;
            default:
                break;
        }
    }

    private void addNewNumber(){
        int number;
        int line;
        int column;

        number = readNumber("\nEnter the number to be entered", 1, 9);
        line = readNumber("\nEnter the line (0 to 8)", 0, 8);
        column = readNumber("\nEnter column (0 to 8)", 0, 8);

        boolean success = board.addNumber(line, column, number);
        if (success) {
            System.out.println("\nNumber added successfully!");
        } else {
            System.out.println("\nError: Position already filled or is a fixed number.");
        }
    }

    private void removeNumber(){
        int line;
        int column;
        
        line = readNumber("\nEnter the line (0 to 8)", 0, 8);
        column = readNumber("\nEnter column line (0 to 8)", 0, 8);

        boolean success = board.removeNumber(line, column);
        if(success){
            System.out.println("\nNumber successfully removed.");
        }
        else{
            System.out.println("\nError during removal! Fixed numbers cannot be removed.");
        }
    }

    private void viewBoard(){
        Space[][] grid = board.getBoard();
        
        System.out.println("\n  0 1 2   3 4 5   6 7 8");
        System.out.println("-------------------------");
        
        for (int l = 0; l < 9; l++) {
            System.out.print(l + "|");
            
            for (int c = 0; c < 9; c++) {
                Integer value = grid[l][c].getActual();
                
                if (value == null) {
                    System.out.print(" .");
                } else {
                    System.out.print(" " + value);
                }
                
                if ((c + 1) % 3 == 0) {
                    System.out.print(" |");
                }
            }
            
            System.out.println();
            
            
            if ((l + 1) % 3 == 0) {
                System.out.println("-------------------------");
            }
        }
    }

    void checkStatus(){
        System.out.println("\nGame Status:" + controller.getGameStatus());

        if(controller.hasError()){
            System.out.println("\nThe game contains errors!");
        }
        else{
            System.out.println("\nThe game no contains errors.");
        }
    }

    private boolean tryToFinish(){
        if(controller.getGameStatus().toString().equals("COMPLETE") && !controller.hasError()){
            System.out.println("\nCongratulations! You finished the board");
            return true;
        }
        System.out.println("\nThe game isn't over yet, you must fill in all the spaces correctly");
        return false;
    }

    private int readNumber(String message, int min, int max) {
        int input;
        while (true) {
            System.out.println(message);
            
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                
                if (input >= min && input <= max) {
                    return input;
                }
                
                System.out.println("Invalid number! Please enter a value between " + min + " and " + max + ".");
            } else {
                // se nao for numero:
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.next();
            }
        }
    }
}
