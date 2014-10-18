package main.logger.entity;

/**
 * Created by TDiva on 18.10.2014.
 */
public class Suit {

    private long id;
    private long apparelId;

    public Suit(long id, long apparelId) {
        this.id = id;
        this.apparelId = apparelId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getApparelId() {
        return apparelId;
    }

    public void setApparelId(long apparelId) {
        this.apparelId = apparelId;
    }
}
