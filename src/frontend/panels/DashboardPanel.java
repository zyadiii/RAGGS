package frontend.panels;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private JLabel studentCountLabel;
    private JLabel courseCountLabel;
    private JLabel enrollmentCountLabel;
    private JLabel gradeCountLabel;

    public DashboardPanel() {

        setLayout(new GridLayout(2, 2, 20, 20));

        studentCountLabel = createCard("Students", "0");
        courseCountLabel = createCard("Courses", "0");
        enrollmentCountLabel = createCard("Enrollments", "0");
        gradeCountLabel = createCard("Grades", "0");

        add(studentCountLabel);
        add(courseCountLabel);
        add(enrollmentCountLabel);
        add(gradeCountLabel);
    }

    private JLabel createCard(String title, String value) {

        JLabel label = new JLabel(
                "<html><center>"
                        + "<h2>" + title + "</h2>"
                        + "<h1>" + value + "</h1>"
                        + "</center></html>",
                SwingConstants.CENTER
        );

        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);

        return label;
    }

    // later you will use this with DAO
    public void updateCounts(int students, int courses, int enrollments, int grades) {

        studentCountLabel.setText(html("Students", students));
        courseCountLabel.setText(html("Courses", courses));
        enrollmentCountLabel.setText(html("Enrollments", enrollments));
        gradeCountLabel.setText(html("Grades", grades));
    }

    private String html(String title, int value) {
        return "<html><center><h2>" + title + "</h2><h1>" + value + "</h1></center></html>";
    }
}