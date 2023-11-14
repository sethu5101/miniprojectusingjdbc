package miniprojectjdbc;
class OnlineBook {
    private int bookId;
    private String bookTitle;
    private String author;
    private double price;
    private int stock;

    public OnlineBook(int bookId, String bookTitle, String author, double price, int stock) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + "\nTitle: " + bookTitle + "\nAuthor: " + author + "\nPrice: $" + price +
                "\nStock: " + stock;
    }
}