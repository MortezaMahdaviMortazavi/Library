import java.util.Scanner;

class Person{
    String name;
    BinaryTree binaryTree;
    int numberOfBorrowing;
    int numberOfAllTimeBooks;
    float[] arriveTimes;
    float[] exitTimes;
    boolean isInLibrary;


    Person(String name){
        this.name=name;
        binaryTree=new BinaryTree();
        numberOfBorrowing=0;
        numberOfAllTimeBooks=0;
        isInLibrary=false;
        arriveTimes=new float[100];
        exitTimes=new float[100];
        
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