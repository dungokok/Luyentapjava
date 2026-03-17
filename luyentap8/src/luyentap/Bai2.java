interface Payment {
    void pay(double amount);
}

class CreditCardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toan bang Credit Card: " + amount);
    }
}

class PayPalPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toan bang PayPal: " + amount);
    }
}

class CashPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toan bang Cash: " + amount);
    }
}

class PaymentFactory {
    public static Payment createPayment(String type) {
        if (type == null) {
            return null;
        }

        switch (type.toLowerCase()) {
            case "creditcard":
                return new CreditCardPayment();
            case "paypal":
                return new PayPalPayment();
            case "cash":
                return new CashPayment();
            default:
                throw new IllegalArgumentException("Phương thức thanh toán không hợp lệ.");
        }
    }
}

public class Bai2 {
    public static void main(String[] args) {
        Payment p1 = PaymentFactory.createPayment("creditcard");
        Payment p2 = PaymentFactory.createPayment("paypal");
        Payment p3 = PaymentFactory.createPayment("cash");

        p1.pay(500000);
        p2.pay(250000);
        p3.pay(100000);
    }
}