package application;

import java.util.Scanner;

public class Member extends Thread{

    private Library library = null;
    private String isbn = "AB11228";

    public Member(Library library) {
        this.library = library;
    }
//System.out.println();
    public void run() {
        /** 3.A. Call the findIndexBook */
        int index = library.findIndexBook(this.isbn);

        Book book = null;

        //check to see is the book found
        if (index < 0) {
            System.out.println("The book you wish to find is not found!");
        } else {

            /** 3.B. Call the loan book */
            book = library.loanBook(index);

            /** 3.C. Sleep for 2000 millis and call return book */
            try {
                Thread.sleep(2000);
                System.out.println("The book you wish to find is found!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            library.returnBook(book);
            System.out.println("The book you loan is return!");
        }
    }
}

class Repository {
    private int integer;
    private boolean isNotify = false;

    public Repository() {
        this.integer = 0;
    }

    synchronized int getInteger() {
        return this.integer;
    }

    synchronized void setInteger(int n) {
        this.integer = n;
    }

    synchronized boolean isNotify() {
        return this.isNotify;
    }

    synchronized void setIsNotify(boolean isNotify) {
        this.isNotify = isNotify;
    }

    public String toString() {
        return "The number: " + this.integer;
    }
}

class Counter extends Thread{
    Repository repository;

    public Counter(Repository repository) {
        this.repository = repository;
    }

    public void run() {
        synchronized (this) {
            try {
                repository.wait();
                r
//                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 1; i <= 5; i++) {
                repository.setInteger(i);
                System.out.println("Continuous count " + i);
            }
        }
    }
}

class Publish extends Thread {
    Repository repository;

    public Publish(Repository repository) {
        this.repository = repository;
    }

    public void run() {
        for (int i = 1; i <= repository.getInteger(); i++)
            System.out.println("Print table " + i);

        repository.notify();


//                try {
//
//    //             Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

    }
}

class Program {

    public static void main(String[] args) {
        Repository repository = new Repository();
        Counter counter = new Counter(repository);
        Publish publish = new Publish(repository);
        counter.start();
        publish.start();
    }
}
