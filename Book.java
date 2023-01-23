import java.util.Scanner;


public class Book{
    String name = new String();
    BinaryTree binaryTree;
    BinaryTree wholeDataInHistory;
    int count;
    int borrowed;
    
    public Book(String name){
        this.name=name;
        count=1;
        binaryTree=new BinaryTree();
        wholeDataInHistory=new BinaryTree();
    }   
    void add(){
        count++;
    }
    void remove(){
        count--;
    }

    void isBorrow(){
        borrowed++;
    }
    void isReturn(){
        borrowed--;
    }

    void returnBook(String personName){
        binaryTree.delete(personName);
        add();
        isReturn();
    }
    void borrow(String personName){
        binaryTree.insert(personName);
        wholeDataInHistory.insert(personName);
        remove();
        isBorrow();
    }
    public void printAllCurrentPerson() {
        System.out.println("All persons who borrowed " + name + " are: ");
        binaryTree.inorder(binaryTree.root);
        System.out.println();
    }

    public void printAllPerson() {
        System.out.println("All persons who borrowed " + name + " are: ");
        wholeDataInHistory.inorder(wholeDataInHistory.root);
        System.out.println();
    }
}