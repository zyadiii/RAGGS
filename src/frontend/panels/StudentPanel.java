package frontend.panels;

import javax.swing.*;
import java.awt.*;

public class StudentPanel extends JPanel {

    public StudentPanel() {

        setLayout(new BorderLayout());

        add(new JLabel("STUDENT MODULE", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
