package frontend.panels;

import backend.dao.CourseDAO;
import backend.dao.EnrollmentDAO;
import backend.dao.StudentDAO;
import backend.models.Course;
import backend.models.Enrollment;
import backend.models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EnrollmentPanel extends JPanel {

    private JTextField searchField;
    private JButton searchButton;

    private JTable enrollmentTable;
    private DefaultTableModel tableModel;

    public EnrollmentPanel() {

        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        searchField = new JTextField();
        searchButton = new JButton("Search");

        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        String[] columns = {
                "ID",
                "Enrollment Date",
                "School Year",
                "Semester",
                "Student",
                "Course"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        enrollmentTable = new JTable(tableModel);
        enrollmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(enrollmentTable);
        add(scrollPane, BorderLayout.CENTER);

        CRUDButtonPanel buttonPanel = new CRUDButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchEnrollments());
        buttonPanel.getRefreshButton().addActionListener(e -> loadEnrollments());
        buttonPanel.getAddButton().addActionListener(e -> addEnrollment());
        buttonPanel.getEditButton().addActionListener(e -> editEnrollment());
        buttonPanel.getDeleteButton().addActionListener(e -> deleteEnrollment());

        loadEnrollments();
    }

    private void addEnrollment() {

        JTextField dateField = new JTextField();

        JComboBox<String> semesterCombo =
                new JComboBox<>(new String[]{
                        "1st Semester",
                        "2nd Semester",
                        "Summer Semester"
                });

        JComboBox<String> schoolYearCombo = new JComboBox<>();

        for (int year = 2026; year <= 2035; year++) {
            schoolYearCombo.addItem(year + "-" + (year + 1));
        }

        JComboBox<Student> studentCombo = new JComboBox<>();

        for (Student student : new StudentDAO().getAll()) {
            studentCombo.addItem(student);
        }

        JComboBox<Course> courseCombo = new JComboBox<>();

        for (Course course : new CourseDAO().getAll()) {
            courseCombo.addItem(course);
        }

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Enrollment Date:"));
        panel.add(dateField);

        panel.add(new JLabel("School Year:"));
        panel.add(schoolYearCombo);

        panel.add(new JLabel("Semester:"));
        panel.add(semesterCombo);

        panel.add(new JLabel("Student:"));
        panel.add(studentCombo);

        panel.add(new JLabel("Course:"));
        panel.add(courseCombo);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Enrollment",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;

        try {

            Enrollment enrollment = new Enrollment();

            enrollment.setEnrollmentDate(dateField.getText().trim());
            enrollment.setSchoolYear((String) schoolYearCombo.getSelectedItem());
            enrollment.setSemester((String) semesterCombo.getSelectedItem());

            Student student = (Student) studentCombo.getSelectedItem();
            Course course = (Course) courseCombo.getSelectedItem();

            if (student != null) {
                enrollment.setStudentId(student.getStudentId());
            }

            if (course != null) {
                enrollment.setCourseId(course.getCourseId());
            }

            new EnrollmentDAO().create(enrollment);

            JOptionPane.showMessageDialog(this, "Enrollment added successfully.");
            loadEnrollments();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Failed to add enrollment.\n" + ex.getMessage()
            );
        }
    }

    private void editEnrollment() {

        int row = enrollmentTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an enrollment.");
            return;
        }

        int enrollmentId = (int) tableModel.getValueAt(row, 0);

        EnrollmentDAO dao = new EnrollmentDAO();
        Enrollment enrollment = dao.getById(enrollmentId);

        if (enrollment == null) {
            JOptionPane.showMessageDialog(this, "Enrollment not found.");
            return;
        }

        JTextField dateField = new JTextField(enrollment.getEnrollmentDate());

        JComboBox<String> semesterCombo = new JComboBox<>(
                new String[]{
                        "1st Semester",
                        "2nd Semester",
                        "Summer Semester"
                }
        );

        semesterCombo.setSelectedItem(enrollment.getSemester());

        JComboBox<String> schoolYearCombo = new JComboBox<>();

        for (int year = 2026; year <= 2035; year++) {
            schoolYearCombo.addItem(year + "-" + (year + 1));
        }

        schoolYearCombo.setSelectedItem(enrollment.getSchoolYear());

        JComboBox<Student> studentCombo = new JComboBox<>();

        StudentDAO studentDAO = new StudentDAO();

        for (Student student : studentDAO.getAll()) {

            studentCombo.addItem(student);

            if (student.getStudentId() == enrollment.getStudentId()) {
                studentCombo.setSelectedItem(student);
            }
        }

        JComboBox<Course> courseCombo = new JComboBox<>();

        CourseDAO courseDAO = new CourseDAO();

        for (Course course : courseDAO.getAll()) {

            courseCombo.addItem(course);

            if (course.getCourseId() == enrollment.getCourseId()) {
                courseCombo.setSelectedItem(course);
            }
        }

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Enrollment Date:"));
        panel.add(dateField);

        panel.add(new JLabel("School Year:"));
        panel.add(schoolYearCombo);

        panel.add(new JLabel("Semester:"));
        panel.add(semesterCombo);

        panel.add(new JLabel("Student:"));
        panel.add(studentCombo);

        panel.add(new JLabel("Course:"));
        panel.add(courseCombo);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Enrollment",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;

        try {

            enrollment.setEnrollmentDate(dateField.getText().trim());
            enrollment.setSchoolYear((String) schoolYearCombo.getSelectedItem());
            enrollment.setSemester((String) semesterCombo.getSelectedItem());

            Student selectedStudent = (Student) studentCombo.getSelectedItem();
            Course selectedCourse = (Course) courseCombo.getSelectedItem();

            if (selectedStudent != null) {
                enrollment.setStudentId(selectedStudent.getStudentId());
            }

            if (selectedCourse != null) {
                enrollment.setCourseId(selectedCourse.getCourseId());
            }

            dao.update(enrollment);

            JOptionPane.showMessageDialog(this, "Enrollment updated successfully.");
            loadEnrollments();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update enrollment.\n" + ex.getMessage()
            );
        }
    }

    private void deleteEnrollment() {
        int row = enrollmentTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an enrollment.");
            return;
        }

        int enrollmentId = (int) tableModel.getValueAt(row, 0);

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Delete selected enrollment?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != JOptionPane.YES_OPTION) return;

        try {

            new EnrollmentDAO().delete(enrollmentId);

            JOptionPane.showMessageDialog(this, "Enrollment deleted successfully.");
            loadEnrollments();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete enrollment.\n" + ex.getMessage()
            );
        }
    }

    private void loadEnrollments() {
        tableModel.setRowCount(0);

        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();

        List<Enrollment> enrollments = enrollmentDAO.getAll();

        if (enrollments == null) return;

        for (Enrollment enrollment : enrollments) {
            if (enrollment == null) continue;

            Student student = studentDAO.getById(enrollment.getStudentId());
            Course course = courseDAO.getById(enrollment.getCourseId());

            String studentName = student != null
                    ? student.getFirstName() + " " + student.getLastName()
                    : "";

            String courseCode = course != null
                    ? course.getCourseCode()
                    : "";

            tableModel.addRow(new Object[]{
                    enrollment.getEnrollmentId(),
                    enrollment.getEnrollmentDate(),
                    enrollment.getSchoolYear(),
                    enrollment.getSemester(),
                    studentName,
                    courseCode
            });
        }
    }

    private void searchEnrollments() {
        String input = searchField.getText().trim();

        if (input.isEmpty()) {
            loadEnrollments();
            return;
        }

        try {
            int enrollmentId = Integer.parseInt(input);

            Enrollment enrollment = new EnrollmentDAO().getById(enrollmentId);

            tableModel.setRowCount(0);

            if (enrollment == null) {
                JOptionPane.showMessageDialog(this, "Enrollment not found.");
                return;
            }

            Student student = new StudentDAO().getById(enrollment.getStudentId());
            Course course = new CourseDAO().getById(enrollment.getCourseId());

            String studentName = student != null
                    ? student.getFirstName() + " " + student.getLastName()
                    : "";

            String courseCode = course != null
                    ? course.getCourseCode()
                    : "";

            tableModel.addRow(new Object[]{
                    enrollment.getEnrollmentId(),
                    enrollment.getEnrollmentDate(),
                    enrollment.getSchoolYear(),
                    enrollment.getSemester(),
                    studentName,
                    courseCode
            });

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Enrollment ID."
            );
        }
    }
}