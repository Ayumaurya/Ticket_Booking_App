package org.example;

import java.util.ArrayList;

public interface Stock{
    public void addBook(String name, String author, String category, float price);
    public ArrayList<Book> findByName(String name);
    public ArrayList<Book> findByAuthor(String author);
    public ArrayList<Book> findByprice(float price);
    public ArrayList<Book> findByCategory(String category);
    public ArrayList<Book> getLowest();
    public ArrayList<Book> getHeighest();
    public void getByPriceRange();
    public void updateName(Book b, String name);
    public void updateAuthor(Book b, String authorName);
    public void updateCategory(Book b, String category);
    public void updatePrice(Book b, float price);
    public void delete(Book b);
}