package visualLogic;

import roleActions.AdminAction;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminActionFrame extends JFrame {
    private AdminAction adminAction;

    public AdminActionFrame(AdminAction adminAction) {
        this.adminAction = adminAction;
        setTitle("Admin Actions");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1)); // 5 rows, 1 column

        JButton addRouteButton = new JButton("Add Route");
        JButton updateRouteButton = new JButton("Update Route");
        JButton deleteRouteButton = new JButton("Delete Route");
        JButton viewRoutesButton = new JButton("View Routes");
        JButton logoutButton = new JButton("Logout");

        addRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminAction.addRoute(); // Call addRoute method from AdminAction
            }
        });

        updateRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminAction.updateRoute(); // Call updateRoute method from AdminAction
            }
        });

        deleteRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminAction.deleteRoute(); // Call deleteRoute method from AdminAction
            }
        });

        viewRoutesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminAction.viewRoutes(); // Call viewRoutes method from AdminAction
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the admin action frame
                // You might want to go back to the login frame or main menu here
            }
        });

        add(addRouteButton);
        add(updateRouteButton);
        add(deleteRouteButton);
        add(viewRoutesButton);
        add(logoutButton);
    }
}
