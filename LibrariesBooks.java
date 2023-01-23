import java.util.Scanner;


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
            // determine space
            if (book.name.charAt(i)==' '){
                index=26;
            }
            if (temp.children[index]==null){
                temp.children[index]=new BookNode(book.name.charAt(i),temp);
            }
            temp=temp.children[index];
        }
        temp.book.count += count;
        
    }

}