package backend.models;

public class Course {
     private int courseId;
    private String courseCode;
    private String courseName;
    private int units;

    public Course() {}

    public Course(int courseId, String courseCode, String courseName, int units) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.units = units;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return courseCode + " - " +
            courseName;
    }
}
