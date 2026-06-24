package frontend.panels;

import javax.swing.*;
import java.awt.*;

public class CoursePanel extends JPanel {

    private JTextField courseCodeField;
    private JTextField courseNameField;
    private JTextField unitsField;

    private JButton addButton;
    private JButton clearButton;

    public CoursePanel() {

        setLayout(new BorderLayout(10, 10));

        // ================= FORM PANEL =================
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("Course Code:"));
        courseCodeField = new JTextField();
        formPanel.add(courseCodeField);

        formPanel.add(new JLabel("Course Name:"));
        courseNameField = new JTextField();
        formPanel.add(courseNameField);

        formPanel.add(new JLabel("Units:"));
        unitsField = new JTextField();
        formPanel.add(unitsField);

        addButton = new JButton("Add Course");
        clearButton = new JButton("Clear");

        formPanel.add(addButton);
        formPanel.add(clearButton);

        add(formPanel, BorderLayout.NORTH);

        // ================= PLACEHOLDER TABLE =================
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        // ================= BUTTON ACTIONS =================
        addButton.addActionListener(e -> addCourse());
        clearButton.addActionListener(e -> clearFields());
    }

    private void addCourse() {

        String code = courseCodeField.getText().trim();
        String name = courseNameField.getText().trim();
        String unitsText = unitsField.getText().trim();

        if (code.isEmpty() || name.isEmpty() || unitsText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            int units = Integer.parseInt(unitsText);

            JOptionPane.showMessageDialog(
                    this,
                    "Course Added (placeholder only)\n" +
                            code + " - " + name + " (" + units + ")",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Units must be a number",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearFields() {

        courseCodeField.setText("");
        courseNameField.setText("");
        unitsField.setText("");
    }
}