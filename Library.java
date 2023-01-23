import java.util.Scanner;
public class Library {
    public static void main(String[] args) {
        LibrariesBooks librariesBooks = new LibrariesBooks();
        LibrariesPeople librariesPeople = new LibrariesPeople();
        Scanner scanner = new Scanner(System.in);
        // Book book = new Book("Harry Potter");
        // Book book2 = new Book("Lord of the Rings");
        Book book3 = new Book("THEHOBBIT");
        // Book book4 = new Book("TheLordoftheRings");
        // Book book5 = new Book("TheLordoftheRings");
        
        // librariesBooks.addNewBook(book, 1);
        // librariesBooks.addNewBook(book2, 1);
        librariesBooks.addNewBook(book3, 1);
        // librariesBooks.addNewBook(book4, 1);
        // librariesBooks.addNewBook(book5, 1);

        // Person person = new Person("Ali");
        // Person person2 = new Person("Veli");
        // Person person3 = new Person("Ayse");
        // Person person4 = new Person("Fatma");
        // Person person5 = new Person("Ali");

        // librariesPeople.arrive(person);
        // librariesPeople.arrive(person2);
        // librariesPeople.arrive(person3);
        // librariesPeople.arrive(person4);
        // librariesPeople.arrive(person5);

    


    }
}
