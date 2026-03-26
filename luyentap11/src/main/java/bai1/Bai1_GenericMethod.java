package bai1;
public class Bai1_GenericMethod {

    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        System.out.print("Integer Array: ");
        printArray(intArray);
        String[] strArray = {"Hello", "Java", "Generic"};
        System.out.print("String Array: ");
        printArray(strArray);
    }
}