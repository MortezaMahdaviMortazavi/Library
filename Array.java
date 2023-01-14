import java.util.Scanner;

public class Array {
    int size;
    int[] array;
    int[][] matrix;

    public Array(int size) {
        this.size = size;
        this.array = new int[size];
    }

    public void print() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.array[i]);
        }
    }

    public void insert(int index, int value) {
        if (index < 0 || index >= this.size) {
            System.out.println("Index out of bounds");
            return;
        }
        this.array[index] = value;
    }

    public void delete(int index) {
        if (index < 0 || index >= this.size) {
            System.out.println("Index out of bounds");
            return;
        }
        this.array[index] = 0;
    }

    public void search(int value) {
        for (int i = 0; i < this.size; i++) {
            if (this.array[i] == value) {
                System.out.println("Found at index " + i);
                return;
            }
        }
        System.out.println("Not found");
    }


}
