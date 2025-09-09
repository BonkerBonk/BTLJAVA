package com.example.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import keongotcutie.Input;
import keongotcutie.Printer;
import keongotcutie.Validator;
import bonk.SudokuSolver;
import bonk.SudokuGenerator;

public class SudokuUI extends JFrame {

    private int[][] initialBoard;
    private int[][] savedBoard; // Thêm một mảng để lưu trữ trạng thái ban đầu
    private final JTextField[][] cells = new JTextField[9][9];
    private final SudokuSolver solver;
    private final Validator validator;
    private final Printer printer = new Printer();
    private final SudokuGenerator generator;

    public SudokuUI(int[][] initialBoard) {
        this.initialBoard = initialBoard;
        this.savedBoard = new int[9][9]; // Khởi tạo mảng savedBoard
        // Sao chép bảng ban đầu vào savedBoard
        for (int i = 0; i < 9; i++) {
            System.arraycopy(initialBoard[i], 0, savedBoard[i], 0, 9);
        }

        this.validator = new Validator();
        this.solver = new SudokuSolver(this.initialBoard, this.validator);
        this.generator = new SudokuGenerator();

        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 3, 5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                JPanel subGridPanel = new JPanel();
                subGridPanel.setLayout(new GridLayout(3, 3, 1, 1));
                subGridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                mainPanel.add(subGridPanel);

                for (int cellRow = 0; cellRow < 3; cellRow++) {
                    for (int cellCol = 0; cellCol < 3; cellCol++) {
                        int row = blockRow * 3 + cellRow;
                        int col = blockCol * 3 + cellCol;

                        JTextField cell = new JTextField();
                        cell.setHorizontalAlignment(JTextField.CENTER);
                        cell.setFont(new Font("Arial", Font.BOLD, 20));
                        cells[row][col] = cell;

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

        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton solveButton = new JButton("Giải");
        JButton clearButton = new JButton("Xóa");
        JButton newButton = new JButton("Làm mới");

        controlPanel.add(solveButton);
        controlPanel.add(clearButton);
        controlPanel.add(newButton);
        add(controlPanel, BorderLayout.SOUTH);

        solveButton.addActionListener(e -> solveSudoku());
        clearButton.addActionListener(e -> clearBoard());
        newButton.addActionListener(e -> newBoard());

        pack();
        setLocationRelativeTo(null);
    }

    private void solveSudoku() {
        // Tạo một bản sao của bảng hiện tại để so sánh sau khi giải
        int[][] userBoard = new int[9][9];
        boolean isUserInputValid = true;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = cells[row][col].getText();
                if (!text.isEmpty()) {
                    try {
                        int value = Integer.parseInt(text);
                        userBoard[row][col] = value;
                    } catch (NumberFormatException e) {
                        isUserInputValid = false;
                        break;
                    }
                } else {
                    userBoard[row][col] = 0;
                }
            }
            if (!isUserInputValid) {
                break;
            }
        }

        if (!isUserInputValid) {
            JOptionPane.showMessageDialog(this, "Vui lòng chỉ nhập số từ 1-9.");
            return;
        }

        if (solver.solve()) {
            // So sánh lời giải với bảng của người dùng
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    int originalValue = savedBoard[row][col];
                    int solvedValue = initialBoard[row][col];
                    int userValue = userBoard[row][col];

                    if (originalValue == 0) { // Ô này ban đầu là trống
                        if (userValue != 0) { // Người dùng đã nhập vào ô này
                            if (userValue == solvedValue) {
                                cells[row][col].setBackground(new Color(153, 255, 153)); // Màu xanh lá cây cho ô đúng
                            } else {
                                cells[row][col].setBackground(new Color(255, 102, 102)); // Màu đỏ cho ô sai
                            }
                        }
                        // Điền các số còn thiếu từ lời giải
                        cells[row][col].setText(String.valueOf(solvedValue));
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Sudoku đã được giải thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Không thể giải Sudoku này.");
        }
    }

    private void clearBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                initialBoard[row][col] = savedBoard[row][col];
                cells[row][col].setText(initialBoard[row][col] == 0 ? "" : String.valueOf(initialBoard[row][col]));

                // Đặt lại màu nền về màu ban đầu
                if (initialBoard[row][col] == 0) {
                    cells[row][col].setBackground(Color.WHITE);
                } else {
                    cells[row][col].setBackground(new Color(240, 240, 240));
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

    private void newBoard() {
        int[][] newPuzzle = generator.generateNewBoard();

        for (int i = 0; i < 9; i++) {
            System.arraycopy(newPuzzle[i], 0, initialBoard[i], 0, 9);
            System.arraycopy(newPuzzle[i], 0, savedBoard[i], 0, 9); // Cập nhật cả savedBoard
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int value = initialBoard[row][col];
                cells[row][col].setText(value == 0 ? "" : String.valueOf(value));
                cells[row][col].setEditable(value == 0);
                cells[row][col].setBackground(value == 0 ? Color.WHITE : new Color(240, 240, 240));
            }
        }
    }
}
