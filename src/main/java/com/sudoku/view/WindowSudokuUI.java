package com.sudoku.view;

import java.awt.*;

import javax.swing.*;

import com.sudoku.control.GameController;
import com.sudoku.model.Board;
import com.sudoku.util.GameStatus;

public class WindowSudokuUI extends JFrame {
    private final GameController controller;
    private final PanelSudokuUI panel;

    public WindowSudokuUI(int w, int h, Board board, GameController controller){
        this.controller = controller;
        this.panel = new PanelSudokuUI(board);

        setTitle("R_Sudoku");

        setSize(w, h);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        setLayout(new BorderLayout());

        add(createButtonPanel(), BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createButtonPanel(){
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnStatus = new JButton("Status");
        JButton btnVerify = new JButton("Verify");
        JButton btnClear = new JButton("Clear");
        JButton btnFinish = new JButton("Finish");

        btnStatus.addActionListener(e -> showStatus());
        btnVerify.addActionListener(e -> panel.highlightVerification());
        btnClear.addActionListener(e -> panel.clearBoard());
        btnFinish.addActionListener(e -> tryFinish());

        buttonPanel.add(btnStatus);
        buttonPanel.add(btnVerify);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnFinish);

        return buttonPanel;
    }

    private void showStatus(){
        GameStatus status = controller.getGameStatus();
        String msg = "Status: " + status;
        if (controller.hasError()) {
            msg += "\nThe game contains errors!";
        } else {
            msg += "\nThe game contains no errors.";
        }
        JOptionPane.showMessageDialog(this, msg, "Game Status", JOptionPane.INFORMATION_MESSAGE);
    }

    private void tryFinish(){
        if (controller.getGameStatus() == GameStatus.COMPLETE && !controller.hasError()) {
            JOptionPane.showMessageDialog(this,
                "Congratulations! You completed the board!",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "The game is not over yet. Fill all spaces correctly.",
                "Game Over",
                JOptionPane.WARNING_MESSAGE);
        }
    }
}
