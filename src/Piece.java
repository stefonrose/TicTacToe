public class Piece {

    private char piece;

    public Piece(char piece) {
        setPiece(piece);
    }

    public Piece(Piece gamePiece) {
        this.piece = gamePiece.piece;
    }

    public char getPiece() {
        return piece;
    }

    public void setPiece(char piece) {
        this.piece = piece;
    }

    public String toString() {
        return "" + piece;
    }

}