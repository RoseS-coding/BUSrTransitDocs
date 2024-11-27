package visualLogic;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import userLogic.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import userLogic.User;
import userLogic.UserService;
import java.util.Map;
import userLogic.*;
import dataLogic.*;

public class LoginFrame extends JFrame {

    // Declare components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel messageLabel;
    private UserService userService;
    private UserDataLoader userDataLoader;
    private Map<String, User> userDatabase;

    public LoginFrame(UserService userService, String csvFilePath, Map<String, User> userDatabase) {
    	this.userService = userService;
    	this.userDataLoader = new UserDataLoader(csvFilePath);
    	this.userDatabase = userDatabase;
    	this.userDataLoader.loadUsers(userDatabase);
        // Set up the frame
        setTitle("Login Page");
        setSize(1080, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final BufferedImage backgroundImage;
        try {
        	backgroundImage = ImageIO.read(getClass().getResource("/Images/loginPage.png"));
        } catch (IOException e) {
        	System.err.println("Error loading background image: " + e.getMessage());
        	System.exit(1);
        	return;
        }
        JPanel contentPane = new JPanel() {
        	@Override
        	protected void paintComponent(Graphics g) {
        		super.paintComponent(g);
        		g.drawImage(backgroundImage, 0, 0, null);
        	}
        };
        
        contentPane.setLayout(null);
        setupComponents(contentPane);
        setLocationRelativeTo(null); // Center the frame on the screen
        setContentPane(contentPane);

        

    }
    
    private void setupComponents(JPanel contentPane) {
    	JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        messageLabel = new JLabel("");
        registerButton = new JButton("Register");
        
     // Set bounds for the components
        usernameLabel.setBounds(100, 300, 200, 30);
        usernameField.setBounds(300, 300, 200, 30); // Set bounds for usernameField
        passwordLabel.setBounds(100, 400, 200, 30);
        passwordField.setBounds(300, 400, 200, 30); // Set bounds for passwordField
        loginButton.setBounds(300, 500, 200, 30);
        messageLabel.setBounds(300, 600, 300, 30);
        registerButton.setBounds(300, 550, 200, 30);

        // Ad components to the contentPane
        contentPane.add(usernameLabel);
        contentPane.add(usernameField);
        contentPane.add(passwordLabel);
        contentPane.add(passwordField);
        contentPane.add(loginButton);
        contentPane.add(registerButton);
        contentPane.add(messageLabel);

        // Add action listener for the login button

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegistrationDialog();
            }
        });
    }


    private void handleLogin() {

    	String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // Validate user credentials using UserService
        User loggedInUser  = userService.validateUser (username, password);
        if (loggedInUser  != null) {
            messageLabel.setForeground(Color.GREEN);
            messageLabel.setText("Login Successful!");
            Main.executeUserAction(loggedInUser );
            dispose(); // Close the login frame // Call executeUser Action with the logged-in user
        } else {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Invalid username or password.");
        }

    }
   
    private void openRegistrationDialog() {
        // Create a new dialog for registration
        JDialog registrationDialog = new JDialog(this, "Register New User", true);
        registrationDialog.setSize(400, 300);
        registrationDialog.setLayout(new GridLayout(5, 2));
        // Create input fields for registration
        JTextField newUsernameField = new JTextField();
        JPasswordField newPasswordField = new JPasswordField();
        JTextField newNameField = new JTextField();
        JTextField newEmailField = new JTextField();

        // Add labels and input fields to the dialog
        registrationDialog.add(new JLabel("Username:"));
        registrationDialog.add(newUsernameField);
        registrationDialog.add(new JLabel("Password:"));
        registrationDialog.add(newPasswordField);
        registrationDialog.add(new JLabel("Name:"));
        registrationDialog.add(newNameField);
        registrationDialog.add(new JLabel("Email:"));
        registrationDialog.add(newEmailField);

        // Create and add the register button
        JButton registerButton = new JButton("Register");
        registrationDialog.add(registerButton);
        // Action listener for the register button
        registerButton.addActionListener(e -> {
            // Retrieve user input
            String username = newUsernameField.getText().trim();
            String password = new String(newPasswordField.getPassword()).trim();
            String name = newNameField.getText().trim();
            String email = newEmailField.getText().trim();
            UserType userType = UserType.USER; // Set fixed user type

            // Validate user input
            if (validateInput(username, password, name, email)) {
                // Create a new User object
                User newUser  = new User(null, username, password, name, email, userType);
                
                // Register the new user using UserService
                userService.registerUser (newUser );
                
                // Check if registration was successful
                if (userService.getUserDatabase().containsKey(username)) {
                    messageLabel.setForeground(Color.GREEN);
                    messageLabel.setText("User  registered successfully!");
                    registrationDialog.dispose(); // Close the dialog
                } else {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Registration failed. User might already exist.");
                }
            } else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Please fill in all fields correctly.");
            }
        });

        registrationDialog.setVisible(true); // Show the dialog
    }
    // Method to validate user input
    private boolean validateInput(String username, String password, String name, String email) {
        return !username.isEmpty() && !password.isEmpty() && !name.isEmpty() && isValidEmail(email);
    }

    // Method to validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }


}
