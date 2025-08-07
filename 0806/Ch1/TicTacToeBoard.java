//練習 1.4：井字遊戲棋盤

import java.util.Scanner;

public class TicTacToeBoard {
    private char[][] board;
    private char currentPlayer;

    public TicTacToeBoard() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // 1. 初始化棋盤
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // 2. 顯示棋盤
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // 3. 放置棋子
    public boolean placeMark(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) { // 在範圍內
            if (board[row][col] == ' ') { // 空格才能放
                board[row][col] = currentPlayer;
                return true;
            }
        }
        return false;
    }

    // 4. 換玩家
    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // 5. 檢查是否有玩家勝利
    public boolean checkWin() {
        // 檢查行
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                board[i][1] == currentPlayer &&
                board[i][2] == currentPlayer) {
                return true;
            }
        }
        // 檢查列
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer &&
                board[1][j] == currentPlayer &&
                board[2][j] == currentPlayer) {
                return true;
            }
        }
        // 檢查對角線
        if (board[0][0] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    // 6. 檢查平手
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    // 主程式（簡單雙人模式）
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToeBoard game = new TicTacToeBoard();

        System.out.println("=== 井字遊戲開始 ===");
        game.printBoard();

        while (true) {
            System.out.println("玩家 " + game.currentPlayer + " 請輸入行列 (0~2) 例如: 1 1");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (game.placeMark(row, col)) {
                game.printBoard();

                if (game.checkWin()) {
                    System.out.println("玩家 " + game.currentPlayer + " 勝利！");
                    break;
                }

                if (game.isBoardFull()) {
                    System.out.println("平手！");
                    break;
                }

                game.changePlayer();
            } else {
                System.out.println("位置無效，請重新輸入！");
            }
        }

        scanner.close();
    }
}
