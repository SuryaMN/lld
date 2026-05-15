import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {

    private static final AtomicInteger counter = new AtomicInteger(1);
    private String confirmationId;
    private Show show;
    private List<String> seatIds;

    public Ticket(Show show, List<String> seatIds) {
        this.show = show;
        this.seatIds = seatIds;
        this.confirmationId = String.valueOf(counter.getAndIncrement());
    }

    public String getConfirmationId() {
        return confirmationId;
    }

    public Show getShow() {
        return show;
    }

    public List<String> getSeats() {
        return seatIds;
    }

    @Override
    public String toString() {
        return "{" +
                "confirmationId=" + confirmationId +
                ", show=" + show.getMovie().getTitle() +
                ", screen=" + show.getScreen().getId() +
                ", seatIds=" + seatIds +
                '}';
    }
}
