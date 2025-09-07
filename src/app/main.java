package app;

import com.example.ui.SudokuUI;
import keongotcutie.Input;
import javax.swing.SwingUtilities;

public class main {
    public static void main(String[] args) {
        // Tạo đối tượng Input để lấy bảng Sudoku ban đầu.
        // Tùy thuộc vào cách bạn cài đặt, có thể lấy từ console hoặc file.
        Input input = new Input();
        int[][] initialBoard = input.readBoard();

        // Khởi chạy giao diện Swing trên Event Dispatch Thread để đảm bảo an toàn luồng.
        SwingUtilities.invokeLater(() -> {
            SudokuUI sudokuUI = new SudokuUI(initialBoard);
            sudokuUI.setVisible(true);
        });
    }
}
