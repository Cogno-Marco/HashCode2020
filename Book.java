public class Book {

    private int id;
    private int value;

    public Book(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    /**
     * Two Books are equal if their id is.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Book))
            return false;
        Book otherBook = (Book) other;
        return this.getId() == otherBook.getId();
    }
}