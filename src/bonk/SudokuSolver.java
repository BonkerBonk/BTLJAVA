package bonk;

import keongotcutie.Validator;

public class SudokuSolver {
    private final int[][] board;
    public static final int BOARD_SIZE = 9;
    private final Validator validator;

    public SudokuSolver(int[][] board, Validator validator) {
        this.board = board;
        this.validator = validator;
    }

    /**
     * Phương thức chính để bắt đầu giải Sudoku.
     * @return true nếu tìm thấy lời giải, ngược lại false.
     */
    public boolean solve() {
        return backtrackSolve();
    }

    /**
     * Thuật toán Backtracking đệ quy để giải Sudoku.
     * @return true nếu tìm thấy lời giải, ngược lại false.
     */
    private boolean backtrackSolve() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                // Tìm ô trống (có giá trị 0)
                if (board[row][col] == 0) {
                    // Thử các số từ 1 đến 9
                    for (int num = 1; num <= BOARD_SIZE; num++) {
                        // Kiểm tra nếu số đó hợp lệ tại vị trí này
                        if (validator.isValid(board, row, col, num)) {
                            // Đặt số vào ô
                            board[row][col] = num;

                            // Gọi đệ quy để giải tiếp
                            if (backtrackSolve()) {
                                return true;
                            } else {
                                // Nếu không tìm được lời giải, hoàn tác và thử số khác
                                board[row][col] = 0;
                            }
                        }
                    }
                    // Nếu thử hết các số mà không có lời giải, trả về false
                    return false;
                }
            }
        }
        // Nếu không còn ô trống, nghĩa là đã tìm được lời giải
        return true;
    }
}
