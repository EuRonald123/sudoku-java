package com.sudoku;

import com.sudoku.control.GameController;
import com.sudoku.model.Board;
import com.sudoku.util.BoardPreset;
import com.sudoku.view.WindowSudokuUI;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        BoardPreset preset;

        if (args.length > 0) {
            preset = BoardPreset.valueOf(args[0].toUpperCase());
        } else {
            preset = (BoardPreset) JOptionPane.showInputDialog(
                null,
                "Choose a difficulty:",
                "Sudoku",
                JOptionPane.QUESTION_MESSAGE,
                null,
                BoardPreset.values(),
                BoardPreset.EASY
            );
            if (preset == null) return;
        }

        Board board = preset.createBoard();
        GameController controller = new GameController(board);
        SwingUtilities.invokeLater(() -> new WindowSudokuUI(800, 800, board, controller));
    }
}
