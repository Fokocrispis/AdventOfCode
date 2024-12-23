package day15;

public class Tile {

    public enum TileType {
        EMPTY, WALL, BLOCK, GUARD
    }

    private TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }
    
    public TileType getTile() {
    	return type;
    }

    public boolean isWall() {
        return type == TileType.WALL;
    }

    public boolean isBlock() {
        return type == TileType.BLOCK;
    }

    public boolean isGuard() {
        return type == TileType.GUARD;
    }
    
    public boolean isEmpty() {
        return type == TileType.EMPTY;
    }
    
    public char getChar() {
    	return switch(type) {
    	case WALL -> '#';
    	case BLOCK -> 'O';
    	case GUARD -> '@';
    	case EMPTY -> '.';
    	};
    }
}
