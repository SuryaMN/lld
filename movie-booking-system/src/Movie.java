import java.util.concurrent.atomic.AtomicInteger;

public class Movie {

    private static final AtomicInteger counter = new AtomicInteger(1);
    private String id;
    private String title;

    public Movie(String title) {
        this.title = title;
        this.id = String.valueOf(counter.getAndIncrement());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
