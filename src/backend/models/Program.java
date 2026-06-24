package backend.models;

public class Program {
    private int programId;
    private String programName;
    private int departmentId;

    public Program() {}

    public Program(int programId, String programName, int departmentId) {
        this.programId = programId;
        this.programName = programName;
        this.departmentId = departmentId;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
