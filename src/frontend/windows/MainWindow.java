package frontend.windows;

import javax.swing.*;
import java.awt.*;

import frontend.panels.*;

public class MainWindow extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    private StudentPanel studentPanel;
    private CoursePanel coursePanel;
    private EnrollmentPanel enrollmentPanel;
    private GradePanel gradePanel;
    private DashboardPanel dashboardPanel;

    public MainWindow() {

        setTitle("Student Information System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= SIDEBAR =================
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1, 10, 10));

        JButton dashboardBtn = new JButton("Dashboard");
        JButton studentBtn = new JButton("Students");
        JButton courseBtn = new JButton("Courses");
        JButton enrollmentBtn = new JButton("Enrollment");
        JButton gradeBtn = new JButton("Grades");
        JButton logoutBtn = new JButton("Logout");

        sidebar.add(dashboardBtn);
        sidebar.add(studentBtn);
        sidebar.add(courseBtn);
        sidebar.add(enrollmentBtn);
        sidebar.add(gradeBtn);
        sidebar.add(logoutBtn);

        add(sidebar, BorderLayout.WEST);

        // ================= CONTENT AREA =================
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        dashboardPanel = new DashboardPanel();
        studentPanel = new StudentPanel();
        coursePanel = new CoursePanel();
        enrollmentPanel = new EnrollmentPanel();
        gradePanel = new GradePanel();

        contentPanel.add(dashboardPanel, "dashboard");
        contentPanel.add(studentPanel, "students");
        contentPanel.add(coursePanel, "courses");
        contentPanel.add(enrollmentPanel, "enrollment");
        contentPanel.add(gradePanel, "grades");

        add(contentPanel, BorderLayout.CENTER);

        // ================= BUTTON ACTIONS =================
        dashboardBtn.addActionListener(e -> cardLayout.show(contentPanel, "dashboard"));
        studentBtn.addActionListener(e -> cardLayout.show(contentPanel, "students"));
        courseBtn.addActionListener(e -> cardLayout.show(contentPanel, "courses"));
        enrollmentBtn.addActionListener(e -> cardLayout.show(contentPanel, "enrollment"));
        gradeBtn.addActionListener(e -> cardLayout.show(contentPanel, "grades"));

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginWindow();
        });

        setVisible(true);
    }
}