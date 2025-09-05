// Nhập dữ liệu
package keongotcutie;

public class Printer {

    // In cả bảng Sudoku 9x9
    public void printBoard(int[][] board) {
        System.out.println("Bảng Sudoku hiện tại:");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------"); // dòng ngang phân cách
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| "); // cột dọc phân cách
                }
                if (board[i][j] == 0) {
                    System.out.print(". "); // ô trống hiển thị bằng dấu chấm
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}