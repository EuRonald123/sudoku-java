package com.sudoku.view;

import javax.swing.*;

public class sudokuUI {
    //classe usada para gerenciar a interface gráfica do meu jogo
    public static void main (String[] args){
        SwingUtilities.invokeLater(() -> new WindowSudokuUI(800,800));
    }
}
