package miniprojectjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class OnlineBookApp {
    private ArrayList<OnlineBook> bookList = new ArrayList<>();
    private int nextBookId;
    private Connection connection;

    public OnlineBookApp() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/10247sql", "root", "root");

            // Initialize nextBookId by finding the maximum book_id in the database
            PreparedStatement getMaxId = connection.prepareStatement("SELECT MAX(book_id) FROM books");
            ResultSet resultSet = getMaxId.executeQuery();
            if (resultSet.next()) {
                nextBookId = resultSet.getInt(1) + 1;
            } else {
                nextBookId = 1;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(String bookTitle, String author, double price, int stock) {
        try {
            String insertQuery = "INSERT INTO books (book_title, author, price, stock) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, bookTitle);
            preparedStatement.setString(2, author);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, stock);
            preparedStatement.executeUpdate();

            OnlineBook book = new OnlineBook(nextBookId, bookTitle, author, price, stock);
            bookList.add(book);
            nextBookId++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAllBooks() {
        try {
            PreparedStatement getAllBooks = connection.prepareStatement("SELECT * FROM books");
            ResultSet resultSet = getAllBooks.executeQuery();

            while (resultSet.next()) {
                int bookId = resultSet.getInt("book_id");
                String title = resultSet.getString("book_title");
                String author = resultSet.getString("author");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");

                OnlineBook book = new OnlineBook(bookId, title, author, price, stock);
                bookList.add(book);
                System.out.println(book.toString() + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void purchaseBook(int bookId) {
        try {
            PreparedStatement getBookById = connection.prepareStatement("SELECT * FROM books WHERE book_id = ?");
            getBookById.setInt(1, bookId);
            ResultSet resultSet = getBookById.executeQuery();

            if (resultSet.next()) {
                int stock = resultSet.getInt("stock");

                if (stock > 0) {
                    PreparedStatement updateStock = connection.prepareStatement("UPDATE books SET stock = ? WHERE book_id = ?");
                    updateStock.setInt(1, stock - 1);
                    updateStock.setInt(2, bookId);
                    updateStock.executeUpdate();
                    System.out.println("Purchase successful! Enjoy your book.");
                } else {
                    System.out.println("Sorry, this book is out of stock.");
                }
            } else {
                System.out.println("Book not found. Invalid book ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
