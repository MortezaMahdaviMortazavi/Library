import java.util.Scanner;

public class Library {
    Row[] rows;
    Book[] allBookOfLibrary;

    public Library(int numberOfRows, int numberOfShelves, int numberOfBooks) {
        this.rows = new Row[numberOfRows];
        for (int i = 0; i < numberOfRows; i++) {
            this.rows[i] = new Row(numberOfShelves);
            for (int j = 0; j < numberOfShelves; j++) {
                this.rows[i].shelves[j] = new Shelf(numberOfBooks);
                for (int k = 0; k < numberOfBooks; k++) {
                    this.rows[i].shelves[j].books[k] = new Book("Book " + k, 100);
                }
            }
        }
        this.allBookOfLibrary = new Book[numberOfRows * numberOfShelves * numberOfBooks];
        int index = 0;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfShelves; j++) {
                for (int k = 0; k < numberOfBooks; k++) {
                    this.allBookOfLibrary[index] = this.rows[i].shelves[j].books[k];
                    index++;
                }
            }
        }
    }

    class Shelf {
        Book[] books;

        public Shelf(int size) {
            this.books = new Book[size];
        }
    }

    class Row {
        Shelf[] shelves;

        public Row(int size) {
            this.shelves = new Shelf[size];
        }

    }

    class LibrarySystem {
        Member[] allMembers;
        Member[] currentMembersInLibrary;
        int numberOfMembers;
        int numberOfCurrentMembers;

        public LibrarySystem(int numberOfMembers) {
            this.numberOfMembers = numberOfMembers;
            this.allMembers = new Member[numberOfMembers];
            // initial all members
            for (int i = 0; i < numberOfMembers; i++) {
                this.allMembers[i] = new Member("Member " + i, i, Library.this.allBookOfLibrary.length);
            }
            this.currentMembersInLibrary = new Member[numberOfMembers];
            this.numberOfCurrentMembers = 0;
        }

        // Function 1
        void arrive(String memberName) {
            for (int i = 0; i < this.numberOfMembers; i++) {
                if (this.allMembers[i].name.equals(memberName)) {
                    this.currentMembersInLibrary[this.numberOfCurrentMembers] = this.allMembers[i];
                    this.numberOfCurrentMembers++;
                    return;
                }
            }
            System.out.println("Member has not been registered");
        }

        void exit(String memberName) { // this function have a bug that should be fixed.prefer to bring members to
                                       // linkedlist
            for (int i = 0; i < this.numberOfCurrentMembers; i++) {
                if (this.currentMembersInLibrary[i].name.equals(memberName)) {
                    this.currentMembersInLibrary[i] = this.currentMembersInLibrary[this.numberOfCurrentMembers - 1];
                    this.numberOfCurrentMembers--;
                    return;
                }
            }
            System.out.println("Member isnt in library or has not been registered");
        }

        void isInLib(String memberName) {
            for (int i = 0; i < this.numberOfCurrentMembers; i++) {
                if (this.currentMembersInLibrary[i].name.equals(memberName)) {
                    System.out.println("Member is in library");
                    return;
                }
            }
            System.out.println("Member isnt in library or has not been registered");
        }

    }

}
