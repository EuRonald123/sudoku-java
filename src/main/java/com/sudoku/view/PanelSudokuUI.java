package com.sudoku.view;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import com.sudoku.model.Board;
import com.sudoku.model.Space;

public class PanelSudokuUI extends JPanel {
    private final Board board;
    private final JTextField[][] fields;
    private final Space[][] grid;

    public PanelSudokuUI(Board board){
        this.board = board;
        this.grid = board.getBoard();
        int size = grid.length;
        fields = new JTextField[size][size];

        setLayout(new GridLayout(size, size));

        for (int l = 0; l < size; l++) {
            for (int c = 0; c < size; c++) {
                JTextField field = new JTextField();
                field.setHorizontalAlignment(JTextField.CENTER);
                field.setFont(new Font("Arial", Font.BOLD, 20));

                int top = (l % 3 == 0) ? 3 : 1;
                int left = (c % 3 == 0) ? 3 : 1;
                int bottom = (l == 8) ? 3 : 1;
                int right = (c == 8) ? 3 : 1;
                field.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, new Color(60, 60, 60)));

                Space space = grid[l][c];

                if (space.getFixed()) {
                    field.setText(String.valueOf(space.getActual()));
                    field.setEditable(false);
                    field.setBackground(new Color(220, 220, 220));
                } else {
                    int line = l;
                    int col = c;
                    field.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            board.removeNumber(line, col);
                            field.setBackground(Color.WHITE);

                            String text = field.getText().trim();
                            if (text.matches("[1-9]")) {
                                int num = Integer.parseInt(text);
                                if (!board.addNumber(line, col, num)) {
                                    field.setText("");
                                }
                            } else if (!text.isEmpty()) {
                                field.setText("");
                            }
                        }
                    });
                }

                fields[l][c] = field;
                add(field);
            }
        }
    }

    public void resetFieldColors(){
        for (int l = 0; l < fields.length; l++) {
            for (int c = 0; c < fields.length; c++) {
                if (grid[l][c].getFixed()) {
                    fields[l][c].setBackground(new Color(220, 220, 220));
                } else {
                    fields[l][c].setBackground(Color.WHITE);
                }
            }
        }
    }

    public void highlightVerification(){
        for (int l = 0; l < fields.length; l++) {
            for (int c = 0; c < fields.length; c++) {
                Integer actual = grid[l][c].getActual();
                int expected = grid[l][c].getExpected();

                if (actual != null) {
                    if (actual.equals(expected)) {
                        fields[l][c].setBackground(new Color(200, 255, 200));
                    } else {
                        fields[l][c].setBackground(new Color(255, 200, 200));
                    }
                }
            }
        }

        Timer timer = new Timer(2000, e -> resetFieldColors());
        timer.setRepeats(false);
        timer.start();
    }

    public void clearBoard(){
        board.cleanBoard();
        for (int l = 0; l < fields.length; l++) {
            for (int c = 0; c < fields.length; c++) {
                if (!grid[l][c].getFixed()) {
                    fields[l][c].setText("");
                    fields[l][c].setBackground(Color.WHITE);
                }
            }
        }
    }
}
