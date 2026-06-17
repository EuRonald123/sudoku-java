package com.sudoku.view;

import javax.swing.*;
import java.awt.*;

public class PanelSudokuUI extends JPanel {
    public PanelSudokuUI(int x){
        //cria uma grade quadrada de X*X
        setLayout(new GridLayout(x, x));

        for (int i = 0; i < (x*x); i++){
            JTextField field = new JTextField();

            //alinhamento no centro
            field.setHorizontalAlignment(JTextField.CENTER);

            field.setFont(new Font("Arial",Font.BOLD, 20));

            add(field);
        }
    }
}
