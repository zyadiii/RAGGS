package backend.models;

public class Block {
    private int blockId;
    private String blockName;
    private int programId;

    public Block() {}

    public Block(int blockId, String blockName, int programId) {
        this.blockId = blockId;
        this.blockName = blockName;
        this.programId = programId;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }
}
