package app;

import com.example.ui.SudokuUI;
import javax.swing.SwingUtilities;
import bonk.SudokuGenerator;

public class main {
    public static void main(String[] args) {
        // Tạo một bảng Sudoku ngẫu nhiên bằng Generator
        SudokuGenerator generator = new SudokuGenerator();
        int[][] initialBoard = generator.generateNewBoard();

        // Khởi chạy giao diện Swing trên Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            SudokuUI sudokuUI = new SudokuUI(initialBoard);
            sudokuUI.setVisible(true);
        });
    }
}
