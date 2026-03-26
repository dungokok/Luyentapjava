package bai2;
interface MessageService {
    void sendMessage(String message);
}
class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Send Email: " + message);
    }
}
class SMSService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Send SMS: " + message);
    }
}
class Notification {
    private MessageService messageService;
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void notifyUser(String message) {
        messageService.sendMessage(message);
    }
}
public class Bai2_SetterInjection {
    public static void main(String[] args) {
        Notification notification = new Notification();
        notification.setMessageService(new EmailService());
        notification.notifyUser("Hello via Email!");
        notification.setMessageService(new SMSService());
        notification.notifyUser("Hello via SMS!");
    }
}