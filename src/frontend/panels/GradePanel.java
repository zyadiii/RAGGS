package frontend.panels;

import backend.dao.GradeDAO;
import backend.dao.StudentDAO;
import backend.dao.CourseDAO;
import backend.dao.EnrollmentDAO;
import backend.models.Grade;
import backend.models.Student;
import backend.models.Course;
import backend.models.Enrollment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GradePanel extends JPanel {

    private JTextField searchField;
    private JButton searchButton;

    private JTable gradeTable;
    private DefaultTableModel tableModel;

    public GradePanel() {

        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        searchField = new JTextField();
        searchButton = new JButton("Search");

        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {
                "ID",
                "Final Grade",
                "Remarks",
                "Enrollment"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        gradeTable = new JTable(tableModel);
        gradeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(gradeTable);
        add(scrollPane, BorderLayout.CENTER);

        CRUDButtonPanel buttonPanel = new CRUDButtonPanel();

        add(buttonPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchGrades());
        buttonPanel.getRefreshButton().addActionListener(e -> loadGrades());
        buttonPanel.getAddButton().addActionListener(e -> addGrade());
        buttonPanel.getEditButton().addActionListener(e -> editGrade());
        buttonPanel.getDeleteButton().addActionListener(e -> deleteGrade());

        loadGrades();
    }

    // ================= ADD =================
    private void addGrade() {

        JTextField finalGradeField = new JTextField();
        JTextField remarksField = new JTextField();

        JComboBox<Enrollment> enrollmentCombo = new JComboBox<>();

        JTextField studentField = new JTextField();
        studentField.setEditable(false);

        JTextField courseField = new JTextField();
        courseField.setEditable(false);

        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();

        for (Enrollment enrollment : enrollmentDAO.getAll()) {
            enrollmentCombo.addItem(enrollment);
        }

        enrollmentCombo.addActionListener(e -> {
            Enrollment selected = (Enrollment) enrollmentCombo.getSelectedItem();

            if (selected != null) {
                Student student = studentDAO.getById(selected.getStudentId());
                Course course = courseDAO.getById(selected.getCourseId());

                studentField.setText(student != null
                        ? student.getFirstName() + " " + student.getLastName()
                        : "");

                courseField.setText(course != null
                        ? course.getCourseCode() + " - " + course.getCourseName()
                        : "");
            }
        });

        if (enrollmentCombo.getItemCount() > 0) {
            enrollmentCombo.setSelectedIndex(0);
        }

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Final Grade:"));
        panel.add(finalGradeField);

        panel.add(new JLabel("Remarks:"));
        panel.add(remarksField);

        panel.add(new JLabel("Enrollment:"));
        panel.add(enrollmentCombo);

        panel.add(new JLabel("Student:"));
        panel.add(studentField);

        panel.add(new JLabel("Course:"));
        panel.add(courseField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Grade",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;

        try {
            Grade grade = new Grade();

            grade.setFinalGrade(Double.parseDouble(finalGradeField.getText().trim()));
            grade.setRemarks(remarksField.getText().trim());

            Enrollment selectedEnrollment = (Enrollment) enrollmentCombo.getSelectedItem();

            if (selectedEnrollment != null) {
                grade.setEnrollmentId(selectedEnrollment.getEnrollmentId());
            }

            new GradeDAO().create(grade);

            JOptionPane.showMessageDialog(this, "Grade added successfully.");
            loadGrades();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Failed to add grade.\n" + ex.getMessage()
            );
        }
    }

    // ================= EDIT =================
    private void editGrade() {

        int row = gradeTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a grade.");
            return;
        }

        int gradeId = (int) tableModel.getValueAt(row, 0);

        GradeDAO gradeDAO = new GradeDAO();
        Grade grade = gradeDAO.getById(gradeId);

        if (grade == null) {
            JOptionPane.showMessageDialog(this, "Grade not found.");
            return;
        }

        JTextField finalGradeField =
                new JTextField(String.valueOf(grade.getFinalGrade()));

        JTextField remarksField =
                new JTextField(grade.getRemarks());

        JComboBox<Enrollment> enrollmentCombo = new JComboBox<>();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

        for (Enrollment enrollment : enrollmentDAO.getAll()) {
            enrollmentCombo.addItem(enrollment);

            if (enrollment.getEnrollmentId() == grade.getEnrollmentId()) {
                enrollmentCombo.setSelectedItem(enrollment);
            }
        }

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Final Grade:"));
        panel.add(finalGradeField);

        panel.add(new JLabel("Remarks:"));
        panel.add(remarksField);

        panel.add(new JLabel("Enrollment:"));
        panel.add(enrollmentCombo);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Grade",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;

        try {

            grade.setFinalGrade(
                    Double.parseDouble(finalGradeField.getText().trim())
            );

            grade.setRemarks(
                    remarksField.getText().trim()
            );

            Enrollment selectedEnrollment =
                    (Enrollment) enrollmentCombo.getSelectedItem();

            if (selectedEnrollment != null) {
                grade.setEnrollmentId(selectedEnrollment.getEnrollmentId());
            }

            gradeDAO.update(grade);

            JOptionPane.showMessageDialog(this, "Grade updated successfully.");
            loadGrades();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update grade.\n" + ex.getMessage()
            );
        }
    }

    // ================= DELETE =================
    private void deleteGrade() {

        int selectedRow = gradeTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a grade.");
            return;
        }

        int gradeId = (int) tableModel.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete selected grade?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            GradeDAO gradeDAO = new GradeDAO();
            gradeDAO.delete(gradeId);

            JOptionPane.showMessageDialog(this, "Grade deleted successfully.");
            loadGrades();

        } catch (Exception exception) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete grade.\n" + exception.getMessage()
            );
        }
    }

    // ================= LOAD =================
    private void loadGrades() {

        tableModel.setRowCount(0);

        GradeDAO gradeDAO = new GradeDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();

        List<Grade> grades = gradeDAO.getAll();

        for (Grade grade : grades) {

            Enrollment enrollment =
                    enrollmentDAO.getById(grade.getEnrollmentId());

            String enrollmentInfo = "";

            if (enrollment != null) {

                Student student =
                        studentDAO.getById(enrollment.getStudentId());

                Course course =
                        courseDAO.getById(enrollment.getCourseId());

                String studentName = student != null
                        ? student.getFirstName() + " " + student.getLastName()
                        : "";

                String courseCode = course != null
                        ? course.getCourseCode()
                        : "";

                enrollmentInfo = studentName + " - " + courseCode;
            }

            tableModel.addRow(new Object[]{
                    grade.getGradeId(),
                    grade.getFinalGrade(),
                    grade.getRemarks(),
                    enrollmentInfo
            });
        }
    }

    // ================= SEARCH =================
    private void searchGrades() {

        String input = searchField.getText().trim();

        if (input.isEmpty()) {
            loadGrades();
            return;
        }

        try {

            int gradeId = Integer.parseInt(input);

            Grade grade = new GradeDAO().getById(gradeId);

            tableModel.setRowCount(0);

            if (grade == null) {
                JOptionPane.showMessageDialog(this, "Grade not found.");
                return;
            }

            Enrollment enrollment =
                    new EnrollmentDAO().getById(grade.getEnrollmentId());

            String enrollmentInfo = "";

            if (enrollment != null) {

                Student student =
                        new StudentDAO().getById(enrollment.getStudentId());

                Course course =
                        new CourseDAO().getById(enrollment.getCourseId());

                String studentName = student != null
                        ? student.getFirstName() + " " + student.getLastName()
                        : "";

                String courseCode = course != null
                        ? course.getCourseCode()
                        : "";

                enrollmentInfo = studentName + " - " + courseCode;
            }

            tableModel.addRow(new Object[]{
                    grade.getGradeId(),
                    grade.getFinalGrade(),
                    grade.getRemarks(),
                    enrollmentInfo
            });

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Grade ID."
            );
        }
    }
}