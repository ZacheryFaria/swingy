package zfaria.swingy;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate() {
        this(0, 0);
    }

    public void addCoordinate(Coordinate c) {
        this.x += c.x;
        this.y += c.y;
    }

    public void addCoordinate(int x, int y) {
        this.addCoordinate(new Coordinate(x, y));
    }

    public boolean equals(Coordinate c) {
        return (this.x == c.x && this.y == c.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate clone() {
        return new Coordinate(x, y);
    }

    @Override
    public String toString() {
        return String.format("%d, %d", x, y);
    }
}
