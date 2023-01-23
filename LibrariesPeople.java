import java.util.Scanner;
import java.util.concurrent.TimeUnit;






public class LibrariesPeople{
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
        // measure time
        float startTime = System.nanoTime();
        temp.person.arriveTimes[temp.person.numberOfBorrowing] = startTime;
        

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
        // measure time
        float endTime = System.nanoTime();
        temp.person.exitTimes[temp.person.numberOfBorrowing] = endTime;
        
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
        if (temp2.book.count == 0){
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
        if (temp2.book.count== 0){
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
        float totalTime = 0;
        // calculate totalTime in log(n) order
        
        // int midIndex = temp.person.arriveTimes.length/2;
        // while (true){
        //     if (temp.person.arriveTimes[midIndex] < startTime){
        //         if (temp.person.exitTimes[midIndex] <= endTime){
        //             totalTime += temp.person.exitTimes[midIndex] - startTime;
        //         }
        //         else{
        //             totalTime += endTime - startTime;
        //         }
        //         midIndex++;
        //     }

        //     if (temp.person.arriveTimes[midIndex] > startTime){
        //         if (temp.person.exitTimes[midIndex] <= endTime){
        //             totalTime += temp.person.exitTimes[midIndex] - temp.person.arriveTimes[midIndex];
        //         }
        //         else{
        //             totalTime += endTime - temp.person.arriveTimes[midIndex];
        //         }
        //         midIndex--;
        //     }
        // }
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
        if (temp2.book.count== 0){
            System.out.println("The book is not available");
            return;
        }
         // we give the book to the person so we decrease the count of the book
        
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
        temp.book.printAllCurrentPerson();
    }

    void allPersonsBorrowedThisBook(){
        // print all books with inorder
        librariesBooks.root.book.printAllPerson();

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
