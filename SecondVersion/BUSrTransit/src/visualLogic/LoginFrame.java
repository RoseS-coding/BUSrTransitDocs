package visualLogic;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;


import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import userLogic.User;
import userLogic.UserService;
import userLogic.*;


public class LoginFrame extends JFrame {

    // Declare components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;
    private UserService userService;

    public LoginFrame(UserService userService) {
    	this.userService = userService;
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
        
     // Set bounds for the components
        usernameLabel.setBounds(100, 300, 200, 30);
        usernameField.setBounds(300, 300, 200, 30); // Set bounds for usernameField
        passwordLabel.setBounds(100, 400, 200, 30);
        passwordField.setBounds(300, 400, 200, 30); // Set bounds for passwordField
        loginButton.setBounds(300, 500, 200, 30);
        messageLabel.setBounds(300, 550, 300, 30);

        // Ad components to the contentPane
        contentPane.add(usernameLabel);
        contentPane.add(usernameField);
        contentPane.add(passwordLabel);
        contentPane.add(passwordField);
        contentPane.add(loginButton);
        contentPane.add(messageLabel);

        // Add action listener for the login button

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
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
            dispose(); // Close the login frame
            Main.executeUserAction(loggedInUser ); // Call executeUser Action with the logged-in user
        } else {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Invalid username or password.");
        }

    }



}