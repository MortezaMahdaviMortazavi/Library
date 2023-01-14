import java.util.Scanner;

public class LinkedList {
    static class Node {
        String name; // can be book name or member name
        int id;
        Node next;
        Member member;

        Node(String name) {
            this.name = name;
            this.next = null;
        }

        Node(int id) {
            this.id = id;
            this.next = null;
        }

        Node(Member member) {
            this.member = member;
            this.next = null;
        }
    }

    Node head;

    public LinkedList() {
        this.head = null;

    }

    void insert(Node x) {
        if (head == null) {
            head = x;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = x;
        }
    }

    void delete(Node x) {
        if (head == null) {
            return;
        }
        if (head == x) {
            head = head.next;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            if (temp.next == x) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    String search(String name) {
        Node temp = head;
        while (temp != null) {
            if (temp.name.equals(name)) {
                return temp.name;
            }
            temp = temp.next;
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert(new Node("book1"));
        list.insert(new Node("book2"));
        Node node = new Node("book3");
        list.insert(node);
        list.insert(new Node("book4"));
        list.delete(node);
        Node temp = list.head;
        while (temp != null) {
            System.out.println(temp.name);
            temp = temp.next;
        }

    }
}
