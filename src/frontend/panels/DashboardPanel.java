package frontend.panels;

import javax.swing.*;

import backend.dao.CourseDAO;
import backend.dao.EnrollmentDAO;
import backend.dao.InstructorDAO;
import backend.dao.StudentDAO;

import java.awt.*;

public class DashboardPanel extends JPanel {

    private JLabel studentCountLabel;
    private JLabel courseCountLabel;
    private JLabel enrollmentCountLabel;
    private JLabel instructorCountLabel;

    public DashboardPanel() {

        setLayout(new GridLayout(2, 2, 20, 20));

        studentCountLabel = createCard("Students", "0");
        courseCountLabel = createCard("Courses", "0");
        enrollmentCountLabel = createCard("Enrollments", "0");
        instructorCountLabel = createCard("Instructors", "0");

        add(studentCountLabel);
        add(courseCountLabel);
        add(enrollmentCountLabel);
        add(instructorCountLabel);
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
        InstructorDAO instructorDAO = new InstructorDAO();

        updateCounts(
                studentDAO.count(),
                courseDAO.count(),
                enrollmentDAO.count(),
                instructorDAO.count()
        );
    }
    
    public void updateCounts(
        int students,
        int courses,
        int enrollments,
        int instructors
    ) {

        studentCountLabel.setText(
                html("Students", students)
        );

        courseCountLabel.setText(
                html("Courses", courses)
        );

        enrollmentCountLabel.setText(
                html("Enrollments", enrollments)
        );

        instructorCountLabel.setText(
                html("Instructors", instructors)
        );
    }

    private String html(String title, int value) {
        return "<html><center><h2>" + title + "</h2><h1>" + value + "</h1></center></html>";
    }
}