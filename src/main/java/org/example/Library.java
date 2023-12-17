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
    public void addBook(String name, String author, String category, String price, String year,Connection connection) {
        Book b = new Book(name.toLowerCase(), price, category.toLowerCase(), author.toLowerCase(), year);
        try {
            PreparedStatement pStmt = connection.prepareStatement("insert into books (bookName, bookCategory, bookAuthor, bookPrice, bookPublished) values (?,?,?,?,?)");
            pStmt.setString(1, b.getName());
            pStmt.setString(2, b.getCategory());
            pStmt.setString(3, b.getAuthorName());
            pStmt.setFloat(4, Float.parseFloat(b.getPrice()));
            pStmt.setFloat(5, Integer.parseInt(b.getYear()));
            // METHOD FOR EXECUTING THE SQL QUERY
            pStmt.executeUpdate();//Adding new to library
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Book> find(String name, String prop, Connection connection) {
        ArrayList<Book> result = new ArrayList<>();
        ResultSet rs;
        try{
            Statement stmt = connection.createStatement();
            if(prop.equals("bookPrice"))
            rs = stmt.executeQuery("select * from books where "+prop+"='"+Float.parseFloat(name)+"'");
            else if(prop.equals("bookPrice")) rs = stmt.executeQuery("select * from books where "+prop+"='"+Integer.parseInt(name)+"'");
            else
                //System.out.println("select * from books where "+prop+" like '%"+name.toLowerCase()+"%'");
                rs = stmt.executeQuery("select * from books where "+prop+" like '%"+name.toLowerCase()+"%'");
            while(rs.next()) {
                Book book1 = new Book();
                book1.setId(rs.getInt(1));
                book1.setName(rs.getString(2));
                book1.setCategory(rs.getString(3));
                book1.setAuthor(rs.getString(4));
                book1.setPrice(String.valueOf(rs.getString(5)));
                result.add(book1);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }


    @Override
    public ArrayList<Book> getCheapest(Connection connection) {
        ArrayList<Book> result = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from books where bookPrice = (SELECT MIN(bookPrice) FROM books)");
            while(rs.next()) {
                Book book1 = new Book();
                book1.setId(rs.getInt(1));
                book1.setName(rs.getString(2));
                book1.setCategory(rs.getString(3));
                book1.setAuthor(rs.getString(4));
                book1.setPrice(String.valueOf(rs.getFloat(5)));
                result.add(book1);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public ArrayList<Book> getCostliest(Connection connection) {
        ArrayList<Book> result = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from books where bookPrice = (SELECT MAX(bookPrice) FROM books)");
            while(rs.next()) {
                Book book1 = new Book();
                book1.setId(rs.getInt(1));
                book1.setName(rs.getString(2));
                book1.setCategory(rs.getString(3));
                book1.setAuthor(rs.getString(4));
                book1.setPrice(String.valueOf(rs.getFloat(5)));
                result.add(book1);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public ArrayList<Book> getByPriceRange(Float min, Float max, Connection connection) {
        ArrayList<Book> result = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from books where bookPrice <= "+max+"&& bookPrice >= "+min);
            while(rs.next()) {
                Book book1 = new Book();
                book1.setId(rs.getInt(1));
                book1.setName(rs.getString(2));
                book1.setCategory(rs.getString(3));
                book1.setAuthor(rs.getString(4));
                book1.setPrice(String.valueOf(rs.getFloat(5)));
                result.add(book1);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public void update(Book b, Connection connection) {
        try {
            Statement stmt = connection.createStatement();
//            System.out.println("update books set " + prop + "='"+ name+"' where id="+b.id);
                stmt.executeUpdate("update books set bookName = '"+b.bookName+"', bookCategory = '"+b.bookCategory+"', bookAuthor = '"+b.bookAuthor+"', bookPrice = "+Float.parseFloat(b.price)+" where id="+b.id);
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
    public void deleteAll() {
        try {
            Statement stmt = dbUtils.connect().createStatement();
//            System.out.println("update books set " + prop + "='"+ name+"' where id="+b.id);
            stmt.executeUpdate("delete from books");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
