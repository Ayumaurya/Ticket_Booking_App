package org.example;

import java.sql.Connection;
import java.util.ArrayList;

public interface Stock{
    public void addBook(String name, String author, String category, String price, Connection connection);
    public ArrayList<Book> find(String name, String prop, Connection connection);

    public ArrayList<Book> getLowest();
    public ArrayList<Book> getHeighest();
    public void getByPriceRange();
    public void update(Book b, String prop, String name, Connection connection);
    public void delete(Book b);
}