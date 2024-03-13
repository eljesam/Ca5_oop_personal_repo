package org.example;
import java.sql.*;
public class Books {
    public void start(){
        String url = "jdbc:myqsl://localhost/";
        String dbName = "bookshop";
        String userName = "root";
        String password = "";

        try(Connection conn = DriverManager.getConnection(url + dbName, userName, password))
        {
            //crate and populate the table
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE books (id INT, title VARCHAR(50), author VARCHAR(50), price FLOAT)");
            stmt.executeUpdate("INSERT INTO books (id, title, author, price) VALUES (1, 'The Da Vinci Code', 'Dan Brown', 15.00)");
            stmt.executeUpdate("INSERT INTO books (id, title, author, price) VALUES (2, 'Le Petit Prince', 'Antoine de Saint-Exupery', 29.00)");
            stmt.executeUpdate("INSERT INTO books (id, title, author, price) VALUES (3, 'The Hobbit', 'J.R.R. Tolkien', 20.00)");
            stmt.executeUpdate("INSERT INTO books (id, title, author, price) VALUES (4, 'And Then There Were None', 'Agatha Christie', 10.00)");
            stmt.executeUpdate("INSERT INTO books (id, title, author, price) VALUES (5, 'Dream of the Red Chamber', 'Cao Xueqin', 25.00)");
            stmt.executeUpdate("INSERT INTO books (id, title, author, price) VALUES (6, 'The Lion, the Witch and the Wardrobe', 'C.S. Lewis', 12.00)");
            stmt.executeUpdate("INSERT INTO books (id, title, author, price) VALUES (7, 'She: A History of Adventure', 'H. Rider Haggard', 18.00)");
            stmt.executeUpdate("INSERT INTO books (id, title, author, price) VALUES (8, 'The Adventures of Pinocchio', 'Carlo Collodi', 22.00)");
            stmt.executeUpdate("INSERT INTO books (id, title, author, price) VALUES (9, 'The Catcher in the Rye', 'J.D. Salinger', 17.00)");
            stmt.executeUpdate("INSERT INTO books (id, title, author, price) VALUES (10, 'The Alchemist', 'Paulo Coelho', 14.00)");




        } catch (SQLException e) {
            //error message
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();

        }
    }



}
