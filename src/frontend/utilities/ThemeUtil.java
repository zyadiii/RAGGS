package frontend.utilities;

import javax.swing.*;
import java.awt.*;

public class ThemeUtil {

    private ThemeUtil() {}

    public static void styleTextField(JTextField field) {
        field.setFont(Theme.NORMAL_FONT);
        field.setForeground(Theme.TEXT);
        field.setBackground(Color.WHITE);
        field.setCaretColor(Theme.TEXT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.BORDER),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    public static void stylePasswordField(JPasswordField field) {
        styleTextField(field);
    }

    public static void styleButton(JButton button) {
        button.setFont(Theme.BUTTON_FONT);
        button.setBackground(Theme.PRIMARY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    public static void styleLabel(JLabel label) {
        label.setFont(Theme.NORMAL_FONT);
        label.setForeground(Theme.TEXT);
    }

    public static void applyWindowTheme(Container container) {
        container.setBackground(Theme.BACKGROUND);
    }
}