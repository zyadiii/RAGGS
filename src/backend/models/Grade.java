package backend.models;

public class Grade {
    private int gradeId;
    private double finalGrade;
    private String remarks;
    private int enrollmentId;

    public Grade() {}

    public Grade(
            int gradeId,
            double finalGrade,
            String remarks,
            int enrollmentId
    ) {
        this.gradeId = gradeId;
        this.finalGrade = finalGrade;
        this.remarks = remarks;
        this.enrollmentId = enrollmentId;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }
}
