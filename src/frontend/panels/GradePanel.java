package frontend.panels;

import backend.dao.GradeDAO;
import backend.dao.EnrollmentDAO;
import backend.models.Grade;
import backend.models.Enrollment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GradePanel extends JPanel {

    private JTextField searchField;

    private JButton searchButton;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton refreshButton;

    private JTable gradeTable;
    private DefaultTableModel tableModel;

    public GradePanel() {

        setLayout(new BorderLayout(10, 10));

        // ================= SEARCH PANEL =================
        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));

        searchField = new JTextField();
        searchButton = new JButton("Search");

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        add(searchPanel, BorderLayout.NORTH);

        // ================= TABLE =================
        String[] columnNames = {
                "ID",
                "Final Grade",
                "Remarks",
                "Enrollment ID"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        gradeTable = new JTable(tableModel);
        gradeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(gradeTable), BorderLayout.CENTER);

        // ================= BUTTON PANEL =================
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

        // ================= ACTIONS =================
        searchButton.addActionListener(e -> searchGrades());
        refreshButton.addActionListener(e -> loadGrades());
        addButton.addActionListener(e -> addGrade());
        editButton.addActionListener(e -> editGrade());
        deleteButton.addActionListener(e -> deleteGrade());

        loadGrades();
    }

    // ================= ADD =================
    private void addGrade() {

        JTextField finalGradeField = new JTextField();
        JTextField remarksField = new JTextField();

        JComboBox<Enrollment> enrollmentCombo = new JComboBox<>();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

        for (Enrollment enrollment : enrollmentDAO.getAll()) {
            enrollmentCombo.addItem(enrollment);
        }

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        formPanel.add(new JLabel("Final Grade:"));
        formPanel.add(finalGradeField);

        formPanel.add(new JLabel("Remarks:"));
        formPanel.add(remarksField);

        formPanel.add(new JLabel("Enrollment:"));
        formPanel.add(enrollmentCombo);

        int dialogResult = JOptionPane.showConfirmDialog(
                this,
                formPanel,
                "Add Grade",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (dialogResult != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            Grade grade = new Grade();

            grade.setFinalGrade(Double.parseDouble(finalGradeField.getText().trim()));
            grade.setRemarks(remarksField.getText().trim());

            Enrollment selectedEnrollment =
                    (Enrollment) enrollmentCombo.getSelectedItem();

            grade.setEnrollmentId(selectedEnrollment.getEnrollmentId());

            GradeDAO gradeDAO = new GradeDAO();
            gradeDAO.create(grade);

            JOptionPane.showMessageDialog(this, "Grade added successfully.");
            loadGrades();

        } catch (Exception exception) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to add grade.\n" + exception.getMessage()
            );
        }
    }

    // ================= EDIT =================
    private void editGrade() {

        int selectedRow = gradeTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a grade.");
            return;
        }

        int gradeId = (int) tableModel.getValueAt(selectedRow, 0);

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

        JTextField enrollmentIdField =
                new JTextField(String.valueOf(grade.getEnrollmentId()));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        formPanel.add(new JLabel("Final Grade:"));
        formPanel.add(finalGradeField);

        formPanel.add(new JLabel("Remarks:"));
        formPanel.add(remarksField);

        formPanel.add(new JLabel("Enrollment ID:"));
        formPanel.add(enrollmentIdField);

        int dialogResult = JOptionPane.showConfirmDialog(
                this,
                formPanel,
                "Edit Grade",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (dialogResult != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            grade.setFinalGrade(Double.parseDouble(finalGradeField.getText().trim()));
            grade.setRemarks(remarksField.getText().trim());
            grade.setEnrollmentId(Integer.parseInt(enrollmentIdField.getText().trim()));

            gradeDAO.update(grade);

            JOptionPane.showMessageDialog(this, "Grade updated successfully.");
            loadGrades();

        } catch (Exception exception) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update grade.\n" + exception.getMessage()
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
        List<Grade> gradeList = gradeDAO.getAll();

        if (gradeList == null) return;

        for (Grade grade : gradeList) {

            tableModel.addRow(new Object[]{
                    grade.getGradeId(),
                    grade.getFinalGrade(),
                    grade.getRemarks(),
                    grade.getEnrollmentId()
            });
        }
    }

    // ================= SEARCH =================
    private void searchGrades() {

        String searchInput = searchField.getText().trim();

        if (searchInput.isEmpty()) {
            loadGrades();
            return;
        }

        try {

            int gradeId = Integer.parseInt(searchInput);

            GradeDAO gradeDAO = new GradeDAO();
            Grade grade = gradeDAO.getById(gradeId);

            tableModel.setRowCount(0);

            if (grade != null) {

                tableModel.addRow(new Object[]{
                        grade.getGradeId(),
                        grade.getFinalGrade(),
                        grade.getRemarks(),
                        grade.getEnrollmentId()
                });

            } else {
                JOptionPane.showMessageDialog(this, "Grade not found.");
            }

        } catch (NumberFormatException exception) {

            JOptionPane.showMessageDialog(this, "Please enter a valid Grade ID.");
        }
    }
}