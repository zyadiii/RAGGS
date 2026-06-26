package frontend.windows;

import javax.swing.*;
import java.awt.*;

import backend.dao.UserDAO;
import backend.models.User;
import frontend.utilities.NotificationUtil;
import frontend.utilities.Theme;

public class LoginWindow extends JFrame {

    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // Window UI Initialization/Constructor
    public LoginWindow(){

        setTitle("RAGGS | Student Record Management System");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout(0, 20));

        JPanel formPanel = new JPanel(new GridLayout(4, 1, 0, 5));

        usernameLabel = new JLabel("Username");
        usernameField = new JTextField();

        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        loginButton = new JButton("Login");

        panel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        
        buttonPanel.setBackground(Theme.BACKGROUND);
        buttonPanel.add(loginButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        usernameField.addActionListener(e -> passwordField.requestFocusInWindow());
        passwordField.addActionListener(e -> login());
        loginButton.addActionListener(e -> login());

        applyAlignment(panel);


        applyTheme(panel);


        setVisible(true);

        usernameField.requestFocusInWindow();
    }

    // Login Service
    private void login(){
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (username.isBlank() || password.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password.");
            return;
        }

        UserDAO dao = new UserDAO();

        User user = dao.login(
                username,
                password
        );

        if (user != null) {
            loginSuccess();
        } else {
            loginFailed();
        }
    }

    private void loginSuccess(){
        NotificationUtil.success(this, "Login Successful");

        Timer timer = new Timer(1000, event -> {
            dispose();
            dispose();
            new MainWindow();
        });

        timer.setRepeats(false);
        timer.start();
    }

    private void loginFailed(){
        NotificationUtil.error(this, "Invalid Username or Password");
    }

    private void applyTheme(JPanel panel){
        getContentPane().setBackground(Theme.BACKGROUND);
        panel.setBackground(Theme.BACKGROUND);

        usernameLabel.setFont(Theme.NORMAL_FONT);
        usernameLabel.setForeground(Theme.TEXT);
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        passwordLabel.setFont(Theme.NORMAL_FONT);
        passwordLabel.setForeground(Theme.TEXT);
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        usernameField.setFont(Theme.NORMAL_FONT);
        usernameField.setForeground(Theme.TEXT);
        usernameField.setBackground(Color.WHITE);
        usernameField.setCaretColor(Theme.TEXT);
        usernameField.setBorder(BorderFactory.createCompoundBorder
            (BorderFactory.createLineBorder(Theme.BORDER), 
            BorderFactory.createEmptyBorder(10, 10, 10, 10))
        );

        passwordField.setFont(Theme.NORMAL_FONT);
        passwordField.setForeground(Theme.TEXT);
        passwordField.setBackground(Color.WHITE);
        passwordField.setCaretColor(Theme.TEXT);
        passwordField.setBorder(BorderFactory.createCompoundBorder
            (BorderFactory.createLineBorder(Theme.BORDER), 
            BorderFactory.createEmptyBorder(10, 10, 10, 10))
        );

        loginButton.setFont(Theme.BUTTON_FONT);
        loginButton.setBackground(Theme.PRIMARY);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
    }

    private void applyAlignment(JPanel panel){
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
}