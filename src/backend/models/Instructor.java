package backend.models;

public class Instructor {
    private int instructorId;
    private String firstName;
    private String middleName;
    private String lastName;
    private int departmentId;

    public Instructor() {}

    public Instructor(
            int instructorId,
            String firstName,
            String middleName,
            String lastName,
            int departmentId
    ) {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.departmentId = departmentId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
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

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
