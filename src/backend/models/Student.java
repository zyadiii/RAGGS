package backend.models;

public class Student {
    private int studentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthDate;
    private String address;
    private String contactNo;
    private String citizenship;
    private String status;
    private String gender;
    private int program_id;
    private String programName;

    public Student() {}

    public Student(
            int studentId,
            String firstName,
            String middleName,
            String lastName,
            String birthDate,
            String address,
            String contactNo,
            String citizenship,
            String status,
            String gender,
            int program_id
    ) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.contactNo = contactNo;
        this.citizenship = citizenship;
        this.status = status;
        this.gender = gender;
        this.program_id = program_id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getProgramId() {
        return program_id;
    }

    public void setProgramId(int program_id) {
        this.program_id = program_id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    @Override
    public String toString() {
        return studentId + " - " +
            firstName + " " +
            lastName;
    }
}