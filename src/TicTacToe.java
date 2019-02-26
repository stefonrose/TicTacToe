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

    public boolean validInput(Move location) {
        int row = location.getRow();
        int col = location.getCol();
        boolean validRow = row >= 0 && row <= 2;
        boolean validCol = col >= 0 && col <= 2;
        return validRow && validCol;
    }

    public boolean isEmpty(Move location) {
        return board[location.getRow()][location.getCol()] == null;
    }

    public int movesTilDraw() {
        return board.length * board.length - totalMoves;
    }

    public Piece getWinner() {
        Move[][] wins = Move.winningMoves();

        for (Move[] win : wins) {
            List<Piece> winningPositions = new LinkedList<Piece>();
            for (Move location : win) {
                winningPositions.add(board[location.getRow()][location.getCol()]);
            }
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

    public void play(Move location) {
        board[location.getRow()][location.getCol()] = player[nextPlayer];
        nextPlayer = nextPlayer != 0 ? 0 : 1;
        totalMoves++;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TicTacToe game = new TicTacToe();

        System.out.println("Time for a game of Tic-Tac-Toe.\n");

        while (true) {
            System.out.println("Would you like to know how to play?\n[Y]es\n[N]o\n");
            String rules = input.nextLine();
            if (rules.toLowerCase().equals("y")) {
                String example1 = "|* * *|\n|X * *|\n|* * *|\n";
                String example2 = "|* * *|\n|X * *|\n|* O *|\n";
                System.out.println("\nHow to play:\nEnter two numbers separated by a comma to indicate where you would like to make a move.");
                System.out.println("The first number indicates the row, while the second number indicates the column.");
                System.out.println("Enter 0 for the first row/column, 1 for the middle row/column and 2 for the last row/column.");
                System.out.println("For example player 'X' entering 1,0 results in:\n" + example1 + "\n");
                System.out.println("And player 'O' entering 2,1 results in:\n" + example2 + "\n");
                System.out.println("Press enter to begin!");
                input.nextLine();

                break;
            } else if (rules.toLowerCase().equals("n")) {
                break;
            } else {
                System.out.println("Pleas enter 'Y' for yes or 'N' for no.");
                continue;
            }
        }


        boolean complete = false;

        while (!complete) {
            if (game.movesTilDraw() == 0) {
                System.out.printf("Progress: This game is a draw!\n\n%s\n\n", game);
                complete = true;
            } else if (game.getWinner() != null) {
                System.out.printf("Progress: %s is the winner!\n\n%s\n\n", game.getWinner(), game);
                complete = true;
            } else if (game.getWinner() == null) {
                System.out.printf("Progress: No winner yet...\n\n%s\n\n", game);

                System.out.printf("%s, it's your turn:\n\n", game.player[game.nextPlayer]);
                String playerInput = input.nextLine();
                Move playerMove;
                try {
                    playerMove = new Move(playerInput);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("That was not a valid input.\nPlease enter 2 numbers separated by a comma.\nValid numbers are: 0, 1, 2\n");
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
            }
        }
    }
}