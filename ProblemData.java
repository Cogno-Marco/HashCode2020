import java.util.*;

/**
 * ProblemData
 * 
 * @author Luca Crema, Marco Cognolato, Riccardo De Zen.
 */
public class ProblemData {

    private List<Library> libraries;
    private int days;
    private int totalBookValue = 0;
    private int bookCount;

    public ProblemData(List<Library> libraries, int days) {
        Set<Book> allBooks = new HashSet<>();
        for (Library eachLibrary : libraries)
            for (Book eachBook : eachLibrary.getBooks())
                allBooks.add(eachBook);
        for (Book eachBook : allBooks)
            totalBookValue += eachBook.getValue();
        bookCount = allBooks.size();
        this.libraries = libraries;
        this.days = days;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public int getDays() {
        return days;
    }

    public int getTotalBookValue() {
        return totalBookValue;
    }

    public int getBookCount() {
        return bookCount;
    }
}