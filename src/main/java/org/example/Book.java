package org.example;

public class Book{
    String price;
    String bookName, bookCategory, bookAuthor, year;

    int id = 0;

    public Book(){

    }
    public Book(String bookName, String price, String bookCategory, String bookAuthor, String year){
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.bookAuthor = bookAuthor;
        this.price = price;
        this.year = year;
    }

    public void setName(String name){
        this.bookName = name;
    }
    public void setAuthor(String author){
        this.bookAuthor = author;
    }

    public void setCategory(String category){
        this.bookCategory = category;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public void setId(int i){
        this.id = i;
    }
    public void setYear(String year)
    {
        this.year = year;
    }
    public String getName(){
        if(!bookName.equals(null) || !bookName.equals("")) return this.bookName;
        else return "NA";
    }
    public String getAuthorName(){
        if(!bookCategory.equals(null) || !bookCategory.equals("")) return this.bookAuthor;
        else return "NA";
    }
    public String getCategory(){
        if(!bookAuthor.equals(null) || !bookAuthor.equals("")) return this.bookCategory;
        else return "NA";
    }
    public String getPrice(){
        if(!bookName.equals(null) || !bookName.equals("")) return this.price;
        else
            return "0f";
    }
    public String getYear()
    {
        if(!year.equals(null) || !year.equals("")) return this.year;
        else
            return "0f";
    }
}