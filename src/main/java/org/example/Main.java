package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class Main{
    InputStreamReader str;
    BufferedReader buff;
    int selectedOption;

    Library lb = new Library();
    public Main(){
        if(str == null){
            str = new InputStreamReader(System.in);
        }
        if(buff == null){
            buff = new BufferedReader(str);
        }
    }
    public static void main(String args[]){
        String url = System.getenv("dbUrl");
        String pass = System.getenv("dbPass");
        String user = System.getenv("dbUser");
        try{
            Connection connection = DriverManager.getConnection(url,user,pass);
            System.out.println("Connected");
            //set and get data from database.
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        Main obj = new Main();
        while(true) {
            System.out.print("Welcome to White House Library!\nPlease Select:\n1. Add Book\n2. Find Book\n3. Update Book\n4. Delete Book\n");
            try {
                obj.selectedOption = Integer.parseInt(obj.buff.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            obj.Option();
        }
    }

    //Input method
    public String getInput() {

        try{
                return buff.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }

    //printArrayList
    public void printArrayList(ArrayList<Book> b)
    {
        if(b.size() == 0) System.out.println("Oops! No books found");
        else {
            for (int i = 0; i < b.size(); i++) {
                System.out.print(Integer.toString(i+1)+".\n{\nBook name: " + b.get(i).bookName + "\nBook Author: " + b.get(i).bookAuthor + "\nBook Price: " + b.get(i).price + "\n}\n");
            }
        }
    }

    //print object
    public void printObj(Book b){
        System.out.print("Book Name: "+b.bookName+"\n"+"Book Author: "+b.bookAuthor+"\nBook Category: "+b.bookCategory+"\n"+"Book Price: "+b.price+"\n");
    }

    //option method
    public void Option(){
        switch (selectedOption){
            case 1:
                String name, author, category;
                float price;
                System.out.println("Enter book name ");
                name = getInput();
                System.out.println("Enter book author's name ");
                author = getInput();
                System.out.println("Enter book category ");
                category = getInput();
                System.out.println("Enter book price ");
                price = Float.parseFloat(getInput());

                lb.addBook(name, author, category, price);
                System.out.println("Book Added Sucessfully!");
                break;
            case 2:
                findOptions();
                break;
            case 3:
                updateOptions();
                break;
            case 4:
                deleteOptions();
                break;
            case 5:
                break;
            default:
                System.out.print("Invalid Input!");
        }
    }

    //find methods
    public void findOptions(){
        System.out.print("Select find method\n1. Find by book name\n2. Find by author's name\n3. Find by category\n4. Find by price\n");
        int x = Integer.parseInt(getInput());
        switch(x){
            case 1:
                System.out.println("Enter name ");
                printArrayList(lb.findByName(getInput()));
                break;
            case 2:
                System.out.println("Enter author's name ");
                printArrayList(lb.findByAuthor(getInput()));
                break;
            case 3:
                System.out.println("Enter book category ");
                printArrayList(lb.findByCategory(getInput()));
                break;
            case 4:
                System.out.println("Enter book price ");
                printArrayList(lb.findByprice(Float.parseFloat(getInput())));
                break;
            default:
                System.out.print("Invalid Input!");
        }
    }


    //Update options
    public void updateOptions() {
        System.out.print("Select update method\n1. Find by book name and update\n2. Find by author's name and update\n3. Find by category and update\n4. Find by price and update\n");
        int x = Integer.parseInt(getInput());
        switch(x){
            case 1:
                System.out.println("Enter name ");
                ArrayList<Book> filteredBook1 = lb.findByName(getInput());
                if(!filteredBook1.isEmpty()) {
                    System.out.print("Select Book\n");
                    printArrayList(filteredBook1);
                    int y = Integer.parseInt(getInput());
                    propToUpdate(filteredBook1.get(y-1));
                }
                else System.out.println("Oops! No books found.");
                break;
            case 2:
                System.out.println("Enter author's name ");
                ArrayList<Book> filteredBook2 = lb.findByAuthor(getInput());
                if(!filteredBook2.isEmpty()) {
                    System.out.print("Select Book\n");
                    printArrayList(filteredBook2);
                    int y = Integer.parseInt(getInput());
                    propToUpdate(filteredBook2.get(y-1));
                }
                else System.out.println("Oops! No books found.");
                break;
            case 3:
                System.out.println("Enter book category ");
                ArrayList<Book> filteredBook3 = lb.findByCategory(getInput());
                if(!filteredBook3.isEmpty()) {
                    System.out.print("Select Book\n");
                    printArrayList(filteredBook3);
                    int y = Integer.parseInt(getInput());
                    propToUpdate(filteredBook3.get(y-1));
                }
                else System.out.println("Oops! No books found.");
                break;
            case 4:
                System.out.println("Enter book price ");
                ArrayList<Book> filteredBook4 = lb.findByprice(Float.parseFloat(getInput()));
                if(!filteredBook4.isEmpty()) {
                    System.out.print("Select Book\n");
                    printArrayList(filteredBook4);
                    int y = Integer.parseInt(getInput());
                    propToUpdate(filteredBook4.get(y-1));
                }
                else System.out.println("Oops! No books found.");
                break;
            default:
                System.out.print("Invalid Input!");
        }
    }
    //properties to update
    public void propToUpdate(Book b){
        System.out.println("Select property to update.\n1. Book name\n2. Author's name\n3. Book Category\n4. Price\n");
        int x = Integer.parseInt(getInput());
        switch (x){
            case 1:
                System.out.println("Enter name: ");
                String name = getInput();
                lb.updateName(b, name);
                System.out.println("Updated successfully!");
                printObj(b);
                break;
            case 2:
                System.out.println("Enter author's name: ");
                String authorName = getInput();
                lb.updateAuthor(b, authorName);
                System.out.println("Updated successfully!");
                printObj(b);
                break;
            case 3:
                System.out.println("Enter category: ");
                String category = getInput();
                lb.updateCategory(b, category);
                System.out.println("Updated successfully!");
                printObj(b);
                break;
            case 4:
                System.out.println("Enter price: ");
                float price = Float.parseFloat(getInput());
                lb.updatePrice(b, price);
                System.out.println("Updated successfully!");
                printObj(b);
                break;
            default:
                System.out.println("Invalid Input!");
        }
    }

    //delete options
    public void deleteOptions(){
        System.out.print("Select update method\n1. Find by book name and delete\n2. Find by author's name and delete\n3. Find by category and delete\n4. Find by price and delete\n");
        int x = Integer.parseInt(getInput());
        switch(x){
            case 1:
                System.out.println("Enter name ");
                ArrayList<Book> filteredBook1 = lb.findByName(getInput());
                if(!filteredBook1.isEmpty()) {
                    System.out.print("Select Book\n");
                    printArrayList(filteredBook1);
                    int y = Integer.parseInt(getInput());
                    lb.delete(filteredBook1.get(y-1));
                    System.out.println("Deleted Successfully!");
                }
                else System.out.println("Oops! No books found.");
                break;
            case 2:
                System.out.println("Enter author's name ");
                ArrayList<Book> filteredBook2 = lb.findByAuthor(getInput());
                if(!filteredBook2.isEmpty()) {
                    System.out.print("Select Book\n");
                    printArrayList(filteredBook2);
                        int y = Integer.parseInt(getInput());
                    lb.delete(filteredBook2.get(y-1));
                    System.out.println("Deleted Successfully!");
                }
                else System.out.println("Oops! No books found.");
                break;
            case 3:
                System.out.println("Enter book category ");
                ArrayList<Book> filteredBook3 = lb.findByCategory(getInput());
                if(!filteredBook3.isEmpty()) {
                    System.out.print("Select Book\n");
                    printArrayList(filteredBook3);
                    int y = Integer.parseInt(getInput());
                    lb.delete(filteredBook3.get(y-1));
                    System.out.println("Deleted Successfully!");
                }
                else System.out.println("Oops! No books found.");
                break;
            case 4:
                System.out.println("Enter book price ");
                ArrayList<Book> filteredBook4 = lb.findByprice(Float.parseFloat(getInput()));
                if(!filteredBook4.isEmpty()) {
                    System.out.print("Select Book\n");
                    printArrayList(filteredBook4);
                    int y = Integer.parseInt(getInput());
                    lb.delete(filteredBook4.get(y-1));
                    System.out.println("Deleted Successfully!");
                }
                else System.out.println("Oops! No books found.");
                break;
            default:
                System.out.print("Invalid Input!");
        }
    }
}