package DAOs;

/** OOP Feb 2024
 *
 * Data Access Object (DAO) for User table with MySQL-specific code
 * This 'concrete' class implements the 'UserDaoInterface'.
 *
 * The DAO will contain the SQL query code to interact with the database,
 * so, the code here is specific to a MySql database.
 * No SQL queries will be used in the Business logic layer of code, thus, it
 * will be independent of the database specifics. Changes to code related to
 * the database are all contained withing the DAO code base.
 *
 *
 * The Business Logic layer is only permitted to access the database by calling
 * methods provided in the Data Access Layer - i.e. by calling the DAO methods.
 * In this way, the Business Logic layer is seperated from the database specific code
 * in the DAO layer.
 */

import DTOs.Book;
import Exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlBooksDao extends MySqlDao implements UserDaoInterface
{
    /**
     * Will access and return a List of all users in User database table
     * @return List of User objects
     * @throws DaoException
     */

    public List<Book> findAllBooks() throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> usersList = new ArrayList<>();

        try
        {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM USER";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                float price = resultSet.getFloat("price");
                Book u = new Book(id, title, author, price);
                usersList.add(u);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllUseresultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return usersList;     // may be empty
    }

    /**
     * Given a username and password, find the corresponding User
     * @param id
     *
     * @return User object if found, or null otherwise
     * @throws DaoException
     */

    //feature 2
    public Book  getBookByID(int id){
        String url = "jdbc:mysql://localhost:3306/bookshop";
        String userName = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            String sql = "SELECT * FROM books WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int bookId = rs.getInt("id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    float price = rs.getFloat("price");
                    System.out.println("Book found:");
                    System.out.println("ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Price: " + price);
                } else {
                    System.out.println("No book found with ID: " + id);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(); //

        }
        return null;
    }
    //feature 3
    public Book deleteBookByID(int id){
        String url = "jdbc:mysql://localhost:3306/bookshop";
        String userName = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            String sql = "DELETE FROM books WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Book with ID: " + id + " deleted successfully");
                } else {
                    System.out.println("No book found with ID: " + id);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(); //

        }
        return null;
    }
    //feature 4
    public void insertBook(int id, String title, String author, float price){
        String url = "jdbc:mysql://localhost:3306/bookshop";
        String userName = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            String sql = "INSERT INTO books (id, title, author, price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.setString(2, title);
                pstmt.setString(3, author);
                pstmt.setFloat(4, price);
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Book inserted successfully");
                } else {
                    System.out.println("Book not inserted");
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(); //

        }
    }
    //feature 5
    //update an existing entity by id
    public void updateBookByID(int id, String title, String author, float price){
        String url = "jdbc:mysql://localhost:3306/bookshop";
        String userName = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            String sql = "UPDATE books SET title = ?, author = ?, price = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, title);
                pstmt.setString(2, author);
                pstmt.setFloat(3, price);
                pstmt.setInt(4, id);
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Book updated successfully");
                } else {
                    System.out.println("No book found with ID: " + id);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(); //

        }
    }

    @Override
    public List<Book> getBookByFilter(Book filter) {
        return null;
    }

    //feature 6
    //get list of entities matching a filter based on DTO object
public List<Book> getBooksByFilter(Book filter){
        String url = "jdbc:mysql://localhost:3306/bookshop";
        String userName = "root";
        String password = "";
        List<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            String sql = "SELECT * FROM books WHERE title = ? AND author = ? AND price = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, filter.getTitle());
                pstmt.setString(2, filter.getAuthor());
                pstmt.setFloat(3, filter.getPrice());
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int bookId = rs.getInt("id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    float price = rs.getFloat("price");
                    Book book = new Book(bookId, title, author, price);
                    books.add(book);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(); //

        }
        return books;
    }

    //feature 7
    //Feature 7 - Convert List of Entities to a JSON String
    public String convertListToJSON(List<Book> books){
        StringBuilder json = new StringBuilder();
        json.append("[");
        for (Book book : books) {
            json.append("{");
            json.append("\"id\": " + book.getId() + ", ");
            json.append("\"title\": \"" + book.getTitle() + "\", ");
            json.append("\"author\": \"" + book.getAuthor() + "\", ");
            json.append("\"price\": " + book.getPrice());
            json.append("}, ");
        }
        json.delete(json.length() - 2, json.length());
        json.append("]");
        return json.toString();
    }

    //feature 8
    //Convert a single Entity by Key as a JSON String
    public String convertEntityToJSON(Book book){
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"id\": " + book.getId() + ", ");
        json.append("\"title\": \"" + book.getTitle() + "\", ");
        json.append("\"author\": \"" + book.getAuthor() + "\", ");
        json.append("\"price\": " + book.getPrice());
        json.append("}");
        return json.toString();
    }


}

