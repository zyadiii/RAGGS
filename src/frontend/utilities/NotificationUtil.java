package frontend.utilities;

import javax.swing.*;
import java.awt.*;

public class NotificationUtil {

    private NotificationUtil() {}

    public static void success(Component parent, String message) {
        showNotif(parent, message, Theme.SUCCESS);
    }

    public static void error(Component parent, String message) {
        showNotif(parent, message, Theme.DANGER);
    }

    private static void showNotif(Component parent, String message, Color color) {

        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.setAlwaysOnTop(true);

        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.BORDER, 1),
                BorderFactory.createEmptyBorder(12, 20, 12, 20)
        ));
        JLabel label = new JLabel(message);
        label.setForeground(Color.WHITE);
        label.setFont(Theme.BUTTON_FONT);

        panel.add(label);

        dialog.setContentPane(panel);
        dialog.pack();

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);

        Timer timer = new Timer(1500, e -> dialog.dispose());
        timer.setRepeats(false);
        timer.start();
    }
}