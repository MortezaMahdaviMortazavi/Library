import java.util.Scanner;

class Person{
    String name;
    String[] booksThatBorrowed = new String[100];
    int numberOfBorrowing;
    boolean isInLibrary;

    Person(String name){
        this.name=name;
        numberOfBorrowing=0;
    }
    void borrow(String bookName){
        booksThatBorrowed[numberOfBorrowing]=bookName;
        numberOfBorrowing++;
        
    }
    void returnBook(String bookName){
        for (int i=0; i<numberOfBorrowing; i++){
            if (booksThatBorrowed[i].equals(bookName)){
                booksThatBorrowed[i]=booksThatBorrowed[numberOfBorrowing-1];
                break;
            }
        }
        numberOfBorrowing--;
    }

    void changePositionOfBeingInLibrary(){
        isInLibrary=!isInLibrary;
    }
}

class Book{
    String name = new String();
    String[] personsThatBorrowedThis = new String[100];
    int count;
    int borrowed;
    
    Book(String name){
        this.name=name;
        count=1;
    }   
    void add(){
        count++;
    }
    void remove(){
        count--;
    }

    void returnBook(String personName){
        for (int i=0; i<borrowed; i++){
            if (personsThatBorrowedThis[i].equals(personName)){
                personsThatBorrowedThis[i]=personsThatBorrowedThis[borrowed-1];
                break;
            }
        }
        borrowed--;
    }
    void borrow(String personName){
        personsThatBorrowedThis[borrowed]=personName;
        borrowed++;
    }
}

class BookNode{
    BookNode parent;
    BookNode[] children= new BookNode[27];
    Book book;
    char character;

    BookNode(char character, BookNode parent){
        this.character=character;
        this.parent=parent;
        this.book = new Book(setBookName());
    }

    String setBookName(){
        StringBuilder name = new StringBuilder();
        BookNode temp = this;
        while (temp.parent!=null){
            name.insert(0,temp.character);
            temp=temp.parent;
        }
        // Inverse the name 
        name = name.reverse();
        return name.toString().strip();
    }
}

class LibrariesBooks{
    BookNode root;

    LibrariesBooks(){
        root=new BookNode(' ',null);
        for (int i=0; i<27; i++){
            root.children[i]=new BookNode((char)(i+65),root);
        }
    }

    void addNewBook(Book book, int count){
        BookNode temp = root;
        for (int i=0; i<book.name.length(); i++){
            int index = book.name.charAt(i)-65;
            if (temp.children[index]==null){
                temp.children[index]=new BookNode(book.name.charAt(i),temp);
            }
            temp=temp.children[index];
        }
        temp.book.count += count;
        
    }

}

class PersonNode{
    PersonNode parent;
    PersonNode[] children= new PersonNode[27];
    Person person;
    char character;

    PersonNode(char character, PersonNode parent){
        this.character=character;
        this.parent=parent;
        this.person = new Person(setPersonName());
    }

    String setPersonName(){
        StringBuilder name = new StringBuilder();
        PersonNode temp = this;
        while (temp.parent!=null){
            name.insert(0,temp.character);
            temp=temp.parent;
        }
        // Inverse the name 
        name = name.reverse();
        return name.toString().strip();
    }
}

class LibrariesPeople{
    PersonNode root;
    LibrariesBooks librariesBooks;
    
    LibrariesPeople(){
        root=new PersonNode(' ',null);
        for (int i=0; i<27; i++){
            root.children[i]=new PersonNode((char)(i+65),root);
        }
    }

    void arrive(Person person){
        PersonNode temp = root;
        for (int i=0; i<person.name.length(); i++){
            int index = person.name.charAt(i)-65;
            if (temp.children[index]==null){
                temp.children[index]=new PersonNode(person.name.charAt(i),temp);
            }
            temp=temp.children[index];
        }
        if (!temp.person.isInLibrary){
            temp.person.changePositionOfBeingInLibrary();
        }

    }

    void exit(Person person){
        PersonNode temp = root;
        for (int i=0; i<person.name.length(); i++){
            int index = person.name.charAt(i)-65;
            if (temp.children[index]==null){
                temp.children[index]=new PersonNode(person.name.charAt(i),temp);
            }
            temp=temp.children[index];
        }
        if (temp.person.isInLibrary){
            temp.person.changePositionOfBeingInLibrary();
        }
    }

    void borrowBook(String personName, String bookName){
        PersonNode temp = root;
        for (int i=0; i<personName.length(); i++){
            int index = personName.charAt(i)-65;
            if (temp.children[index]==null){
                temp.children[index]=new PersonNode(personName.charAt(i),temp);
            }
            temp=temp.children[index];
        }
        if (temp.person==null){
            temp.person=new Person(personName);
        }
        if (!temp.person.isInLibrary){
            System.out.println("The person is not in the library");
            return;
        }
        BookNode temp2 = librariesBooks.root;
        for (int i=0; i<bookName.length(); i++){
            int index = bookName.charAt(i)-65;
            if (temp2.children[index]==null){
                temp2.children[index]=new BookNode(bookName.charAt(i),temp2);
            }
            temp2=temp2.children[index];
        }
        if (temp2.book.count - temp2.book.borrowed == 0){
            System.out.println("The book is not available");
            return;
        }
        temp.person.borrow(temp2.book.name);
        temp2.book.borrow();
        // assign the book to the person
        
    }
    void returnBook(String personName, String bookName){
        PersonNode temp = root;
        for (int i=0; i<personName.length(); i++){
            int index = personName.charAt(i)-65;
            if (temp.children[index]==null){
                temp.children[index]=new PersonNode(personName.charAt(i),temp);
            }
            temp=temp.children[index];
        }
        if (temp.person==null){
            temp.person=new Person(personName);
        }
        if (!temp.person.isInLibrary){
            System.out.println("The person is not in the library");
            return;
        }
        BookNode temp2 = librariesBooks.root;
        for (int i=0; i<bookName.length(); i++){
            int index = bookName.charAt(i)-65;
            if (temp2.children[index]==null){
                temp2.children[index]=new BookNode(bookName.charAt(i),temp2);
            }
            temp2=temp2.children[index];
        }
        if (temp2.book.count - temp2.book.borrowed == 0){
            System.out.println("The book is not available");
            return;
        }
        temp.person.returnBook();
        temp2.book.borrow();
    }

}
