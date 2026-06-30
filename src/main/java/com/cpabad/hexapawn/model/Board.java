package main.java.com.cpabad.hexapawn.model;

public class Board {
    private int boardId;

    public String[] validSquaresArr = {"a1", "b1", "c1", "a2", "b2", "c2", "a3", "b3", "c3"};

    public Board(int boardId, String[] validSquaresArr) {
        this.boardId = boardId;
        this.validSquaresArr = validSquaresArr;
    }

    public int getBoardId() {
        return boardId;
    }

}
