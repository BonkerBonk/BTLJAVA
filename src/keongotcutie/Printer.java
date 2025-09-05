// Nhập dữ liệu
package src.keongotcutie;

public class    Printer{
    public  void    printBoard(int[][] board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                System.out.print(board[i][j]+" ");
            }
        }
    }
}