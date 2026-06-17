package com.sudoku.view;

import javax.swing.*;

public class WindowSudokuUI extends JFrame {

    public WindowSudokuUI(int w, int h){
        setTitle("R_Sudoku");

        setSize(w, h);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        //Panel
        add(new PanelSudokuUI(9));

        setVisible(true);
    }
}
