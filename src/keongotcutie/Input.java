//  Xuất dữ liệu
package keongotcutie;

import java.util.Scanner;


public  class  Input {
    private Scanner scanner = new Scanner(System.in);
    public  int[][] readBoard() {
        int[][] board = new int[9][9];
        System.out.println("Nhập Sudoku (9x9,dùng 0 cho ô trống)");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = scanner.nextInt();
            }
        }
        return board;
    }

    public void close(){
        if(scanner != null){
            scanner.close();
        }
    }
}