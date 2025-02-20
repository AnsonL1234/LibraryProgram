package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    /** 1.1 A instance variable that will store in the collection - In this case, I will use ArrayList */
    private List<Book> booksList = null;

    public Library(Book book) {
        booksList = new ArrayList<Book>();
        this.booksList.add(book);
    }

    /**
     * 1.2 function method that find the book by index
     */
    public int findIndexBook(String isbn) {
        for(int i = 0; i < booksList.size(); i++) {
            if(booksList.get(i).isbn().equals(isbn))
                return i;
        }
        return -1;
    }

    /**
     * 1.3
     * This is the object function method that return the loan book value
     * return if the book is found in the location
     * return null otherwise
     */
    public Book loanBook(int bookIndex) {
        Book book = null;
        for (int i = booksList.size() - 1; i >= 0; i--) {
            book = booksList.get(bookIndex);
            if (booksList.get(i).equals(book)) {
                return book;
            }
        }
        return null;
    }

    /**
     * 1.4 pure method for return the book by the Book object parameters
     */
    public void returnBook(Book book) {
        booksList.add(book);
    }

    /**
     * 1.5 Create library object that will store the hardcore value and create two member object that run both thread
     */
    public static void main(String[] args) {
        Library myLibrary = null;
        Book book = Book.builder()
                .authorFirstName("Guang Cheng")
                .authorSurname("Anson Ling")
                .isbn("AB11228")
                .publishYear(2001)
                .build();

        myLibrary = new Library(book);

        Member member1 = new Member(myLibrary);
        Member member2 = new Member(myLibrary);
        member1.start();
        member2.start();

    }
}
