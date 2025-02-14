package application;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private ArrayList<Book> booksList = null;

    public Library() {
        booksList = new ArrayList<>();
        Book[] book = new Book[3];
        Scanner scan = new Scanner(System.in);
        String isbn = "";

        book[0] = Book.builder().authorFirstName("Anson").authorSurname("Ling").isbn("AB1111").publishYear(2001).build();
        book[1] = Book.builder().authorFirstName("Sebastian").authorSurname("Moyo").isbn("AB1122").publishYear(2001).build();
        book[2] = Book.builder().authorFirstName("Khalid").authorSurname("Slim").isbn("AB1133").publishYear(2001).build();
        booksList.add(book[0]);
        booksList.add(book[1]);
        booksList.add(book[2]);

        System.out.println("Please enter the isbn: ");
        isbn = scan.nextLine();

        int index = findIndexBook(isbn);
        System.out.println(index);

        try {
            //holding the current book that wish to loan
            Book book1 = loanBook(index);
            System.out.println("The loan book is " + loanBook(index));

            //remove the loan book from the collection
            booksList.remove(loanBook(index));

            //display the book list to check
            System.out.println("Book Detail: \n");
            for (Book book2: booksList)
                System.out.println(book2);

            //return the loan book
            returnBook(book1);

            //display the info
            System.out.println("Book Detail: \n");
            for (Book book2: booksList)
                System.out.println(book2);

            System.out.println("The book has been return!");
        } catch (Exception e) {
            System.out.println("The loan book is not found in the collection!");
        }
    }

    public int findIndexBook(String isbn) {
        for(int i = 0; i < booksList.size(); i++) {
            if(booksList.get(i).isbn().equals(isbn))
                return i;
        }
        return -1;
    }

    /**
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

    public void returnBook(Book book) {
        booksList.add(book);
    }

    public static void main(String[] args) {
        new Library();
    }
}
