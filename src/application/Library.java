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
    }

    public int findIndexBook(String isbn) {
        for(int i = 0; i < booksList.size(); i++) {
            if(booksList.get(i).isbn().equals(isbn))
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        new Library();
    }
}
