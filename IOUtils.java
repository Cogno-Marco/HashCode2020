import java.util.*;
import java.io.File; // Import the File class
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors

/**
 * 
 * @author Luca Crema
 * @param <I> InputData type
 */
public class IOUtils {

	public static ProblemData readInputFile(String filename) throws FileNotFoundException, IOException {
		String fileURL = "sources/" + filename;
		File file = new File(fileURL);
		Scanner sc = new Scanner(file);
		// first line has scan days
		String[] firstLine = sc.nextLine().split(" ");
		int scanningDays = Integer.parseInt(firstLine[2]);

		// second line has books and values
		String[] booksValuesTXT = sc.nextLine().split(" ");
		int[] booksValues = new int[booksValuesTXT.length];
		for (int i = 0; i < booksValuesTXT.length; i++) {
			booksValues[i] = Integer.parseInt(booksValuesTXT[i]);
		}

		List<Library> libraries = new ArrayList<Library>();

		int currentLibraryName = 0;

		// all other lines have libraries data
		while (sc.hasNextLine()) {
			String[] libraryData = sc.nextLine().split(" ");
			if (libraryData.length < 3)
				break;
			int signupDays = Integer.parseInt(libraryData[1]);
			int parallelCount = Integer.parseInt(libraryData[2]);
			String[] libraryBooksTXT = sc.nextLine().split(" ");
			if (libraryBooksTXT.length <= 0)
				break;

			List<Book> libraryBooks = new ArrayList<Book>();
			for (String bookIDTXT : libraryBooksTXT) {
				int bookID = Integer.parseInt(bookIDTXT);
				Book book = new Book(bookID, booksValues[bookID]);
				libraryBooks.add(book);
			}
			Collections.sort(libraryBooks, new BookComparator());
			Library library = new Library(currentLibraryName, libraryBooks, signupDays, parallelCount);
			libraries.add(library);
			currentLibraryName++;
		}

		sc.close();

		return new ProblemData(libraries, scanningDays);
	}

	public static void writeOutputFile(OutputData data, String filename) throws IOException {
		String fileURL = "outputs/" + filename;

		createFile(fileURL);

		writeToFile(fileURL, data.getOutput());
	}

	private static void createFile(String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists())
			file.createNewFile();
	}

	private static void writeToFile(String filename, String content) throws IOException {
		FileWriter fileWriter = new FileWriter(filename);
		fileWriter.write(content);
		fileWriter.close();
	}

}
