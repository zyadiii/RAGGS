package frontend.panels;

import javax.swing.*;
import java.awt.*;

public class GradePanel extends JPanel {

    private JComboBox<String> enrollmentCombo;
    private JTextField gradeField;
    private JTextField remarksField;

    private JButton saveButton;
    private JButton clearButton;

    public GradePanel() {

        setLayout(new BorderLayout(10, 10));

        // ================= FORM PANEL =================
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("Enrollment:"));
        enrollmentCombo = new JComboBox<>();
        formPanel.add(enrollmentCombo);

        formPanel.add(new JLabel("Final Grade:"));
        gradeField = new JTextField();
        formPanel.add(gradeField);

        formPanel.add(new JLabel("Remarks:"));
        remarksField = new JTextField();
        formPanel.add(remarksField);

        saveButton = new JButton("Save Grade");
        clearButton = new JButton("Clear");

        formPanel.add(saveButton);
        formPanel.add(clearButton);

        add(formPanel, BorderLayout.NORTH);

        // ================= TABLE (PLACEHOLDER) =================
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        // ================= ACTIONS =================
        saveButton.addActionListener(e -> saveGrade());
        clearButton.addActionListener(e -> clearFields());

        // TEMP DATA until DAO is ready
        loadDummyData();
    }

    private void saveGrade() {

        if (enrollmentCombo.getSelectedItem() == null ||
            gradeField.getText().trim().isEmpty() ||
            remarksField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all fields",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            double grade = Double.parseDouble(gradeField.getText().trim());

            String enrollment = enrollmentCombo.getSelectedItem().toString();

            JOptionPane.showMessageDialog(
                    this,
                    "Grade Saved (placeholder)\n" +
                            enrollment + "\nGrade: " + grade,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Grade must be a number",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearFields() {

        enrollmentCombo.setSelectedIndex(-1);
        gradeField.setText("");
        remarksField.setText("");
    }

    // temporary data until DAO is connected
    private void loadDummyData() {

        enrollmentCombo.addItem("Student1 - CS101");
        enrollmentCombo.addItem("Student2 - IT202");
    }
}