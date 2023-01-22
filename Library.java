import java.util.Scanner;

class BinaryTree {
    class Node{
        Node left;
        Node right;
        Node parent;
        String data;

        Node(String data){
            this.data = data;
            left = null;
            right = null;
            parent = null;
        }
    }
    Node root;
    
    BinaryTree(){
        root = null;
    }

    boolean morePrior(String bookName1, String bookName2){
        int i = 0;
        while (i < bookName1.length() && i < bookName2.length()){
            if (bookName1.charAt(i) > bookName2.charAt(i)){
                return true;
            }else if (bookName1.charAt(i) < bookName2.charAt(i)){
                return false;
            }
            i++;
        }
        return bookName1.length() > bookName2.length();
    }

    void insert(String data){
        root = insert(root, data);
    }

    Node insert(Node root, String data){
        if (root == null){
            root = new Node(data);
            return root;
        }
        if (morePrior(data, root.data)){
            root.left = insert(root.left, data);
            root.left.parent = root;
        }else{
            root.right = insert(root.right, data);
            root.right.parent = root;
        }
        return root;
    }

    void replace(Node node , Node child){
        if (node.parent == null){
            root = child;
        }else if (node.parent.left == node){
            node.parent.left = child;
        }else{
            node.parent.right = child;
        }
        if (child != null){
            child.parent = node.parent;
        }
    }

    void delete(String data){
        root = delete(root, data);
    }

    Node delete(Node root, String data){
        if (root == null){
            return root;
        }
        if (root.data.equals(data)){
            if (root.left == null){
                replace(root, root.right);
            }else if (root.right == null){
                replace(root, root.left);
            }else{
                Node temp = root.right;
                while (temp.left != null){
                    temp = temp.left;
                }
                root.data = temp.data;
                delete(root.right, temp.data);
            }
        }else if (morePrior(data, root.data)){
            delete(root.left, data);
        }else{
            delete(root.right, data);
        }
        return root;
    }

    void inorder(Node root){
        if (root != null){
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }
}

class Person{
    String name;
    BinaryTree binaryTree;
    int numberOfBorrowing;
    int numberOfAllTimeBooks;
    int[] timesThatBeingInLibrary = new int[100];
    boolean isInLibrary;


    Person(String name){
        this.name=name;
        numberOfBorrowing=0;
        numberOfAllTimeBooks=0;
    }
    void borrow(String bookName){
        binaryTree.insert(bookName);
        numberOfBorrowing++;
        numberOfAllTimeBooks++;
    }
    void returnBook(String bookName){
        binaryTree.delete(bookName);
        numberOfBorrowing--;
    }

    void changePositionOfBeingInLibrary(){
        isInLibrary=!isInLibrary;
    }
    public void printAllBooks() {
        System.out.println("All books of " + name + " are: ");
        binaryTree.inorder(binaryTree.root);
        System.out.println();
    }
    public int allTimeBooks() {
        return numberOfAllTimeBooks;
    }
    public String totalTimeInLibrary(float startTime, float endTime) {
        int totalTime = 0;
        for (int i = 0; i < timesThatBeingInLibrary.length; i++){
            totalTime += timesThatBeingInLibrary[i];
        }
        return "Total time of " + name + " in library is: " + totalTime + " minutes";
    }
}

class Book{
    String name = new String();
    BinaryTree binaryTree;
    
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
        binaryTree.delete(personName);
        borrowed--;
    }
    void borrow(String personName){
        binaryTree.insert(personName);
        borrowed++;
    }
    public void printAllPerson() {
        System.out.println("All persons who borrowed " + name + " are: ");
        binaryTree.inorder(binaryTree.root);
        System.out.println();
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
        temp2.book.borrow(temp.person.name);
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
        temp.person.returnBook(temp2.book.name);
        temp2.book.borrow(temp.person.name);
    }

    void isinLib(String personName){
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
        if (temp.person.isInLibrary){
            System.out.println("The person is in the library");
        }else{
            System.out.println("The person is not in the library");
        }
    }

    void TotalTimeInLib(String personName,float startTime,float endTime){
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
        System.out.println("The total time is "+temp.person.totalTimeInLibrary(startTime,endTime));
    }

    void shouldBring(String personName,String bookName){
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
        temp2.book.count-=1; // we give the book to the person so we decrease the count of the book
        
    }

    void allPersonCurrentBooks(String personName){
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
        // print all books with inorder
        temp.person.printAllBooks();
    }

    void allPersonHaveThisBook(String bookName){
        BookNode temp = librariesBooks.root;
        for (int i=0; i<bookName.length(); i++){
            int index = bookName.charAt(i)-65;
            if (temp.children[index]==null){
                temp.children[index]=new BookNode(bookName.charAt(i),temp);
            }
            temp=temp.children[index];
        }
        if (temp.book==null){
            temp.book=new Book(bookName);
        }
        // print all books with inorder
        temp.book.printAllPerson();
    }

    void allPersonsBorrowedThisBook(){

    }
    int allPersonAllTimeBooks(String personName){
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

        return temp.person.allTimeBooks();
        
    }

}

public class Library {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}