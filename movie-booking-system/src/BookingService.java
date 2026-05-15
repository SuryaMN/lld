import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {

    private final List<Theatre> theatres;
    private final Map<String, Ticket> ticketRegistry;

    public BookingService() {
        this.ticketRegistry = new HashMap<>();
        this.theatres = new ArrayList<>();
    }

//+ getShowsByMovie(String substring) -> List<Show>
//+ getShowsInATheatre(String theatreName) -> List<Show>
//+ getSeats(Show show) -> List<Seat>
//+ cancelBooking(String confirmationId) -> boolean

    public void addTheatre(Theatre theatre) {
        this.theatres.add(theatre);
    }
    public synchronized Ticket bookShow(Show show, List<String> seatIds) {
        if(seatIds == null || seatIds.isEmpty()) {
            throw new RuntimeException("Seats not selected");
        }
        if(LocalDateTime.now().isAfter(show.getStartTime())) {
            throw new RuntimeException("Show started..too late");
        }

        Ticket ticket = null;
        try {
            System.out.println("[ " + Thread.currentThread().getName() + " ] " + "Booking in progress for seat ids - " + seatIds);

            List<Seat> seats = seatIds.stream().map(seatId -> show.getScreen().getSeat(seatId)).toList();
            List<Seat> reservedSeats = this.ticketRegistry.values().stream()
                    .flatMap(tic -> tic.getSeats().stream())
                    .map(seatId -> show.getScreen().getSeat(seatId))
                    .toList();

            for(Seat seat: seats) {
                if(reservedSeats.contains(seat)) {
                    throw new RuntimeException("Seat " + seat.getSeatId() + " is already booked. Choose another seat");
                }
            }
            ticket = new Ticket(show, seatIds);

            Thread.sleep(5000); //Simulate payment delay

            System.out.println("[ " + Thread.currentThread().getName() + " ] " + "Generated ticket with confirmation id - " + ticket.getConfirmationId());
            this.ticketRegistry.put(ticket.getConfirmationId(), ticket);
            System.out.println("[ " + Thread.currentThread().getName() + " ] " + "Booking done. Here is your ticket - " + ticket);
            return ticket;
        } catch(Exception e) {
            System.out.println("[ " + Thread.currentThread().getName() + " ] " + "Unable to reserve seats. Error - " + e);
        }
        return ticket;
    }

    public synchronized void cancelBooking(String confirmationId) {

        if(confirmationId == null || confirmationId.isBlank() || !this.ticketRegistry.containsKey(confirmationId)) {
            System.out.println("[ " + Thread.currentThread().getName() + " ] " + "Not a valid confirmation id");
            return;
        }

        Ticket ticket = this.ticketRegistry.get(confirmationId);
        if(LocalDateTime.now().isAfter(ticket.getShow().getStartTime())) {
            System.out.println("[ " + Thread.currentThread().getName() + " ] " + "Show already started. Reservation cannot be cancelled now");
            return;
        }

        System.out.println("[ " + Thread.currentThread().getName() + " ] " + "Reservation cancelled");
        this.ticketRegistry.remove(confirmationId);
    }

//    public List<Show> getShowsInATheatre(String theatreName) {
//
//    }

}
