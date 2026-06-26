package frontend.windows;

import javax.swing.*;
import java.awt.*;

import frontend.components.GeneralComponent;
import frontend.panels.*;
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

        setTitle("RAGGS | Student Record Management System");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // -------------- SIDEBAR ----------------
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1, 10, 10));
        sidebar.setBackground(Theme.SIDEBAR);

        Color sidebarColor = Theme.SIDEBAR;

        JButton dashboardBtn = GeneralComponent.sidebarButton("Dashboard", sidebarColor);
        JButton studentBtn = GeneralComponent.sidebarButton("Students", sidebarColor);
        JButton courseBtn = GeneralComponent.sidebarButton("Courses", sidebarColor);
        JButton enrollmentBtn = GeneralComponent.sidebarButton("Enrollment", sidebarColor);
        JButton gradeBtn = GeneralComponent.sidebarButton("Grades", sidebarColor);
        JButton logoutBtn = GeneralComponent.logoutButton("Logout");

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

        logoutBtn.addActionListener(e -> logout());

        setVisible(true);
    }

    private void logout(){
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to logout?",
            "Logout Confirmation",
            JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION){
            dispose();
            new LoginWindow();
        }
    }
}