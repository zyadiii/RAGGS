package frontend.panels;

import javax.swing.*;
import java.awt.*;

public class EnrollmentPanel extends JPanel {

    private JComboBox<String> studentCombo;
    private JComboBox<String> courseCombo;

    private JTextField schoolYearField;
    private JTextField semesterField;

    private JButton enrollButton;
    private JButton clearButton;

    public EnrollmentPanel() {

        setLayout(new BorderLayout(10, 10));

        // ================= FORM PANEL =================
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));

        formPanel.add(new JLabel("Student:"));
        studentCombo = new JComboBox<>();
        formPanel.add(studentCombo);

        formPanel.add(new JLabel("Course:"));
        courseCombo = new JComboBox<>();
        formPanel.add(courseCombo);

        formPanel.add(new JLabel("School Year:"));
        schoolYearField = new JTextField();
        formPanel.add(schoolYearField);

        formPanel.add(new JLabel("Semester:"));
        semesterField = new JTextField();
        formPanel.add(semesterField);

        enrollButton = new JButton("Enroll");
        clearButton = new JButton("Clear");

        formPanel.add(enrollButton);
        formPanel.add(clearButton);

        add(formPanel, BorderLayout.NORTH);

        // ================= TABLE (PLACEHOLDER) =================
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        // ================= ACTIONS =================
        enrollButton.addActionListener(e -> enrollStudent());
        clearButton.addActionListener(e -> clearFields());

        // TEMP DATA (until DAO is ready)
        loadDummyData();
    }

    private void enrollStudent() {

        if (studentCombo.getSelectedItem() == null ||
            courseCombo.getSelectedItem() == null ||
            schoolYearField.getText().trim().isEmpty() ||
            semesterField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all fields",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String student = studentCombo.getSelectedItem().toString();
        String course = courseCombo.getSelectedItem().toString();

        JOptionPane.showMessageDialog(
                this,
                "Enrolled Successfully (placeholder)\n" +
                        student + " → " + course,
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearFields();
    }

    private void clearFields() {

        studentCombo.setSelectedIndex(-1);
        courseCombo.setSelectedIndex(-1);
        schoolYearField.setText("");
        semesterField.setText("");
    }

    // temporary until DAO is connected
    private void loadDummyData() {

        studentCombo.addItem("Student 1");
        studentCombo.addItem("Student 2");

        courseCombo.addItem("CS101 - Programming");
        courseCombo.addItem("IT202 - Networking");
    }
}