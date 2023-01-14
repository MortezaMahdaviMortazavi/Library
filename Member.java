import java.util.Scanner;

public class Member {
    String name;
    String[] reservedBooks;
    int id;

    public Member(String name, int id, int libraryBooksNumber) {
        this.name = name;
        this.id = id;
        this.reservedBooks = new String[libraryBooksNumber];
    }

}
