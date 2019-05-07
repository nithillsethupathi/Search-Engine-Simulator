package HeapSort;


/**
 * PageRank class that assigns a PageRank score based on Frequency, history, links, and money for the URLS
 * The score for all 4 are assigned randomly and are totalled into one score that is the PageRank score
 */
public class PageRank {
    String URL;
    private int frequency;
    private int history;
    private int linked;
    private int money;
    private int score;

    /**
     * Initializing PageRank constructor
     */
    public PageRank(String URL) {
        this.URL = URL;
        this.frequency = (int) (Math.random() * 25);
        this.history = (int) (Math.random() * 25);
        this.linked = (int) (Math.random() * 25);
        this.money = (int) (Math.random() * 25);
        this.score = frequency + history + linked + money;
    }

    /**
     * Getter method to get the frequency
     *
     * @return
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Getter method that gets the history
     *
     * @return
     */
    public int getHistory() {
        return history;
    }

    /**
     * Getter method that gets the link
     *
     * @return
     */
    public int getLinked() {
        return linked;
    }

    /**
     * Getter method that gets the money
     *
     * @return
     */
    public int getMoney() {
        return money;
    }

    /**
     * Getter method that gets the score
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter method that sets the score
     */
    public void setScore(int score) {
        this.score = score;
    }
}
