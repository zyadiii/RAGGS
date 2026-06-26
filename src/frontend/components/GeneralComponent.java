package frontend.components;

import javax.swing.*;

import frontend.utilities.Theme;
import frontend.utilities.ThemeUtil;

import java.awt.*;

public class GeneralComponent {

    private GeneralComponent() {}

    // ===== GENERAL SIDEBAR BUTTONS =====
    public static JButton sidebarButton(String text, Color baseColor) {

        JButton button = new JButton(text);

        button.setFont(Theme.BUTTON_FONT);
        button.setForeground(Theme.SIDEBAR_TEXT);
        button.setBackground(baseColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 4, Theme.PRIMARY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(Theme.PRIMARY);
                button.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(baseColor);
                button.setForeground(Theme.SIDEBAR_TEXT);
            }
        });

        return button;
    }

    // ===== LOGOUT BUTTON =====
    public static JButton logoutButton(String text) {

        JButton button = new JButton(text);

        button.setFont(Theme.BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(Theme.DANGER);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(Theme.DANGER.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(Theme.DANGER);
            }
        });

        return button;
    }

    // ===== TEXT FIELD =====
    public static JTextField textField() {

        JTextField field = new JTextField();

        field.setFont(Theme.NORMAL_FONT);
        field.setForeground(Theme.TEXT);
        field.setBackground(Color.WHITE);
        field.setCaretColor(Theme.TEXT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.BORDER),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        return field;
    }

    // ===== PASSWORD FIELD =====
    public static JPasswordField passwordField() {

        JPasswordField field = new JPasswordField();

        field.setFont(Theme.NORMAL_FONT);
        field.setForeground(Theme.TEXT);
        field.setBackground(Color.WHITE);
        field.setCaretColor(Theme.TEXT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.BORDER),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        return field;
    }

    // ===== LABEL =====
    public static JLabel label(String text) {

        JLabel label = new JLabel(text);
        label.setFont(Theme.NORMAL_FONT);
        label.setForeground(Theme.TEXT);

        return label;
    }

    // ===== BUTTON =====
    public static JButton button(String text) {

        JButton button = new JButton(text);

        button.setFont(Theme.BUTTON_FONT);
        button.setBackground(Theme.PRIMARY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        return button;
    }

    public static JLabel createDashboardCard(String title, int value) {

        JLabel label = new JLabel("", SwingConstants.CENTER);

        ThemeUtil.styleCard(label);

        label.setText("""
            <html><center>
            <div style='font-size:18px;font-weight:bold;'>%s</div>
            <br>
            <div style='font-size:36px;'>%d</div>
            </center></html>
            """.formatted(title, value));

        return label;
    }
}