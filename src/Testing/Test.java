package Testing;

import application.Book;

public class Test {

    public Test()
    {
        insertNewBook();
    }

    public void insertNewBook() {
        Book oi = Book.builder()
                .authorFirstName("Anson")
                .authorSurname("Ling")
                .isbn("AB1001")
                .publishYear(2001)
                .build();

        System.out.println(oi);
    }

    public static void main(String[] args) {
        new Test();
    }
}
