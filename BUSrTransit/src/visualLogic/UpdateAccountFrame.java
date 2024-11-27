package visualLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateAccountFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField emailField;

    public UpdateAccountFrame() {
        // Set up the frame
        setTitle("Update Account Info");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Create labels and text fields
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JButton updateButton = new JButton("Update");
        JButton cancelButton = new JButton("Cancel");

        // Add action listener for the update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to update user account info
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                String email = emailField.getText();

                // Here you would typically call a method to update the user info in your system
                // For example: userService.updateUser (username, password, name, email);

                // Show a confirmation message
                JOptionPane.showMessageDialog(UpdateAccountFrame.this, "Account information updated successfully!");
                dispose(); // Close the frame
            }
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame without making changes
            }
        });

        // Add components to the frame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(updateButton);
        add(cancelButton);
    }

    public static void main(String[] args) {
        // Create and display the update account frame
        UpdateAccountFrame updateFrame = new UpdateAccountFrame();
        updateFrame.setVisible(true);
    }
}