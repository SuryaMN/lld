import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Theatre {
    private static final AtomicInteger counter = new AtomicInteger(1);
    private String id;
    private String name;
    private Map<String, List<Show>> showMap;

    public Theatre(String name) {
        this.name = name;
        this.id = String.valueOf(counter.getAndIncrement());
        this.showMap = new HashMap<>();
    }

    public void addShow(Show show) {
        String movieId = show.getMovie().getId();
        if(!this.showMap.containsKey(movieId)) {
            this.showMap.put(movieId, new ArrayList<>());
        }
        this.showMap.get(movieId).add(show);
    }

    public List<Show> getShows() {
        return showMap.values().stream().flatMap(Collection::stream).toList();
    }

//    public List<Show> getShows(String movieName) {
//    }
}
