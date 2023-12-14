package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Library implements Stock {

    ArrayList<Book> bookList = new ArrayList<>();
    //database




    @Override
    public void addBook(String name, String author, String category, String price, Connection connection) {
        Book b = new Book(name, price, category, author);
        try {
            PreparedStatement pStmt = connection.prepareStatement("insert into books (bookName, bookCategory, bookAuthor, bookPrice) values (?,?,?,?)");
            pStmt.setString(1, b.getName());
            pStmt.setString(2, b.getCategory());
            pStmt.setString(3, b.getAuthorName());
            pStmt.setString(4, b.getPrice());
            // METHOD FOR EXECUTING THE SQL QUERY
            pStmt.executeUpdate();//Adding new to library
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Book> find(String name, String prop, Connection connection) {
        ArrayList<Book> result = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from books where "+prop+"='"+name+"'");
            while(rs.next()) {
                Book book1 = new Book();
                book1.setId(rs.getInt(1));
                book1.setName(rs.getString(2));
                book1.setCategory(rs.getString(3));
                book1.setAuthor(rs.getString(4));
                book1.setPrice(rs.getString(5));
                result.add(book1);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }


    @Override
    public ArrayList<Book> getLowest() {
        ArrayList<Book> result = new ArrayList<>();
        if(bookList.isEmpty()) return result;
        else {
            result.add(bookList.get(0));
            for(int i=1; i<bookList.size(); i++){
                if(bookList.get(i).price.equals(result.get(i-1).price)){
                    result.add(bookList.get(i));
                }
                else if(Float.parseFloat(result.get(i-1).price) > Float.parseFloat(bookList.get(i).price)){
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
                if(bookList.get(i).price.equals(result.get(i-1).price)){
                    result.add(bookList.get(i));
                }
                else if(Float.parseFloat(result.get(i-1).price) < Float.parseFloat(bookList.get(i).price)){
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
    public void update(Book b, String prop, String name, Connection connection) {
        b.bookName = name;
        try {
            Statement stmt = connection.createStatement();
//            System.out.println("update books set " + prop + "='"+ name+"' where id="+b.id);
            stmt.executeUpdate("update books set " + prop + "='"+ name+"' where id="+b.id);
        }catch(Exception e){
            System.out.println(e);
        }
    }


    @Override
    public void delete(Book b, Connection connection) {
        try {
            Statement stmt = connection.createStatement();
//            System.out.println("update books set " + prop + "='"+ name+"' where id="+b.id);
            stmt.executeUpdate("delete from books where id="+b.id);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
