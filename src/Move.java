public class Move {

    private int[] location;
    private int row;
    private int col;

    public Move(String input) {
        String[] inputStringArr = input.split(",");
        location = new int[2];
        String n;

        n = inputStringArr[0].replaceAll("\\s", "");
        try {
            int num = Integer.parseInt(n);
            location[0] = num;
            row = num;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }

        n = inputStringArr[1].replaceAll("\\s", "");
        try {
            int num = Integer.parseInt(n);
            location[1] = num;
            col = num;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public static Move[][] winningMoves(){
        Move[][] wins = {
                {new Move("0,0"), new Move("1,0"), new Move("2,0")},
                {new Move("0,1"), new Move("1,1"), new Move("2,1")},
                {new Move("0,2"), new Move("1,2"), new Move("2,2")},
                {new Move("0,0"), new Move("0,1"), new Move("0,2")},
                {new Move("1,0"), new Move("1,1"), new Move("1,2")},
                {new Move("2,0"), new Move("2,1"), new Move("2,2")},
                {new Move("0,0"), new Move("1,1"), new Move("2,2")},
                {new Move("0,2"), new Move("1,1"), new Move("2,0")}
                        };
        return wins;
    }

}
