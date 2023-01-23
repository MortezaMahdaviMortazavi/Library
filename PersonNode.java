import java.util.Scanner;


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