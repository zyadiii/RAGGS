package frontend.windows;

import javax.swing.*;
import java.awt.*;

import frontend.panels.*;
import frontend.utilities.ComponentUtil;
import frontend.utilities.Theme;

public class MainWindow extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    private StudentPanel studentPanel;
    private CoursePanel coursePanel;
    private EnrollmentPanel enrollmentPanel;
    private GradePanel gradePanel;
    private DashboardPanel dashboardPanel;

    public MainWindow() {

        setTitle("Student Record Management System");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // -------------- SIDEBAR ----------------
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1, 10, 10));
        sidebar.setBackground(Theme.SIDEBAR);

        Color sidebarColor = Theme.SIDEBAR;

        JButton dashboardBtn = ComponentUtil.sidebarButton("Dashboard", sidebarColor);
        JButton studentBtn = ComponentUtil.sidebarButton("Students", sidebarColor);
        JButton courseBtn = ComponentUtil.sidebarButton("Courses", sidebarColor);
        JButton enrollmentBtn = ComponentUtil.sidebarButton("Enrollment", sidebarColor);
        JButton gradeBtn = ComponentUtil.sidebarButton("Grades", sidebarColor);
        JButton logoutBtn = ComponentUtil.logoutButton("Logout");

        sidebar.add(dashboardBtn);
        sidebar.add(studentBtn);
        sidebar.add(courseBtn);
        sidebar.add(enrollmentBtn);
        sidebar.add(gradeBtn);
        sidebar.add(logoutBtn);
        
        add(sidebar, BorderLayout.WEST);

        // -------------- MAIN AREA --------------
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

        // -------------- BUTTON ACTIONS --------------
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