package frontend.panels;

import backend.dao.ProgramDAO;
import backend.dao.StudentDAO;
import backend.models.Program;
import backend.models.Student;
import frontend.components.CRUDButtonComponent;
import frontend.components.SearchBarComponent;
import frontend.utilities.ThemeUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentPanel extends JPanel {

    private JTextField searchField;

    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentPanel() {
        setLayout(new BorderLayout(10, 10));

        // ================= TOP PANEL =================
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setOpaque(false);

        JLabel title = new JLabel("STUDENTS");
        ThemeUtil.styleTitle(title);
        topPanel.add(title, BorderLayout.WEST);

        SearchBarComponent searchBar = new SearchBarComponent();
        topPanel.add(searchBar, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // ================= TABLE =================
        String[] columns = {
                "ID",
                "First Name",
                "Middle Name",
                "Last Name",
                "Gender",
                "Contact No.",
                "Program",
                "Status"
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

        // ================= BUTTONS =================
        CRUDButtonComponent buttonPanel = new CRUDButtonComponent();
        add(buttonPanel, BorderLayout.SOUTH);

        searchBar.getSearchButton().addActionListener(e -> searchStudents());

        searchField = searchBar.getSearchField();

        buttonPanel.getRefreshButton().addActionListener(e -> loadStudents());
        buttonPanel.getAddButton().addActionListener(e -> addStudent());
        buttonPanel.getEditButton().addActionListener(e -> editStudent());
        buttonPanel.getDeleteButton().addActionListener(e -> deleteStudent());

        loadStudents();
    }

    private void addStudent() {
        JTextField firstNameField = new JTextField();
        JTextField middleNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField birthDateField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField contactNoField = new JTextField();
        JTextField citizenshipField = new JTextField();

        JComboBox<String> statusCombo = new JComboBox<>(
                new String[]{"Active", "Inactive", "Graduated", "Dropped"}
        );

        JComboBox<String> genderCombo = new JComboBox<>(
                new String[]{"Male", "Female"}
        );

        JComboBox<Program> programCombo = new JComboBox<>();

        ProgramDAO programDAO = new ProgramDAO();
        
        for (Program p : programDAO.getAll()) {
            programCombo.addItem(p);
        }

        JPanel panel = new JPanel(new GridLayout(0, 2));

        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);

        panel.add(new JLabel("Middle Name:"));
        panel.add(middleNameField);

        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);

        panel.add(new JLabel("Gender:"));
        panel.add(genderCombo);

        panel.add(new JLabel("Birth Date:"));
        panel.add(birthDateField);

        panel.add(new JLabel("Address:"));
        panel.add(addressField);

        panel.add(new JLabel("Contact No:"));
        panel.add(contactNoField);

        panel.add(new JLabel("Citizenship:"));
        panel.add(citizenshipField);

        panel.add(new JLabel("Program:"));
        panel.add(programCombo);

        panel.add(new JLabel("Status:"));
        panel.add(statusCombo);
        
        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Student",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;

        try {
            Student student = new Student();

            student.setFirstName(firstNameField.getText());
            student.setMiddleName(middleNameField.getText());
            student.setLastName(lastNameField.getText());
            student.setBirthDate(birthDateField.getText());
            student.setAddress(addressField.getText());
            student.setContactNo(contactNoField.getText());
            student.setCitizenship(citizenshipField.getText());
            student.setStatus((String) statusCombo.getSelectedItem());
            student.setGender((String) genderCombo.getSelectedItem());
            Program selectedProgram = (Program) programCombo.getSelectedItem();

            if (selectedProgram != null) {
                student.setProgramId(selectedProgram.getProgramId());
            }

            new StudentDAO().create(student);

            JOptionPane.showMessageDialog(this, "Student added successfully.");
            loadStudents();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Failed to add student.\n" + ex.getMessage());
        }
    }

    private void editStudent() {
        int row = studentTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student.");
            return;
        }

        int studentId = (int) tableModel.getValueAt(row, 0);

        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getById(studentId);

        if (student == null) {
            JOptionPane.showMessageDialog(this, "Student not found.");
            return;
        }

        JTextField firstNameField = new JTextField(student.getFirstName());
        JTextField middleNameField = new JTextField(student.getMiddleName());
        JTextField lastNameField = new JTextField(student.getLastName());
        JTextField birthDateField = new JTextField(student.getBirthDate());
        JTextField addressField = new JTextField(student.getAddress());
        JTextField contactNoField = new JTextField(student.getContactNo());
        JTextField citizenshipField = new JTextField(student.getCitizenship());

        JComboBox<String> statusCombo = new JComboBox<>(
                new String[]{"Active", "Inactive", "Graduated", "Dropped"}
        );

        JComboBox<String> genderCombo = new JComboBox<>(
            new String[]{"Male", "Female"}
        );

        JComboBox<Program> programCombo = new JComboBox<>();

        ProgramDAO programDAO = new ProgramDAO();

        for (Program p : programDAO.getAll()) {
            programCombo.addItem(p);

            if (p.getProgramId() == student.getProgramId()) {
                programCombo.setSelectedItem(p);
            }
        }

        statusCombo.setSelectedItem(student.getStatus());
        genderCombo.setSelectedItem(student.getGender());

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);

        panel.add(new JLabel("Middle Name:"));
        panel.add(middleNameField);

        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);

        panel.add(new JLabel("Gender:"));
        panel.add(genderCombo);

        panel.add(new JLabel("Birth Date:"));
        panel.add(birthDateField);

        panel.add(new JLabel("Address:"));
        panel.add(addressField);

        panel.add(new JLabel("Contact No:"));
        panel.add(contactNoField);

        panel.add(new JLabel("Citizenship:"));
        panel.add(citizenshipField);

        panel.add(new JLabel("Program:"));
        panel.add(programCombo);

        panel.add(new JLabel("Status:"));
        panel.add(statusCombo);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Student",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;

        try {
            student.setFirstName(firstNameField.getText());
            student.setMiddleName(middleNameField.getText());
            student.setLastName(lastNameField.getText());
            student.setBirthDate(birthDateField.getText());
            student.setAddress(addressField.getText());
            student.setContactNo(contactNoField.getText());
            student.setCitizenship(citizenshipField.getText());
            student.setStatus((String) statusCombo.getSelectedItem());
            student.setGender((String) genderCombo.getSelectedItem());
            Program selectedProgram = (Program) programCombo.getSelectedItem();

            if (selectedProgram != null) {
                student.setProgramId(selectedProgram.getProgramId());
            }

            studentDAO.update(student);

            JOptionPane.showMessageDialog(this, "Student updated successfully.");
            loadStudents();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Failed to update student.\n" + ex.getMessage());
        }
    }

    private void deleteStudent() {
        int row = studentTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student.");
            return;
        }

        int studentId = (int) tableModel.getValueAt(row, 0);

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Delete selected student?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != JOptionPane.YES_OPTION) return;

        try {
            new StudentDAO().delete(studentId);
            JOptionPane.showMessageDialog(this, "Student deleted successfully.");
            loadStudents();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Failed to delete student.\n" + ex.getMessage());
        }
    }

    private void loadStudents() {
        tableModel.setRowCount(0);

        List<Student> students = new StudentDAO().getAll();
        if (students == null) return;

        for (Student student : students) {
            if (student == null) continue;

            tableModel.addRow(new Object[]{
                student.getStudentId(),
                student.getFirstName(),
                student.getMiddleName(),
                student.getLastName(),
                student.getGender(),
                student.getContactNo(),
                student.getProgramName(),
                student.getStatus()
            });
        }
    }

    private void searchStudents() {

        String input = searchField.getText().trim();

        if (input.isEmpty()) {
            loadStudents();
            return;
        }

        try {
            int studentId = Integer.parseInt(input);

            Student student = new StudentDAO().getById(studentId);

            tableModel.setRowCount(0);

            if (student != null) {
                tableModel.addRow(new Object[]{
                    student.getStudentId(),
                    student.getFirstName(),
                    student.getMiddleName(),
                    student.getLastName(),
                    student.getGender(),
                    student.getContactNo(),
                    student.getProgramId(),
                    student.getStatus()
                });
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid Student ID.");
        }
    }
}