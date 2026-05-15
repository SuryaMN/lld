import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Show {
    private Screen screen;
    private Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<String, Ticket> tickets;

    public Show(Screen screen, Movie movie, LocalDateTime startTime, LocalDateTime endTime) {
        this.screen = screen;
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tickets = new HashMap<>();
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Map<String, Ticket> tickets) {
        this.tickets = tickets;
    }
}
