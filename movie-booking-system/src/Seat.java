public class Seat {

//- seatId: String //(A10)
//- row: char
//- seatNumber: int
//- screen: Screen

    private String seatId;
    private char row;
    private int seatNumber;
    private Screen screen;

    public Seat(char row, int seatNumber, Screen screen) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.screen = screen;
        this.seatId = row + "" + seatNumber;
    }

    public String getSeatId() {
        return seatId;
    }

    public char getRow() {
        return row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
    public Screen getScreen() {
        return screen;
    }
}
