package frontend.panels;

import backend.dao.StudentDAO;
import backend.models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentPanel extends JPanel {

    private JTextField searchField;

    private JButton searchButton;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton refreshButton;

    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentPanel() {

        setLayout(new BorderLayout(10, 10));

        // ================= SEARCH PANEL =================
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        searchField = new JTextField();
        searchButton = new JButton("Search");

        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // ================= TABLE =================
        String[] columns = {
                "ID",
                "First Name",
                "Middle Name",
                "Last Name",
                "Block",
                "Status",
                "Contact No"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(studentTable);

        add(scrollPane, BorderLayout.CENTER);

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
        searchButton.addActionListener(e -> searchStudents());

        refreshButton.addActionListener(e -> loadStudents());

        addButton.addActionListener(e -> addStudent());

        editButton.addActionListener(e -> editStudent());

        deleteButton.addActionListener(e -> deleteStudent());

        loadStudents();
    }

    private void addStudent(){

        JTextField firstNameField = new JTextField();
        JTextField middleNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField birthDateField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField contactNoField = new JTextField();
        JTextField citizenshipField = new JTextField();
        JComboBox<String> statusCombo = new JComboBox<>(
                new String[]{
                        "Continuing",
                        "Regular",
                        "Dropped"
                }
        );
        JTextField blockIdField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));

        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);

        panel.add(new JLabel("Middle Name:"));
        panel.add(middleNameField);

        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);

        panel.add(new JLabel("Birth Date:"));
        panel.add(birthDateField);

        panel.add(new JLabel("Address:"));
        panel.add(addressField);

        panel.add(new JLabel("Contact No:"));
        panel.add(contactNoField);

        panel.add(new JLabel("Citizenship:"));
        panel.add(citizenshipField);

        panel.add(new JLabel("Status:"));
        panel.add(statusCombo);

        panel.add(new JLabel("Block ID:"));
        panel.add(blockIdField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Student",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            Student student = new Student();

            student.setFirstName(firstNameField.getText());
            student.setMiddleName(middleNameField.getText());
            student.setLastName(lastNameField.getText());
            student.setBirthDate(birthDateField.getText());
            student.setAddress(addressField.getText());
            student.setContactNo(contactNoField.getText());
            student.setCitizenship(citizenshipField.getText());
            student.setStatus(
                    statusCombo.getSelectedItem().toString()
            );
            student.setBlockId(
                    Integer.parseInt(blockIdField.getText())
            );

            StudentDAO studentDAO = new StudentDAO();

            studentDAO.create(student);

            JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully."
            );

            loadStudents();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to add student.\n" + ex.getMessage()
            );
        }
    }
    
    private void editStudent(){
        int row = studentTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student."
            );

            return;
        }

        int studentId = (int) tableModel.getValueAt(row, 0);

        StudentDAO studentDAO = new StudentDAO();

        Student student = studentDAO.getById(studentId);

        if (student == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student not found."
            );

            return;
        }

        JTextField firstNameField =
                new JTextField(student.getFirstName());

        JTextField middleNameField =
                new JTextField(student.getMiddleName());

        JTextField lastNameField =
                new JTextField(student.getLastName());

        JTextField birthDateField =
                new JTextField(student.getBirthDate());

        JTextField addressField =
                new JTextField(student.getAddress());

        JTextField contactField =
                new JTextField(student.getContactNo());

        JTextField citizenshipField =
                new JTextField(student.getCitizenship());

        JTextField blockIdField =
                new JTextField(
                        String.valueOf(student.getBlockId())
                );

        JComboBox<String> statusCombo =
                new JComboBox<>(
                        new String[]{
                                "Continuing",
                                "Regular",
                                "Dropped"
                        }
                );

        statusCombo.setSelectedItem(
                student.getStatus()
        );

        JPanel panel = new JPanel(
                new GridLayout(0, 2, 5, 5)
        );

        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);

        panel.add(new JLabel("Middle Name:"));
        panel.add(middleNameField);

        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);

        panel.add(new JLabel("Birth Date:"));
        panel.add(birthDateField);

        panel.add(new JLabel("Address:"));
        panel.add(addressField);

        panel.add(new JLabel("Contact No:"));
        panel.add(contactField);

        panel.add(new JLabel("Citizenship:"));
        panel.add(citizenshipField);

        panel.add(new JLabel("Block ID:"));
        panel.add(blockIdField);

        panel.add(new JLabel("Status:"));
        panel.add(statusCombo);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Student",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            student.setFirstName(
                    firstNameField.getText()
            );

            student.setMiddleName(
                    middleNameField.getText()
            );

            student.setLastName(
                    lastNameField.getText()
            );

            student.setBirthDate(
                    birthDateField.getText()
            );

            student.setAddress(
                    addressField.getText()
            );

            student.setContactNo(
                    contactField.getText()
            );

            student.setCitizenship(
                    citizenshipField.getText()
            );

            student.setStatus(
                    (String) statusCombo.getSelectedItem()
            );

            student.setBlockId(
                    Integer.parseInt(
                            blockIdField.getText().trim()
                    )
            );

            studentDAO.update(student);

            JOptionPane.showMessageDialog(
                    this,
                    "Student updated successfully."
            );

            loadStudents();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update student.\n"
                            + ex.getMessage()
            );
        }
    }
    
    private void deleteStudent(){
        int row = studentTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student."
            );

            return;
        }

        int studentId = (int) tableModel.getValueAt(row, 0);

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Delete selected student?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            StudentDAO studentDAO = new StudentDAO();

            studentDAO.delete(studentId);

            JOptionPane.showMessageDialog(
                    this,
                    "Student deleted successfully."
            );

            loadStudents();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete student.\n" +
                    ex.getMessage()
            );
        }
    }

    private void loadStudents() {

        tableModel.setRowCount(0);

        StudentDAO studentDAO = new StudentDAO();

        List<Student> students = studentDAO.getAll();

        if (students == null) {
            return;
        }

        for (Student student : students) {
            if (student == null) {
                continue;
            }

            tableModel.addRow(new Object[]{
                    student.getStudentId(),
                    student.getFirstName(),
                    student.getMiddleName(),
                    student.getLastName(),
                    student.getBlockId(),
                    student.getStatus(),
                    student.getContactNo()
            });
        }
    }

    private void searchStudents() {

        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {

            loadStudents();
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Search not implemented yet.\nKeyword: " + keyword
        );
    }
}