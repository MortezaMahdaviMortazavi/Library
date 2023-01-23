import java.util.Scanner;

public class BinaryTree {
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
