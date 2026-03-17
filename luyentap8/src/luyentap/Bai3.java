import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Bai3 {

    public static CompletableFuture<Boolean> xacThucKhachHang(String customerId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Dang xac thuc khach hang...");
                Thread.sleep(2000); // trễ 2 giây

                if (!customerId.equals("KH001")) {
                    throw new RuntimeException("Xac thuc that bai: khach hang ko hop le.");
                }

                System.out.println("Xac thuc thanh cong.");
                return true;
            } catch (InterruptedException e) {
                throw new RuntimeException("Loi trong qua trinh xac thuc");
            }
        });
    }

    public static CompletableFuture<Boolean> kiemTraSoDu(double balance, double amount) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Dang kiem tra so du..");
                Thread.sleep(1500); // trễ 1.5 giây

                if (balance < amount) {
                    throw new RuntimeException("Kiem tra so du that bai. Ko du tien.");
                }

                System.out.println("So du hop le");
                return true;
            } catch (InterruptedException e) {
                throw new RuntimeException("Loi khi kiem tra so du");
            }
        });
    }

    public static CompletableFuture<String> chuyenTien(double amount) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Dang thuc hien chuyen tien...");
                Thread.sleep(3000); // trễ 3 giây
                return "Chuyen tien thanh cong: " + amount;
            } catch (InterruptedException e) {
                throw new RuntimeException("Loi trong qua trinh chuyen tien.");
            }
        });
    }

    public static void main(String[] args) {
        String customerId = "KH001";
        double balance = 1000000;
        double amount = 500000;

        CompletableFuture<String> giaoDich = xacThucKhachHang(customerId)
                .thenCompose(authResult -> kiemTraSoDu(balance, amount))
                .thenCompose(balanceResult -> chuyenTien(amount))
                .exceptionally(ex -> {
                    return "Giao dich bi huy: " + ex.getMessage();
                });

        try {
            String ketQua = giaoDich.get();
            System.out.println("Ket qua cuoi cung: " + ketQua);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Loi he thong: " + e.getMessage());
        }
    }
}