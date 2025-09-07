package com.example.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import keongotcutie.Input;
import keongotcutie.Printer;
import keongotcutie.Validator;
import bonk.SudokuSolver; // Sửa import

public class SudokuUI extends JFrame {

    private final int[][] initialBoard;
    private final JTextField[][] cells = new JTextField[9][9];
    private final SudokuSolver solver;
    private final Validator validator;
    private final Printer printer = new Printer();

    public SudokuUI(int[][] initialBoard) {
        this.initialBoard = initialBoard;
        this.validator = new Validator();
        this.solver = new SudokuSolver(this.initialBoard, this.validator);

        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 3, 5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tạo 9 bảng con cho các khối 3x3
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                JPanel subGridPanel = new JPanel();
                subGridPanel.setLayout(new GridLayout(3, 3, 1, 1));
                subGridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                mainPanel.add(subGridPanel);

                // Tạo các ô nhập liệu cho từng ô
                for (int cellRow = 0; cellRow < 3; cellRow++) {
                    for (int cellCol = 0; cellCol < 3; cellCol++) {
                        int row = blockRow * 3 + cellRow;
                        int col = blockCol * 3 + cellCol;

                        JTextField cell = new JTextField();
                        cell.setHorizontalAlignment(JTextField.CENTER);
                        cell.setFont(new Font("Arial", Font.BOLD, 20));
                        cells[row][col] = cell;

                        // Đặt giá trị ban đầu và khóa các ô đã có
                        if (initialBoard[row][col] != 0) {
                            cell.setText(String.valueOf(initialBoard[row][col]));
                            cell.setEditable(false);
                            cell.setBackground(new Color(240, 240, 240));
                        }
                        subGridPanel.add(cell);
                    }
                }
            }
        }

        add(mainPanel, BorderLayout.CENTER);

        // Bảng điều khiển
        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton solveButton = new JButton("Giải");
        JButton clearButton = new JButton("Xóa");

        controlPanel.add(solveButton);
        controlPanel.add(clearButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Thêm các sự kiện cho nút
        solveButton.addActionListener(e -> solveSudoku());
        clearButton.addActionListener(e -> resetBoard());

        pack();
        setLocationRelativeTo(null);
    }

    private void solveSudoku() {
        if (solver.solve()) {
            JOptionPane.showMessageDialog(this, "Sudoku đã được giải thành công!");
            updateUIBoard();
        } else {
            JOptionPane.showMessageDialog(this, "Không thể giải Sudoku này.");
        }
    }

    private void resetBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (initialBoard[row][col] == 0) {
                    cells[row][col].setText("");
                }
            }
        }
    }

    private void updateUIBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col].setText(String.valueOf(initialBoard[row][col]));
            }
        }
    }
}
