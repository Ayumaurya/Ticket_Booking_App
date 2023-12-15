package org.example;

import java.sql.Connection;
import java.util.ArrayList;

public interface Stock{
    public void addBook(String name, String author, String category, String price, Connection connection);
    public ArrayList<Book> find(String name, String prop, Connection connection);

    public ArrayList<Book> getCheapest(Connection connection);
    public ArrayList<Book> getCostliest(Connection connection);
    public ArrayList<Book> getByPriceRange(Float min, Float max, Connection connection);
    public void update(Book b, Connection connection);
    public void delete(Book b, Connection connection);
}