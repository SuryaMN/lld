import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Screen {

    private static final AtomicInteger counter = new AtomicInteger(1);
    private String id;
    private List<Seat> seats;
    private Theatre theatre;

    public Screen(Theatre theatre) {
        this.theatre = theatre;
        this.id = "screen" + String.valueOf(counter.getAndIncrement());
        createSeatMap();
    }

    private void createSeatMap() {
        this.seats = new ArrayList<>();
        for(char c='A'; c<='Z'; c++) {
            for(int i=1; i<=20; i++) {
                Seat seat = new Seat(c, i, this);
                this.seats.add(seat);
            }
        }
    }

    public Seat getSeat(String seatId) {
        return seats.stream().filter(seat -> seat.getSeatId().equals(seatId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Seat with seat id " + seatId + " not found"));
    }

    public String getId() {
        return id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Theatre getTheatre() {
        return theatre;
    }
}
