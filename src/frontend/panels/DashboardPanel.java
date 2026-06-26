package frontend.panels;

import javax.swing.*;
import java.awt.*;

import backend.dao.CourseDAO;
import backend.dao.EnrollmentDAO;
import backend.dao.StudentDAO;

public class DashboardPanel extends JPanel {

    private JLabel studentCountLabel;
    private JLabel courseCountLabel;
    private JLabel enrollmentCountLabel;
    private JLabel activeStudentCountLabel;

    public DashboardPanel() {

        setLayout(new BorderLayout());

        studentCountLabel = createCard("Students", "0");
        courseCountLabel = createCard("Courses", "0");
        enrollmentCountLabel = createCard("Enrollments", "0");
        activeStudentCountLabel = createCard("Active Students", "0");

        JPanel cardPanel = new JPanel(new GridLayout(2, 2, 20, 20));

        cardPanel.add(studentCountLabel);
        cardPanel.add(courseCountLabel);
        cardPanel.add(enrollmentCountLabel);
        cardPanel.add(activeStudentCountLabel);

        add(cardPanel, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadCounts());

        add(refreshButton, BorderLayout.SOUTH);

        loadCounts();
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

    private void loadCounts() {

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

        updateCounts(
                studentDAO.count(),
                courseDAO.count(),
                enrollmentDAO.count(),
                studentDAO.countActive()
        );
    }

    public void updateCounts(
            int students,
            int courses,
            int enrollments,
            int activeStudents
    ) {

        studentCountLabel.setText(html("Students", students));
        courseCountLabel.setText(html("Courses", courses));
        enrollmentCountLabel.setText(html("Enrollments", enrollments));
        activeStudentCountLabel.setText(html("Active Students", activeStudents));
    }

    private String html(String title, int value) {
        return "<html><center><h2>" + title + "</h2><h1>" + value + "</h1></center></html>";
    }
}