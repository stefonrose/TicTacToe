import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {

    private Piece[][] board;
    private Piece[] player;
    private int nextPlayer;
    private int totalMoves;

    public TicTacToe() {
        nextPlayer = 0;
        player = new Piece[]{new Piece('X'), new Piece('O')};
        board = new Piece[3][3];
        for (Piece[] row : board) {
            for (Piece col : row) {
                col = null;
            }
        }
    }

    public int[] convertInput(String input) {
        String[] inputStringArr = input.split(",");
        int[] move = new int[2];
        String n;

        n = inputStringArr[0].replaceAll("\\s", "");
        move[0] = Integer.parseInt(n);

        n = inputStringArr[1].replaceAll("\\s", "");
        move[1] = Integer.parseInt(n);
        System.out.printf("\n\n*debugging* converted input is %s\n\n", Arrays.toString(move));

        return move;
    }

    public boolean validInput(int[] location) {
        int row = location[0];
        int col = location[1];
        boolean validRow = row >= 0 && row <= 2;
        boolean validCol = col >= 0 && col <= 2;
        return validRow && validCol;
    }

    public boolean isEmpty(int[] location) {
        int row = location[0];
        int col = location[1];
        return board[row][col] == null;
    }

    public int movesRemaining() {
        return board.length * board.length - totalMoves;
    }

    public Piece getWinner() {
        int[][][] wins =
                {
                        {{0, 0}, {1, 0}, {2, 0}}, {{0, 1}, {1, 1}, {2, 1}},
                        {{0, 2}, {1, 2}, {2, 2}}, {{0, 0}, {0, 1}, {0, 2}},
                        {{1, 0}, {1, 1}, {1, 2}}, {{2, 0}, {2, 1}, {2, 2}},
                        {{0, 0}, {1, 1}, {2, 2}}, {{0, 2}, {1, 1}, {2, 0}}
                };
        //Piece winner = null;
        for (int[][] win : wins) {
            List<Piece> winningPositions = new LinkedList<Piece>();
            for (int[] location : win) {
                int row = location[0];
                int col = location[1];
                winningPositions.add(board[row][col]);
            }
            //Piece pos1 = winningPositions.get(0);
            if (winningPositions.get(0) == winningPositions.get(1) &&
                    winningPositions.get(0) == winningPositions.get(2)) {
                return winningPositions.get(0);
            }
        }
        return null;
    }

    public String toString() {
        String board = "";
        for (int row = 0; row < this.board.length; row++) {
//            for (int col = 0; col < this.board[row].length; col++) {
//                board += "--";
//            }
            board += "|";
            for (int col = 0; col < this.board[row].length; col++) {
                if (col != 0) {
                    board += " " + (this.board[row][col] == null ? "*" : this.board[row][col]);
                } else {
                    board += "" + (this.board[row][col] == null ? "*" : this.board[row][col]);
                }
            }
            board += "|\n";
        }
        return board;
    }

    public void play(int[] location) {
        int row = location[0];
        int col = location[1];
        board[row][col] = player[nextPlayer];
        nextPlayer = nextPlayer != 0 ? 0 : 1;
        totalMoves++;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TicTacToe game = new TicTacToe();

        System.out.println("Time for a game of Tic-Tac-Toe.\n");

        while (true) {
            System.out.println("Would you like to know how to play?\n[Y]es\n[N]o");
            String rules = input.nextLine();
            if (rules.toLowerCase().equals("y")) {
                String example1 = "|* * *|\n|X * *|\n|* * *|\n";
                String example2 = "|* * *|\n|X * *|\n|* O *|\n";
                System.out.println("How to play:\nEnter two numbers separated by a comma to indicate where you would like to make move.");
                System.out.println("The first number indicates the row, while the second number indicates the column.");
                System.out.println("Enter 0 for the first row/column, 1 for the middle row/column and 2 for the last row/column.");
                System.out.println("For example player 'X' entering 1,0 results in:\n" + example1 + "\n");
                System.out.println("And player 'O' entering 2,1 results in:\n" + example2 + "\n");
                System.out.println("Press enter to begin!");
                String begin = input.nextLine();
                break;
            } else if (rules.toLowerCase().equals("n")){
                break;
            } else {
                System.out.println("Pleas enter 'Y' for yes or 'N' for no.");
                continue;
            }

        }


        boolean complete = false;

        while (!complete) {
            if (game.totalMoves > 8) {
                System.out.printf("Progress: This game is a draw!\n\n%s", game);
                complete = true;
            } else if (game.getWinner() == null) {
                System.out.printf("Progress: No winner yet...\n\n%s\n\n", game);

                System.out.printf("%s, it's your turn:\n\n", game.player[game.nextPlayer]);
                String playerInput = input.nextLine();
                int[] playerMove = new int[2];
                try {
                    playerMove = game.convertInput(playerInput);
                } catch (NumberFormatException e) {
                    System.out.println("That was not a valid input.\nPlease enter 2 numbers separated by a comma.\n");
                    continue;
                }
                if (game.validInput(playerMove)) {
                    if (game.isEmpty(playerMove)) {
                        game.play(playerMove);
                    } else {
                        System.out.println("The location you entered already contains a piece.\nPlease enter another.\n");
                        continue;
                    }
                } else {
                    System.out.println("The location you entered is out of bounds.\nPlease enter another.\n");
                    continue;
                }
            } else if (game.getWinner() != null) {
                System.out.printf("Progress: %s is the winner!\n\n%s", game.getWinner(), game);
                complete = true;
            }
        }
    }
}
