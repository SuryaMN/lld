import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MovieBookingSystemMain {

    public static void main(String[] args) {

        Theatre theatre = new Theatre("INOX");
        Screen screen = new Screen(theatre);
        Movie movie = new Movie("Superbad");
        Show show = new Show(screen, movie, LocalDateTime.of(2026,06,01,01,01), LocalDateTime.of(2026,06,03,01,01));
        theatre.addShow(show);

        BookingService service = new BookingService();
        List<String> seatIds = new ArrayList<>();
        seatIds.add("A10");



        Thread t1 = new Thread(() -> {
           Ticket ticket = service.bookShow(show, seatIds);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(ticket != null) {
               service.cancelBooking(ticket.getConfirmationId());
           }
        });

        Thread t2 = new Thread(() -> {
            Ticket ticket = service.bookShow(show, seatIds);
            if(ticket != null) {
                service.cancelBooking(ticket.getConfirmationId());
            }
        });

        t1.start();
        t2.start();

    }
}
