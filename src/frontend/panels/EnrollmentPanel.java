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
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton refreshButton;

    private JTable enrollmentTable;
    private DefaultTableModel tableModel;

    public EnrollmentPanel() {

        setLayout(new BorderLayout(10, 10));

        // ================= SEARCH PANEL =================
        JPanel topPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        searchField = new JTextField();
        searchButton = new JButton("Search");

        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // ================= TABLE =================
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
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };

        enrollmentTable = new JTable(tableModel);

        enrollmentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(enrollmentTable);

        add(scrollPane, BorderLayout.CENTER);

        // ================= BUTTON PANEL =================
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT)
        );

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // ================= ACTIONS =================
        searchButton.addActionListener(
                e -> searchEnrollments()
        );

        refreshButton.addActionListener(
                e -> loadEnrollments()
        );

        addButton.addActionListener(
                e -> addEnrollment()
        );

        editButton.addActionListener(
                e -> editEnrollment()
        );

        deleteButton.addActionListener(
                e -> deleteEnrollment()
        );

        loadEnrollments();
    }

    private void addEnrollment() {
        JTextField dateField = new JTextField();
        JComboBox<String> semesterCombo =
            new JComboBox<>(new String[]{"1st Semester", "2nd Semester", "Summer Semester"});

        JComboBox<String> schoolYearCombo = new JComboBox<>();
        for (int year = 2026; year <= 2035; year++){
            schoolYearCombo.addItem(year + "-" + (year + 1));
        }

        JComboBox<Student> studentCombo = new JComboBox<>();
        StudentDAO studentDAO = new StudentDAO();
        for (Student student : studentDAO.getAll()) {
            studentCombo.addItem(student);
        }    

        JComboBox<Course> courseCombo = new JComboBox<>();
        CourseDAO courseDAO = new CourseDAO();
        for (Course course : courseDAO.getAll()) {
            courseCombo.addItem(course);
        }    

        JPanel panel = new JPanel(
            new GridLayout(0, 2, 5, 5)
        );

        panel.add(new JLabel("Enrollment Date:"));
        panel.add(dateField);

        panel.add(new JLabel("School Year:"));
        panel.add(schoolYearCombo);

        panel.add(new JLabel("Semester:"));
        panel.add(semesterCombo);

        panel.add(new JLabel("Student ID:"));
        panel.add(studentCombo);

        panel.add(new JLabel("Course ID:"));
        panel.add(courseCombo);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Enrollment",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            Enrollment enrollment =
                    new Enrollment();

            enrollment.setEnrollmentDate(
                    dateField.getText().trim()
            );

            Student selectedStudent =
        (Student) studentCombo.getSelectedItem();

        Course selectedCourse =
                (Course) courseCombo.getSelectedItem();

        enrollment.setSchoolYear(
                (String) schoolYearCombo.getSelectedItem()
        );

        enrollment.setSemester(
            (String) semesterCombo.getSelectedItem()
        );

        enrollment.setStudentId(
                selectedStudent.getStudentId()
        );

        enrollment.setCourseId(
                selectedCourse.getCourseId()
        );

            EnrollmentDAO dao =
                    new EnrollmentDAO();

            dao.create(enrollment);

            JOptionPane.showMessageDialog(
                    this,
                    "Enrollment added successfully."
            );

            loadEnrollments();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to add enrollment.\n"
                            + ex.getMessage()
            );
        }
    }

    private void editEnrollment() {

        int row =
                enrollmentTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an enrollment."
            );

            return;
        }

        int enrollmentId =
                (int) tableModel.getValueAt(row, 0);

        EnrollmentDAO dao =
                new EnrollmentDAO();

        Enrollment enrollment =
                dao.getById(enrollmentId);

        if (enrollment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enrollment not found."
            );

            return;
        }

        JTextField dateField =
                new JTextField(
                        enrollment.getEnrollmentDate()
                );

        JTextField schoolYearField =
                new JTextField(
                        enrollment.getSchoolYear()
                );

        JComboBox<String> semesterCombo =
            new JComboBox<>(
                new String[]{
                    "1st Semester",
                    "2nd Semester",
                    "Summer Semester"
                }
            );

        semesterCombo.setSelectedItem(
            enrollment.getSemester()
        );

        JTextField studentIdField =
                new JTextField(
                        String.valueOf(
                                enrollment.getStudentId()
                        )
                );

        JTextField courseIdField =
                new JTextField(
                        String.valueOf(
                                enrollment.getCourseId()
                        )
                );

        JPanel panel = new JPanel(
                new GridLayout(0, 2, 5, 5)
        );

        panel.add(new JLabel("Enrollment Date:"));
        panel.add(dateField);

        panel.add(new JLabel("School Year:"));
        panel.add(schoolYearField);

        panel.add(new JLabel("Semester:"));
        panel.add(semesterCombo);

        panel.add(new JLabel("Student ID:"));
        panel.add(studentIdField);

        panel.add(new JLabel("Course ID:"));
        panel.add(courseIdField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Enrollment",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            enrollment.setEnrollmentDate(
                dateField.getText().trim()
            );

            enrollment.setSchoolYear(
                schoolYearField.getText().trim()
            );

            enrollment.setSemester(
                (String) semesterCombo.getSelectedItem()
            );

            enrollment.setStudentId(
                Integer.parseInt(
                    studentIdField.getText().trim()
                )
            );

            enrollment.setCourseId(
                Integer.parseInt(
                    courseIdField.getText().trim()
                )
            );

            dao.update(enrollment);

            JOptionPane.showMessageDialog(
                    this,
                    "Enrollment updated successfully."
            );

            loadEnrollments();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update enrollment.\n"
                            + ex.getMessage()
            );
        }
    }

    private void deleteEnrollment() {

        int row =
                enrollmentTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an enrollment."
            );

            return;
        }

        int enrollmentId =
                (int) tableModel.getValueAt(row, 0);

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete selected enrollment?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            EnrollmentDAO dao =
                    new EnrollmentDAO();

            dao.delete(enrollmentId);

            JOptionPane.showMessageDialog(
                    this,
                    "Enrollment deleted successfully."
            );

            loadEnrollments();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete enrollment.\n"
                            + ex.getMessage()
            );
        }
    }

    private void loadEnrollments() {
        tableModel.setRowCount(0);

        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

        StudentDAO studentDAO = new StudentDAO();

        CourseDAO courseDAO = new CourseDAO();

        List<Enrollment> enrollments = enrollmentDAO.getAll();

        for (Enrollment enrollment : enrollments) {
            Student student = studentDAO.getById(
                enrollment.getStudentId()
            );

            Course course = courseDAO.getById(enrollment.getCourseId());

            String studentName = "";
            String courseCode = "";

            if (student != null) {
                studentName = student.getFirstName()
                            + " " + student.getLastName();
            }

            if (course != null) {
                courseCode = course.getCourseCode();
            }

            tableModel.addRow(
                    new Object[]{
                            enrollment.getEnrollmentId(),
                            enrollment.getEnrollmentDate(),
                            enrollment.getSchoolYear(),
                            enrollment.getSemester(),
                            studentName,
                            courseCode
                    }
            );
        }
    }

    private void searchEnrollments() {

        String input =
                searchField.getText().trim();

        if (input.isEmpty()) {

            loadEnrollments();
            return;
        }

        try {

            int enrollmentId =
                    Integer.parseInt(input);

            EnrollmentDAO dao =
                    new EnrollmentDAO();

            Enrollment enrollment =
                    dao.getById(enrollmentId);

            tableModel.setRowCount(0);

            if (enrollment != null) {

                StudentDAO studentDAO = new StudentDAO();

                CourseDAO courseDAO = new CourseDAO();

                Student student = studentDAO.getById(enrollment.getStudentId());

                Course course = courseDAO.getById(enrollment.getCourseId());

                String studentName = "";
                String courseCode = "";

                if (student != null) {
                    studentName =
                            student.getFirstName()
                            + " "
                            + student.getLastName();
                }

                if (course != null) {
                    courseCode =
                            course.getCourseCode();
                }

                tableModel.addRow(
                    new Object[]{
                        enrollment.getEnrollmentId(),
                        enrollment.getEnrollmentDate(),
                        enrollment.getSchoolYear(),
                        enrollment.getSemester(),
                        studentName,
                        courseCode
                    }
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Enrollment not found."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Enrollment ID."
            );
        }
    }
}