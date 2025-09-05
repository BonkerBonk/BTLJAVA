package app;

import keongotcutie.Input;
import keongotcutie.Printer;
import keongotcutie.Validator;

public class main {
    public static void main(String[] args) {
        // Tạo đối tượng cho từng chức năng
        Input input = new Input();
        Printer printer = new Printer();
        Validator validator = new Validator();

        // Nhập Sudoku
        int[][] board = input.readBoard();

        // In Sudoku vừa nhập
        printer.printBoard(board);

        // Thử kiểm tra hợp lệ: đặt số 5 vào ô [0][2]
        int row = 0, col = 2, num = 5;
        boolean valid = validator.isValid(board, row, col, num);

        if (valid) {
            System.out.println("Có thể đặt " + num + " vào ô [" + row + "][" + col + "]");
        } else {
            System.out.println("Không thể đặt " + num + " vào ô [" + row + "][" + col + "]");
        }
    }
}