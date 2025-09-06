package app;

import keongotcutie.Input;
import keongotcutie.Printer;
import keongotcutie.Validator;
import keongotcutie.SudokuSolver;

public class main {
    public static void main(String[] args) {
        // Tạo đối tượng cho từng chức năng
        Input input = new Input();
        Printer printer = new Printer();
        Validator validator = new Validator();

        // Nhập Sudoku
        int[][] board = input.readBoard();

        System.out.println("Bảng Sudoku ban đầu:");
        printer.printBoard(board);

        // Tạo đối tượng SudokuSolver và giải
        SudokuSolver solver = new SudokuSolver(board, validator);

        // Giải Sudoku
        if (solver.solve()) {
            System.out.println("\nLời giải Sudoku:");
            printer.printBoard(board);
        } else {
            System.out.println("\nKhông thể giải Sudoku này.");
        }
    }
}
