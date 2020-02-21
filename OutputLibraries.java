import java.util.*;

/**
 * OutputLibraries
 */
public class OutputLibraries implements OutputData {
    private List<Library> libraries;
    private int problemDays;

    public OutputLibraries(List<Library> libraries, int problemDays) {
        this.libraries = libraries;
        this.problemDays = problemDays;
    }

    public String getOutput() {
        String output = "";
        int score = 0;
        int remainingDays = problemDays;

        // first line: how many libraries for scanning
        output += libraries.size() + "\n";

        // other lines: for each library...
        for (Library library : libraries) {

            // the first line has the library name...
            output += library.getLibraryName() + " ";

            remainingDays -= library.getSignupTime();
            List<Book> booksForSignup = library.getBooksToSend(remainingDays);

            // and how many books...
            output += booksForSignup.size() + "\n";

            // the second line has the books
            for (Book book : booksForSignup) {
                output += book.getId() + " ";
                score += book.getValue();
            }
            output += "\n";
        }
        System.out.println("Output score: " + score);

        return output;
    }
}