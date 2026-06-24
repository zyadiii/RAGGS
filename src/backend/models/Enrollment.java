package backend.models;

public class Enrollment {
    private int enrollmentId;
    private String enrollmentDate;
    private String schoolYear;
    private int semester;
    private int studentId;
    private int courseId;

    public Enrollment() {}

    public Enrollment(
            int enrollmentId,
            String enrollmentDate,
            String schoolYear,
            int semester,
            int studentId,
            int courseId
    ) {
        this.enrollmentId = enrollmentId;
        this.enrollmentDate = enrollmentDate;
        this.schoolYear = schoolYear;
        this.semester = semester;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
