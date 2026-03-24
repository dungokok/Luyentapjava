package bai1;
import java.util.HashMap;

public class Bai1_HashMap {
    public static void main(String[] args) {
        HashMap<Integer, String> employees = new HashMap<>();

        employees.put(101, "Anna");
        employees.put(102, "Peter");
        employees.put(103, "Mary");

        String name102 = employees.get(102);
        System.out.println("Nhan vien ID 102: " + name102);

        if (!employees.containsKey(105)) {
            employees.put(105, "Unknown");
        }

        System.out.println("Danh sach nhan vien: " + employees);
    }
}
