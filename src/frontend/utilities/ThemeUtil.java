package frontend.utilities;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ThemeUtil {

    private ThemeUtil() {}

    public static void applyWindowTheme(Container container) {
        container.setBackground(Theme.BACKGROUND);
    }

    public static void stylePanel(JPanel panel) {
        panel.setBackground(Theme.BACKGROUND);
    }

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

    public static void styleTitle(JLabel label) {
        label.setFont(Theme.TITLE_FONT);
        label.setForeground(Theme.TEXT);
    }

    public static void styleTable(JTable table) {

        table.setFont(Theme.NORMAL_FONT);
        table.setRowHeight(28);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = table.getTableHeader();
        header.setFont(Theme.HEADER_FONT);
        header.setBackground(Theme.PRIMARY);
        header.setForeground(Color.WHITE);
    }

    public static void styleCard(JLabel label) {

        label.setOpaque(true);
        label.setBackground(Theme.CARD_BACKGROUND);
        label.setForeground(Theme.TEXT);

        Border outer = BorderFactory.createLineBorder(Theme.BORDER);
        Border inner = BorderFactory.createEmptyBorder(25, 20, 25, 20);

        label.setBorder(BorderFactory.createCompoundBorder(outer, inner));
    }
}