package org.example;
import java.sql.*;

/*
INSERT INTO books (id, title, author, price) VALUES
    (1, 'The Da Vinci Code', 'Dan Brown', 15.00),
    (2, 'Le Petit Prince', 'Antoine de Saint-Exupery', 29.00),
    (3, 'The Hobbit', 'J.R.R. Tolkien', 20.00),
    (4, 'And Then There Were None', 'Agatha Christie', 10.00),
    (5, 'Dream of the Red Chamber', 'Cao Xueqin', 25.00),
    (6, 'The Lion, the Witch and the Wardrobe', 'C.S. Lewis', 12.00),
    (7, 'She: A History of Adventure', 'H. Rider Haggard', 18.00),
    (8, 'The Adventures of Pinocchio', 'Carlo Collodi', 22.00),
    (9, 'The Catcher in the Rye', 'J.D. Salinger', 17.00),
    (10, 'The Alchemist', 'Paulo Coelho', 14.00);
 */
public class Books {
    public void start(){
        String url = "jdbc:mysql://localhost/";
        String dbName = "bookshop";
        String userName = "root";
        String password = "";

        try(Connection conn = DriverManager.getConnection(url + dbName, userName, password))
        {

            Statement stmt = conn.createStatement();





        } catch (SQLException e) {
            //error message
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();

        }
    }



}
