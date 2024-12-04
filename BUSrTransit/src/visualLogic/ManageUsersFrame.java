package visualLogic;

import roleActions.AdminAction;
import userLogic.*; // Assuming you have a User class
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageUsersFrame extends JFrame {
	private AdminAction adminAction;

    // Input fields for adding/updating users
    private JTextField userIdField;
    private JTextField usernameField; // New field for username
    private JTextField passwordField; // New field for password
    private JTextField nameField; // New field for name
    private JTextField emailField; // New field for email
    private JComboBox<UserType> userTypeComboBox; // ComboBox for user type
    private UserService userService;

    public ManageUsersFrame(AdminAction adminAction, UserService userService) {
        this.adminAction = adminAction;
        this.userService = userService;
        setupUI();
    }

    private void setupUI() {
        setTitle("Manage Users");
        setSize(400, 400); // Increased size for additional fields
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        // Create input fields
        userIdField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();

        // Assuming UserType is an enum, populate the combo box with user types
        userTypeComboBox = new JComboBox<>(UserType.values());

        // Add components to the frame
        add(new JLabel("User  ID:"));
        add(userIdField);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("User  Type:"));
        add(userTypeComboBox);

        // Add buttons for actions
        JButton addButton = new JButton("Add User");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        JButton updateButton = new JButton("Update User");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        JButton deleteButton = new JButton("Delete User");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

        JButton viewButton = new JButton("View Users");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewUsers();
            }
        });

        // Add buttons to the frame
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(viewButton);
    }

    private void addUser() {
        String userId = userIdField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        UserType userType = (UserType)userTypeComboBox.getSelectedItem(); // Get selected user type

        // Create a new User object
        User newUser  = new User(userId, username, password, name, email, userType);
        adminAction.addUser(newUser ); // Assuming addUser  method exists in AdminAction
        JOptionPane.showMessageDialog(this, "User  added successfully!");
    }

    private void updateUser() {
        String userId = userIdField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        UserType userType = (UserType)userTypeComboBox.getSelectedItem(); // Get selected user type

        // Assuming updateUser  method is: updateUser (String userId, String username, String password, String name, String email, UserType userType)
        adminAction.updateUser(userId, username, password, name, email, userType); // Assuming updateUser  method exists in AdminAction
        JOptionPane.showMessageDialog(this, "User  updated successfully!");
    }

    private void deleteUser() {
        String userId = userIdField.getText();
        adminAction.deleteUser(userId); // Assuming deleteUser   method exists in AdminAction
        JOptionPane.showMessageDialog(this, "User   deleted successfully!");
    }

    private void viewUsers() {
        StringBuilder usersList = new StringBuilder();
        List<User> users = adminAction.getUsers(); // Assuming getUsers method exists in AdminAction

        System.out.println("Number of users: " + users.size());
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No users found.", "User  List", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        for (User  user : users) {
            usersList.append(user.toString()).append("\n"); // Assuming User class has a toString method
        }
        JOptionPane.showMessageDialog(this, usersList.toString(), "User  List", JOptionPane.INFORMATION_MESSAGE);
    }
}
