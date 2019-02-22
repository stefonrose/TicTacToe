public class Piece {

    private char piece;

    public Piece(char piece) {
        setPiece(piece);
    }

    public void setPiece(char piece) {
        this.piece = piece;
    }

    public String toString() {
        return "" + piece;
    }

}
