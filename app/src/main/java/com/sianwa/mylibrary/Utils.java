package com.sianwa.mylibrary;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> favouriteBooks;


    private Utils() {
        if (null == allBooks) {
            allBooks = new ArrayList<>();
            initData();
        }

        if (null == currentlyReadingBooks) {
            currentlyReadingBooks = new ArrayList<>();
        }

        if (null == wantToReadBooks) {
            wantToReadBooks = new ArrayList<>();
        }

        if (null == favouriteBooks) {
            favouriteBooks = new ArrayList<>();
        }

        if (null == alreadyReadBooks) {
            alreadyReadBooks = new ArrayList<>();
        }
    }

    private void initData() {
        allBooks.add(new Book(1, "1q84", "Haruku Maham",
                1300, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBQZSg-b2LFkLlt9fWndS1w8SONabDZBHf0dtdb3-bqcuKxduL", "A wrork of maddening brilliance", "Long Description"));
        allBooks.add(new Book(2, "Harry Potter", "JK Rowling",
                1300, "data:image/jpeg;base64,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", "A wrork of maddening brilliance", "Long Description"));
    }

    public static Utils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public Book getBookById(int id) {
        for (Book b : allBooks) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book){
        return alreadyReadBooks.add(book);
    }

    public boolean addToWantToRead(Book book){
        return wantToReadBooks.add(book);
    }

    public boolean addToFavourites(Book book){
        return favouriteBooks.add(book);
    }

    public boolean addToCurrentlyReading(Book book){
        return currentlyReadingBooks.add(book);
    }

    public boolean removeFromAlreadyRead(Book book){return alreadyReadBooks.remove(book);}

    public boolean removeFromWantToRead(Book book){return wantToReadBooks.remove(book);}

    public boolean removeFromCurrentlyReading(Book book){return currentlyReadingBooks.remove(book);}

    public boolean removeFromFavourites(Book book){return favouriteBooks.remove(book);}

}
