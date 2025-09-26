package game;

import java.util.ArrayList;
import java.util.Random;
import game.entities.Characters;

import static game.CellEntityType.*;

public class Grid extends ArrayList<ArrayList<Cell>> {
    private final int width;
    private final int height;
    private Characters character;
    private Cell currentCell;
    private static Grid grid;

    private Grid(int width, int height, Cell currentCell) {
        this.width = width;
        this.height = height;
        this.currentCell = currentCell;
    }

    public void setCharacter(Characters character) {
            this.character = character;
    }

    public static Grid generateGrid(int width, int height) {
        Random rand = new Random();
        Cell currentCell = new Cell(rand.nextInt(height), rand.nextInt(width), PLAYER, true);
        grid = new Grid(width, height, currentCell);
        for (int i = 0; i < height; i++) {
            ArrayList<Cell> rows = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                if (currentCell.x == i && currentCell.y == j) {
                    rows.add(currentCell);
                } else {
                    rows.add(new Cell(i, j));
                }
            }
            grid.add(rows);
        }

        //portal set
        grid.replaceCell(PORTAL);


        //sanctuary set
        int min = 2;
        int max = width * height / 4;
        int randomSanctuary = rand.nextInt(max - min) + min;

        while (randomSanctuary >= 0) {
            Cell verify = grid.replaceCell(SANCTUARY);
            if (verify.type == SANCTUARY)
                randomSanctuary--;
        }

//        enemy set
        min = 4;
        max = width * height / 3;
        int randomEnemy = rand.nextInt(max - min) + min;

        while (randomEnemy >= 0) {
            Cell verify = grid.replaceCell(ENEMY);
            if (verify.type == ENEMY)
                randomEnemy--;
        }
        return grid;
    }


    public void generateGridTest() {
        Random rand = new Random();
        int width = 5;
        int height = 5;
        Cell currentCell = new Cell(0, 0, PLAYER, true);
        grid = new Grid(width, height, currentCell);
        for (int i = 0; i < height; i++) {
            ArrayList<Cell> rows = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                if (currentCell.x == i && currentCell.y == j) {
                    rows.add(currentCell);
                } else {
                    rows.add(new Cell(i, j));
                }
            }
            this.add(rows);
        }

        grid.replaceCellTest(PORTAL, 4, 4);
        grid.replaceCellTest(SANCTUARY, 0, 3);
        grid.replaceCellTest(SANCTUARY, 1, 3);
        grid.replaceCellTest(SANCTUARY, 2, 0);
        grid.replaceCellTest(SANCTUARY, 4, 3);
        grid.replaceCellTest(ENEMY, 3, 4);

    }

    public void replaceCellTest(CellEntityType type, int x, int y) {
        if (!this.get(x).get(y).status && this.get(x).get(y).type == VOID) {
            this.get(x).set(y, new Cell(x, y, type, false));
        }
    }

    //set types of cell
    public Cell replaceCell(CellEntityType type) {
        Random rand = new Random();
        int x = rand.nextInt(height);
        int y = rand.nextInt(width);
        if (!this.get(x).get(y).status && this.get(x).get(y).type == VOID) {
            this.get(x).set(y, new Cell(x, y, type, false));
        }
        return this.get(x).get(y);
    }

    //singletone
    public static Grid getInstance() {
        if (grid == null) {
            throw new IllegalStateException("Grid has not been initialized yet!");
        }
        return grid;
    }


    public Cell goNorth() throws ImpossibleMoveException {
        int x = this.currentCell.x;
        int y = this.currentCell.y;
        this.get(x).set(y, new Cell(x, y, VOID, true));
        if (x - 1 < 0) {
            throw new ImpossibleMoveException("Cannot move north: out of bounds.");
        }
        Cell nextMove = this.get(x-1).get(y);
        this.currentCell = this.get(x-1).set(y, new Cell(x - 1, y, PLAYER, false));
        return nextMove;
    }

    public Cell goSouth() throws ImpossibleMoveException {
        int x = this.currentCell.x;
        int y = this.currentCell.y;
        this.get(x).set(y, new Cell(x, y, VOID, true));
        if (x + 1 >= this.size()) {
            throw new ImpossibleMoveException("Cannot move south: out of bounds.");
        }
        Cell nextMove = this.get(x+1).get(y);

        this.currentCell = this.get(x+1).set(y, new Cell(x+1, y, PLAYER, false));
        return nextMove;

    }

    public Cell goWest() throws ImpossibleMoveException {
        int x = this.currentCell.x;
        int y = this.currentCell.y;
        this.get(x).set(y, new Cell(x, y, VOID, true));
        if (y - 1 < 0) {
            throw new ImpossibleMoveException("Cannot move east: out of bounds.");
        }
        Cell nextMove = this.get(x).get(y - 1);
        this.currentCell = this.get(x).set(y - 1, new Cell(x, y - 1, PLAYER, false));
        return nextMove;
    }

    public Cell goEast() throws ImpossibleMoveException {
        int x = this.currentCell.x;
        int y = this.currentCell.y;
        this.get(x).set(y, new Cell(x, y, VOID, true));
        if (y + 1 >= this.get(0).size()) {
            throw new ImpossibleMoveException("Cannot move west: out of bounds.");
        }
        Cell nextMove = this.get(x).get(y + 1);
        this.currentCell = this.get(x).set(y + 1, new Cell(x, y + 1, PLAYER, false));
        return nextMove;
    }


    public void printGrid() {
        for (ArrayList<Cell> row : grid) {
            for (Cell cell : row)
                System.out.print(cell.toString() + " ");
            System.out.println();
        }
    }

}



