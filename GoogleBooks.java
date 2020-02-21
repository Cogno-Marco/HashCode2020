import java.util.List;
import java.util.ArrayList;

public class GoogleBooks {

    private static final String[] inputs = { "a_example.txt", "b_read_on.txt", "c_incunabula.txt",
            "d_tough_choices.txt", "e_so_many_books.txt", "f_libraries_of_the_world.txt" };

    private static final String[] outputs = { "output_a.txt", "output_b.txt", "output_c.txt", "output_d.txt",
            "output_e.txt", "output_f.txt" };

    private static List<Library> signedUpLibraries = new ArrayList<Library>();

    public static void main(String[] args) {
        try {
            int current = 1;
            ProblemData pd = IOUtils.readInputFile(inputs[current]);
            OutputLibraries ol = new OutputLibraries(chooseAllLibraries(pd.getLibraries(), pd.getDays()), pd.getDays());
            System.out.println(ol.getOutput());
            IOUtils.writeOutputFile(ol, outputs[current]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @returns Ordered list of choosen libraries that will send the books
     */
    private static List<Library> chooseAllLibraries(List<Library> libraries, int problemDays) {

        for (int day = 0; day < problemDays
                && libraries.size() > 0; /* Non fare niente, ci arrangiamo ad aggiornare day */) {
            int signUpDays = signUpLibrary(libraries, problemDays - day);
            day += signUpDays;
            System.out.println("chooseAllLibraries: current day " + day);
        }
        return signedUpLibraries;
    }

    /**
     * @return How many days has been used, always less or equals than remainingDays
     */
    private static int signUpLibrary(List<Library> libraries, int remainingDays) {
        // Scegliamo la libreria che potrebbe fare il risultato maggiore
        System.out.println("libraries count " + libraries.size());
        Library chosenLibrary = chooseBestLibrary(libraries, remainingDays);
        if (chosenLibrary == null) // Se non viene scelta nessuna libreria
            return remainingDays; //

        // Aggiungiamo alla lista delle librerie
        signedUpLibraries.add(chosenLibrary);
        libraries.remove(chosenLibrary);
        int signupTime = chosenLibrary.getSignupTime();
        removeBooksFromAllLibraries(libraries, chosenLibrary.getBooksToSend(remainingDays - signupTime));
        return signupTime;
    }

    /**
     * @return The library with the highest potential score considering the books
     *         that have been shipped up to the current day. null if no library is
     *         available
     */
    private static Library chooseBestLibrary(List<Library> libraries, int remainingDays) {
        // System.out.println("Number of libraries: " + libraries.size() +
        // "\nchooseBestLibrary: remainingDays " + remainingDays);
        int maxScore = 0;
        Library chosenLibrary = null;
        int libraryScore = 0; // Variabile nel for
        for (Library l : libraries) {
            libraryScore = l.getPossibleScore(remainingDays); // Per non calcolarlo due volte
            // System.out.println("Library " + l.getLibraryName() + " score: " +
            // libraryScore);
            if (libraryScore > maxScore) {
                maxScore = libraryScore;
                chosenLibrary = l;
            }
        }
        if (chosenLibrary != null)
            System.out.println("Scelto la libreria " + chosenLibrary.getLibraryName() + " con punteggio " + maxScore);
        return chosenLibrary;

    }

    /**
     * Removes books that will be shipped by a library from all the other libraries
     * 
     * @param libraries     without the removed library
     * @param booksToRemove Books that will actually be removed
     */
    private static void removeBooksFromAllLibraries(List<Library> libraries, List<Book> booksToRemove) {
        for (int i = 0; i < libraries.size(); i++) {
            libraries.get(i).getBooks().removeAll(booksToRemove);
        }
    }

}