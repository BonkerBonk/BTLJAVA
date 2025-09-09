package bonk;

import java.util.Random;

public class SudokuGenerator {
    private final int[][] board;
    private final Random random;

    public SudokuGenerator() {
        this.board = new int[9][9];
        this.random = new Random();
    }

    // Phương thức chính để tạo một bảng Sudoku mới
    public int[][] generateNewBoard() {
        // Xóa bảng cũ
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 0;
            }
        }

        // Điền các số vào bảng trống để tạo một bảng đã giải
        fillBoard(0, 0);

        // Xóa một số ô để tạo câu đố. Độ khó có thể tùy chỉnh ở đây.
        int cellsToRemove = 40; // Xóa khoảng 40 ô cho độ khó trung bình
        removeCells(cellsToRemove);
        return this.board;
    }

    // Sử dụng thuật toán quay lui để điền các số vào bảng đã được giải
    private boolean fillBoard(int row, int col) {
        if (row == 9) {
            return true; // Đã điền xong toàn bộ bảng
        }

        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col == 8) ? 0 : col + 1;

        // Tạo một mảng ngẫu nhiên các số từ 1 đến 9 để đảm bảo tính ngẫu nhiên
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffleArray(numbers);

        for (int num : numbers) {
            if (isSafe(row, col, num)) {
                board[row][col] = num;
                if (fillBoard(nextRow, nextCol)) {
                    return true;
                }
                board[row][col] = 0; // Quay lui
            }
        }
        return false;
    }

    // Kiểm tra xem số có an toàn để đặt vào ô này không
    private boolean isSafe(int row, int col, int num) {
        // Kiểm tra hàng
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        // Kiểm tra cột
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        // Kiểm tra khối 3x3
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // Trộn mảng ngẫu nhiên
    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int j = random.nextInt(array.length);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // Xóa ngẫu nhiên một số ô
    private void removeCells(int count) {
        while (count > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                count--;
            }
        }
    }
}
