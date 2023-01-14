import java.util.Scanner;

public class Book {
    String name;
    String memberWhoBorrowedThis;
    String[] allMembersWhoBorrowedThis;

    public Book(String name, int libraryMembersNumber) {
        this.name = name;
        this.memberWhoBorrowedThis = null;
        this.allMembersWhoBorrowedThis = new String[libraryMembersNumber];
    }

    private boolean isBorrowed() {
        return this.memberWhoBorrowedThis != null;
    }

    private void borrow(String memberName) {
        this.memberWhoBorrowedThis = memberName;
    }

    private void returnBook() {
        this.memberWhoBorrowedThis = null;
    }
}
