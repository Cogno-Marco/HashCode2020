import java.util.Comparator;

/**
 * @author Riccardo De Zen.
 */
public class BookComparator implements Comparator<Book> {
    /**
     * This comparator orders the Books by value, in a decreasing order.
     */
    public int compare(Book first, Book second) {
        return second.getValue() - first.getValue();
    }
}