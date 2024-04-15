import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {
    private JTextField displayField;
    private double num1, num2, result;
    private char operation;
    private boolean resultDisplayed;

    public CalculatorApp() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.GRAY); // Set background color

        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 45)); // Increased font size
        displayField.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border
        panel.add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 1, 1)); // Add spacing between buttons
        buttonPanel.setBackground(Color.GRAY); // Set background color

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.PLAIN, 24)); // Increased font size for buttons
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border to buttons
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);

        resultDisplayed = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (resultDisplayed && Character.isDigit(command.charAt(0)) || command.equals(".")) {
            // If result is displayed and a digit is clicked, start a new calculation
            displayField.setText(command);
            resultDisplayed = false;
        } else if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
            displayField.setText(displayField.getText() + command);
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            num1 = Double.parseDouble(displayField.getText());
            operation = command.charAt(0);
            displayField.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(displayField.getText());
            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(this, "Error: Division by zero!");
                        return;
                    }
                    break;
            }
            displayField.setText(String.valueOf(result));
            resultDisplayed = true;
        } else {
            displayField.setText("");
        }
    }

    public static void main(String[] args) {
        // Use the event dispatch thread to build the GUI
        SwingUtilities.invokeLater(() -> new CalculatorApp());
    }
}
// -By Anmol Vaswani