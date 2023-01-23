import java.util.Scanner;


public class BookNode{
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