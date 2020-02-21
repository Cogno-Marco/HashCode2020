import java.util.*;

public class Library {

    private List<Book> books;
    private int signupTime;
    private int parallelCount;
    private int libraryName;

    /**
     * @param books The list of Books contained in this library, it is copied and
     *              sorted by value.
     */
    public Library(int libraryName, List<Book> books, int signupTime, int parallelCount) {
        List<Book> sortedBooks = new ArrayList<>(books);
        Collections.sort(sortedBooks, new BookComparator());
        this.books = sortedBooks;
        this.signupTime = signupTime;
        this.parallelCount = parallelCount;
        this.libraryName = libraryName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public int getSignupTime() {
        return signupTime;
    }

    public int getParallelCount() {
        return parallelCount;
    }

    public int getLibraryName() {
        return libraryName;
    }

    /**
     * @return The first book of the List, having the maximum value.
     */
    public Book getMaxValueBook() {
        return books.get(0);
    }

    /**
     * Method removing the first book of the List and returning it;
     */
    public Book popMaxValueBook() {
        Book maxValBook = books.get(0);
        books.remove(0);
        return maxValBook;
    }

    /**
     * Method removing {@code bookToRemove} from this library.
     * 
     * @param bookToRemove The book to remove.
     * @return true if the book was removed, false otherwise.
     */
    public boolean removeBook(Book bookToRemove) {
        return books.remove(bookToRemove);
    }

    /**
     * Calculates how much score it can do in the given remaining days
     * 
     * 
     * @param remainingDays Remaining days useful to scan
     * @param scannedBooks  Books already scanned
     */
    public int getPossibleScore(int remainingDays) {
        int internalBookCounter = 0; // Quanti libri sono stati contati da questa libreria
        int usefulDays = remainingDays - signupTime; // Quanti giorni possiamo effettivamente inviare libri
        int possibleScore = 0;
        // System.out.println("getPossibleScore: nomeLibreria " + libraryName);
        // Ciclo per i giorni rimanenti oppure per i libri
        for (int i = 0; i < usefulDays && internalBookCounter < books.size(); i++) {
            // j è quanti libri abbiamo mandato in questa giornata
            // System.out.println("getPossibleScore: giorno " + i);
            for (int j = 0; j < parallelCount && internalBookCounter < books.size(); j++) {
                // System.out.println("getPossibleScore: scelgo il libro numero " +
                // books.get(internalBookCounter).getId()+ " di valore " +
                // books.get(internalBookCounter).getValue());
                // I libri già mandati sono già stati rimossi da questa libreria
                possibleScore += books.get(internalBookCounter).getValue();
                internalBookCounter++;
            }
        }
        // System.out.println("getPossibleScore: ritorno lo score " + possibleScore);
        // return possibleScore;
        return possibleScore / signupTime;
    }

    /**
     * @param usefulDays remaining useful days (this means to exclude our signup
     *                   time).
     */
    public List<Book> getBooksToSend(int usefulDays) {
        List<Book> booksToSend = new ArrayList<Book>();
        long possiblebooks = (long) usefulDays * (long) parallelCount;
        // System.out.print("getBookSand: libreria " + libraryName + " : " + usefulDays
        // + " usefulDays " + usefulDays
        // + " parallelCount " + parallelCount + " possiblebooks " + possiblebooks);

        for (int i = 0; i < possiblebooks && i < books.size(); i++) {
            booksToSend.add(books.get(i));
            // System.out.print("Libro " + books.get(i).getId() + " valore " +
            // books.get(i).getValue());
        }
        // System.out.println("");
        return booksToSend;
    }
}