package frontend.panels;

import backend.dao.CourseDAO;
import backend.models.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CoursePanel extends JPanel {

    private JTextField searchField;

    private JButton searchButton;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton refreshButton;

    private JTable courseTable;
    private DefaultTableModel tableModel;

    public CoursePanel() {

        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        searchField = new JTextField();
        searchButton = new JButton("Search");

        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        String[] columns = {
                "ID",
                "Course Code",
                "Course Name",
                "Units"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        courseTable = new JTable(tableModel);
        courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(courseTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchCourses());
        refreshButton.addActionListener(e -> loadCourses());
        addButton.addActionListener(e -> addCourse());
        editButton.addActionListener(e -> editCourse());
        deleteButton.addActionListener(e -> deleteCourse());

        loadCourses();
    }

    private void addCourse() {

        JTextField codeField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField unitsField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Course Code:"));
        panel.add(codeField);

        panel.add(new JLabel("Course Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Units:"));
        panel.add(unitsField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Course",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;

        try {
            Course course = new Course();

            course.setCourseCode(codeField.getText().trim());
            course.setCourseName(nameField.getText().trim());
            course.setUnits(Integer.parseInt(unitsField.getText().trim()));

            new CourseDAO().create(course);

            JOptionPane.showMessageDialog(this, "Course added successfully.");
            loadCourses();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Failed to add course.\n" + ex.getMessage());
        }
    }

    private void editCourse() {

        int row = courseTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course.");
            return;
        }

        int courseId = (int) tableModel.getValueAt(row, 0);

        CourseDAO dao = new CourseDAO();
        Course course = dao.getById(courseId);

        if (course == null) {
            JOptionPane.showMessageDialog(this, "Course not found.");
            return;
        }

        JTextField codeField = new JTextField(course.getCourseCode());
        JTextField nameField = new JTextField(course.getCourseName());
        JTextField unitsField = new JTextField(String.valueOf(course.getUnits()));

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Course Code:"));
        panel.add(codeField);

        panel.add(new JLabel("Course Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Units:"));
        panel.add(unitsField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Course",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;

        try {
            course.setCourseCode(codeField.getText().trim());
            course.setCourseName(nameField.getText().trim());
            course.setUnits(Integer.parseInt(unitsField.getText().trim()));

            dao.update(course);

            JOptionPane.showMessageDialog(this, "Course updated successfully.");
            loadCourses();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Failed to update course.\n" + ex.getMessage());
        }
    }

    private void deleteCourse() {

        int row = courseTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course.");
            return;
        }

        int courseId = (int) tableModel.getValueAt(row, 0);

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Delete selected course?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != JOptionPane.YES_OPTION) return;

        try {
            new CourseDAO().delete(courseId);

            JOptionPane.showMessageDialog(this, "Course deleted successfully.");
            loadCourses();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Failed to delete course.\n" + ex.getMessage());
        }
    }

    private void loadCourses() {

        tableModel.setRowCount(0);

        List<Course> courses = new CourseDAO().getAll();

        for (Course course : courses) {
            tableModel.addRow(new Object[]{
                    course.getCourseId(),
                    course.getCourseCode(),
                    course.getCourseName(),
                    course.getUnits()
            });
        }
    }

    private void searchCourses() {

        String input = searchField.getText().trim();

        if (input.isEmpty()) {
            loadCourses();
            return;
        }

        try {
            int courseId = Integer.parseInt(input);

            Course course = new CourseDAO().getById(courseId);

            tableModel.setRowCount(0);

            if (course != null) {
                tableModel.addRow(new Object[]{
                        course.getCourseId(),
                        course.getCourseCode(),
                        course.getCourseName(),
                        course.getUnits()
                });
            } else {
                JOptionPane.showMessageDialog(this, "Course not found.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid Course ID.");
        }
    }
}