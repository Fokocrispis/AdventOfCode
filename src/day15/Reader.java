package day15;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private static final Path PATH = Paths.get("./resources/Day15_test.txt");

    private List<Character> instructions;
    private List<Point> blocks;
    private List<Point> walls;
    private Tile[][] map;
    private boolean readMap;
    private Point guard;

    public Reader() {
        this.instructions = new ArrayList<>();
        this.blocks = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.readMap = true;
    }

    public void readFromFile() {
        List<Tile[]> lines = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(PATH)) {
            String line;
            int row = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    readMap = false;
                    continue;
                }

                if (readMap) {
                    char[] rowChars = line.toCharArray();
                    Tile[] tileRow = new Tile[rowChars.length];

                    for (int col = 0; col < rowChars.length; col++) {
                        char c = rowChars[col];
                        switch (c) {
                            case '@':
                                tileRow[col] = new Tile(Tile.TileType.GUARD);
                                guard = new Point(col, row);
                                break;
                            case 'O':
                                tileRow[col] = new Tile(Tile.TileType.BLOCK);
                                blocks.add(new Point(col, row));
                                break;
                            case '#':
                                tileRow[col] = new Tile(Tile.TileType.WALL);
                                walls.add(new Point(col, row));
                                break;
                            default:
                                tileRow[col] = new Tile(Tile.TileType.EMPTY);
                                break;
                        }
                    }

                    lines.add(tileRow);
                    row++;
                } else {
                    for (char c : line.toCharArray()) {
                        instructions.add(c);
                    }
                }
            }

            map = new Tile[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                map[i] = lines.get(i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Character> getInstructions() {
        return this.instructions;
    }

    public Tile[][] getMap() {
        return this.map;
    }

    public Point getGuard() {
        return this.guard;
    }

    public List<Point> getWalls() {
        return this.walls;
    }

    public List<Point> getBlocks() {
        return this.blocks;
    }
}
