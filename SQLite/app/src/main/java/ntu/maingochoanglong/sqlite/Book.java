package ntu.maingochoanglong.sqlite;

public class Book {
    private int bookID;
    private String bookName;
    private int page;
    private float price;
    private String description;

    public Book(int bookID, String bookName, int page, float price, String description) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.page = page;
        this.price = price;
        this.description = description;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
