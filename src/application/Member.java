package application;

public class Member extends Thread{

    private Library library = null;
    private String isbn = "AB11228";

    public Member(Library library) {
        this.library = library;
    }

    public void run() {
        /** 3.A. Call the findIndexBook method */
        int index = library.findIndexBook(this.isbn);

        Book book = null;

        //check if the book found
        if (index < 0) {
            System.out.println("The book you wish to find is not found!");
        } else {

            /** 3.B. Call the loan book method */
            book = library.loanBook(index);

            /** 3.C. Sleep for 2000 millis and call return book method */
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

/**
 * Question 2 - Part 1 - Note: Is update to question 3 that giving the new variable of boolean to check the condition
 *  - The repository class that store integer
 */
class Repository {
    private int integer;
    private boolean isPulish = false;

    public Repository() {
        this.integer = 0;
    }

    synchronized int getInteger() {
        return this.integer;
    }

    synchronized void setInteger(int n) {
        this.integer = n;
    }

    synchronized boolean isPulish() {
        return this.isPulish;
    }

    synchronized void setIsPulish(boolean isPulish) {
        this.isPulish = isPulish;
    }

    public String toString() {
        return "The number: " + this.integer;
    }
}

/**
 * Question 2 - Part 2 - Note: Is update to question 3 that implement synchronized with wait and notify
 *  - The Counter class should create a thread that starts counting from 0 (0, 1, 2, 3 ...) and
 *  - stores each value in the Repository class
 */
class Counter extends Thread{
    private final Repository repository;

    public Counter(Repository repository) {
        this.repository = repository;
    }

    public void run() {
        synchronized (repository) {

            for (int i = 1; i <= 5; i++) {
                repository.setInteger(i);
                System.out.println("Store value " + i);
            }

            repository.notify(); //after storing the value. notify to release
            repository.setIsPulish(true); // after notify set isPublish to true
        }
    }
}

/**
 * Question 2 - Part 3 - Note: Is update to question 3 that implement synchronized with wait and notify
 *  - The Publisher class should create a thread that keeps reading the value in the
 *  - Repository class and printing it
 */
class Publish extends Thread {
    private final Repository repository;

    public Publish(Repository repository) {
        this.repository = repository;
    }

    public void run() {
        synchronized (repository) {

            System.out.println("isNotify is now " + repository.isPulish() + "\n");

            try {
                repository.wait(); // told the method to wait until notify release
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("\nisNotify is now " + repository.isPulish() + "\n");

            //only allow to print when publish
            if(repository.isPulish()) {
                for (int i = 1; i <= repository.getInteger(); i++)
                    System.out.println("Print table " + i);
            }

        }
    }
}

class Program {

    public static void main(String[] args) {
        Repository repository = new Repository();
        Counter counter = new Counter(repository);
        Publish publish = new Publish(repository);

        publish.start();
        counter.start();
    }
}
