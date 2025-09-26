package game;

public class Cell {
    public int x;
    public int y;
    public CellEntityType type;
    public boolean status;

    public Cell() {
        this.x = 0;
        this.y = 0;
        this.status = false;
        this.type = null;
    }

    public Cell(int x, int y, CellEntityType type, boolean status) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.status = status;
    }

    public Cell(int x, int y) {
        this(x, y, CellEntityType.VOID, false);
    }

    public String toString() {
        if (this.type == CellEntityType.PLAYER)
            return "P";
        if (!this.status) {
            return "N";
        }
        return "V";
    }
}
