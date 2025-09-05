// Kiểm tra
package keongotcutie;

public class Validator {

    // Kiểm tra xem số num có hợp lệ tại ô [row][col] không
    public boolean isValid(int[][] board, int row, int col, int num) {
        // Kiểm tra hàng
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }

        // Kiểm tra cột
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Kiểm tra ô vuông 3x3
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true; // Hợp lệ
    }
}