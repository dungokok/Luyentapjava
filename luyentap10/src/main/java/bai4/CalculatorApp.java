package bai4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {

    private JTextField display;
    private JLabel historyLabel;

    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public CalculatorApp() {
        setTitle("Calculator");
        setSize(380, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        historyLabel = new JLabel(" ");
        historyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        historyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        historyLabel.setForeground(Color.GRAY);

        display = new JTextField("0");
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Segoe UI", Font.BOLD, 36));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       topPanel.add(historyLabel, BorderLayout.NORTH);
       topPanel.add(display, BorderLayout.CENTER);

       JPanel buttonPanel = new JPanel(new GridLayout(6, 4, 8, 8));
       buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       String[] buttons = {
                "%", "CE", "C", "⌫",
                "1/x", "x²", "√x", "÷",
                "7", "8", "9", "×",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ".", "="
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            btn.setFocusPainted(false);
            btn.addActionListener(this);

            if ("=÷×-+CEC⌫1/xx²√x%".contains(text) || text.equals("=")) {
                btn.setBackground(new Color(240, 240, 240));
            } else {
                btn.setBackground(Color.WHITE);
            }

            buttonPanel.add(btn);
        }

        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = ((JButton) e.getSource()).getText();

        if (cmd.matches("[0-9]")) {
            inputNumber(cmd);
        } else {
            switch (cmd) {
                case ".":
                    inputDot();
                    break;
                case "+":
                case "-":
                case "×":
                case "÷":
                    setOperator(cmd);
                    break;
                case "=":
                    calculate();
                    break;
                case "C":
                    clearAll();
              break;
                case "CE":
               clearEntry();
                    break;
            case "⌫":
                    backspace();
                break;
                case "+/-":
                toggleSign();
                    break;
                case "%":
                    percent();
                    break;
                case "1/x":
                    reciprocal();
                    break;
                case "x²":
                    square();
                    break;
                case "√x":
                    squareRoot();
                    break;
            }
        }
    }

    private void inputNumber(String num) {
        if (startNewNumber || display.getText().equals("0")) {
            display.setText(num);
            startNewNumber = false;
        } else {
            display.setText(display.getText() + num);
        }
    }

    private void inputDot() {
        if (startNewNumber) {
            display.setText("0.");
            startNewNumber = false;
        } else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    private void setOperator(String op) {
        firstNumber = Double.parseDouble(display.getText());
        operator = op;
        historyLabel.setText(formatNumber(firstNumber) + " " + op);
        startNewNumber = true;
    }

    private void calculate() {
        if (operator.isEmpty()) return;

        double secondNumber = Double.parseDouble(display.getText());
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "×":
                result = firstNumber * secondNumber;
                break;
            case "÷":
                if (secondNumber == 0) {
                    display.setText("Không thể chia 0");
                    operator = "";
                    startNewNumber = true;
                    return;
                }
                result = firstNumber / secondNumber;
                break;
        }

        historyLabel.setText(formatNumber(firstNumber) + " " + operator + " " + formatNumber(secondNumber) + " =");
        display.setText(formatNumber(result));
        operator = "";
        startNewNumber = true;
    }

    private void clearAll() {
        display.setText("0");
        historyLabel.setText(" ");
        firstNumber = 0;
        operator = "";
        startNewNumber = true;
    }

    private void clearEntry() {
        display.setText("0");
        startNewNumber = true;
    }

    private void backspace() {
        if (startNewNumber) return;

        String text = display.getText();
        if (text.length() > 1) {
            display.setText(text.substring(0, text.length() - 1));
        } else {
            display.setText("0");
            startNewNumber = true;
        }
    }

    private void toggleSign() {
        String text = display.getText();
        if (!text.equals("0") && !text.equals("Không thể chia 0")) {
            if (text.startsWith("-")) {
                display.setText(text.substring(1));
            } else {
                display.setText("-" + text);
            }
        }
    }

    private void percent() {
        double value = Double.parseDouble(display.getText());
        value = value / 100;
        display.setText(formatNumber(value));
    }

    private void reciprocal() {
        double value = Double.parseDouble(display.getText());
        if (value == 0) {
            display.setText("Không thể chia 0");
            startNewNumber = true;
            return;
        }
        display.setText(formatNumber(1 / value));
        startNewNumber = true;
    }

    private void square() {
        double value = Double.parseDouble(display.getText());
        display.setText(formatNumber(value * value));
        startNewNumber = true;
    }

    private void squareRoot() {
        double value = Double.parseDouble(display.getText());
        if (value < 0) {
            display.setText("Lỗi");
            startNewNumber = true;
            return;
        }
        display.setText(formatNumber(Math.sqrt(value)));
        startNewNumber = true;
    }

    private String formatNumber(double number) {
        if (number == (long) number) {
            return String.valueOf((long) number);
        }
        return String.valueOf(number);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorApp app = new CalculatorApp();
            app.setVisible(true);
        });
    }
}
