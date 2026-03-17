import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String mssv;
    private String ten;
    private double gpa;

    public Student(String mssv, String ten, double gpa) {
        this.mssv = mssv;
        this.ten = ten;
        this.gpa = gpa;
    }

    public String getMssv() {
        return mssv;
    }

    public String getTen() {
        return ten;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return "MSSV: " + mssv + ", Tên: " + ten + ", GPA: " + gpa;
    }
}

class StudentManager {
    private ArrayList<Student> danhSach = new ArrayList<>();

    public void themSinhVien(Student sv) {
        danhSach.add(sv);
        System.out.println("Da them sinh vien");
    }

    public void hienThiDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sach sinh vien trong.");
            return;
        }

        System.out.println("Danh sach sinh vien ");
        for (Student sv : danhSach) {
            System.out.println(sv);
        }
    }

    public void timTheoTen(String ten) {
        boolean timThay = false;
        for (Student sv : danhSach) {
            if (sv.getTen().toLowerCase().contains(ten.toLowerCase())) {
                System.out.println(sv);
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Ko tim thay svien co ten: " + ten);
        }
    }

    public void xoaTheoMssv(String mssv) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMssv().equalsIgnoreCase(mssv)) {
                danhSach.remove(i);
                System.out.println("Đa xoa sinh vien co MSSV: " + mssv);
                return;
            }
        }
        System.out.println("Ko tim thay sinh vien co MSSV: " + mssv);
    }
}

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        int choice;

        do {
            System.out.println("\n Menu qly sinh vien");
            System.out.println("1. them sinh vien");
            System.out.println("2. Hien thi danh sach sinh vien");
            System.out.println("3. Tim kiem sinh vien theo ten");
            System.out.println("4. Xoa svien theo MSSV");
            System.out.println("0. Thoa");
            System.out.print("Chon chuc nang: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("NhapMSSV: ");
                    String mssv = sc.nextLine();

                    System.out.print("Nhap ten: ");
                    String ten = sc.nextLine();

                    System.out.print("Nhap GPA: ");
                    double gpa = Double.parseDouble(sc.nextLine());

                    manager.themSinhVien(new Student(mssv, ten, gpa));
                    break;

                case 2:
                    manager.hienThiDanhSach();
                    break;

                case 3:
                    System.out.print("Nhap ten can tim: ");
                    String tenTim = sc.nextLine();
                    manager.timTheoTen(tenTim);
                    break;

                case 4:
                    System.out.print("Nhap mssv can xoa: ");
                    String mssvXoa = sc.nextLine();
                    manager.xoaTheoMssv(mssvXoa);
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh");
                    break;

                default:
                    System.out.println("Lua chon ko hop le.");
            }

        } while (choice != 0);

        sc.close();
    }
}