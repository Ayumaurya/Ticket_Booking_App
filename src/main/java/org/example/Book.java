package org.example;

public class Book{
    float price;
    String bookName, bookCategory, bookAuthor;
    public Book(String bookName, float price, String bookCategory, String bookAuthor){
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.bookAuthor = bookAuthor;
        this.price = price;
    }
}