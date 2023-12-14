package org.example;

import java.util.ArrayList;

public class Library implements Stock {

    ArrayList<Book> bookList = new ArrayList<>();
    @Override
    public void addBook(String name, String author, String category, float price) {
        bookList.add(new Book(name, price, category, author)); //Adding new to library
    }

    @Override
    public ArrayList<Book> findByName(String name) {
        ArrayList<Book> result = new ArrayList<>();
        for(int i=0; i<bookList.size(); i++){ if(name.equals(bookList.get(i).bookName))
            result.add(bookList.get(i));}
        return result;
    }

    @Override
    public ArrayList<Book> findByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for(int i=0; i<bookList.size(); i++){ if(author.equals(bookList.get(i).bookAuthor))
            result.add(bookList.get(i));}
        return result;
    }

    @Override
    public ArrayList<Book> findByprice(float price) {
        ArrayList<Book> result = new ArrayList<>();
        for(int i=0; i<bookList.size(); i++){ if(price == (bookList.get(i).price))
            result.add(bookList.get(i));}
        return result;
    }

    @Override
    public ArrayList<Book> findByCategory(String category) {
        ArrayList<Book> result = new ArrayList<>();
        for(int i=0; i<bookList.size(); i++){ if(category.equals(bookList.get(i).bookCategory))
            result.add(bookList.get(i));}
        return result;
    }

    @Override
    public ArrayList<Book> getLowest() {
        ArrayList<Book> result = new ArrayList<>();
        if(bookList.isEmpty()) return result;
        else {
            result.add(bookList.get(0));
            for(int i=1; i<bookList.size(); i++){
                if(bookList.get(i).price == result.get(i-1).price){
                    result.add(bookList.get(i));
                }
                else if(result.get(i-1).price > bookList.get(i).price){
                    result.clear();
                    result.add(bookList.get(i));
                }
            }
            return result;
        }
    }

    @Override
    public ArrayList<Book> getHeighest() {
        ArrayList<Book> result = new ArrayList<>();
        if(bookList.isEmpty()) return result;
        else {
            result.add(bookList.get(0));
            for(int i=1; i<bookList.size(); i++){
                if(bookList.get(i).price == result.get(i-1).price){
                    result.add(bookList.get(i));
                }
                else if(result.get(i-1).price < bookList.get(i).price){
                    result.clear();
                    result.add(bookList.get(i));
                }
            }
            return result;
        }
    }

    @Override
    public void getByPriceRange() {

    }

    @Override
    public void updateName(Book b, String name) {
        b.bookName = name;
    }

    @Override
    public void updateAuthor(Book b, String authorName) {
        b.bookAuthor = authorName;
    }

    @Override
    public void updatePrice(Book b, float price) {
        b.price = price;
    }
    @Override
    public void updateCategory(Book b, String category){
        b.bookCategory = category;
    }

    @Override
    public void delete(Book b) {
        bookList.remove(b);
    }
}
