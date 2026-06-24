package frontend.windows;

import javax.swing.*;

import backend.dao.UserDAO;
import backend.models.User;

import java.awt.*;

public class LoginWindow extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginWindow() {

        setTitle("Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Username:"));

        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));

        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel());

        loginButton = new JButton("Login");
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {

        String username = usernameField.getText();

        String password = String.valueOf(
                passwordField.getPassword()
        );

        UserDAO dao = new UserDAO();

        User user = dao.login(
                username,
                password
        );

        if (user != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login Successful"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Username or Password"
            );
        }
    }
}