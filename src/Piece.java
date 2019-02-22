public class Piece {

    private char piece;

    public Piece(char piece) {
        setPiece(piece);
    }

    public Piece(Piece gamePiece) {
        this.piece = gamePiece.piece;
    }

    public void setPiece(char piece) {
        this.piece = piece;
    }

    public boolean isEqual(Piece p){
        return this.piece == p.piece;
    }

    public String toString() {
        return "" + piece;
    }

}
