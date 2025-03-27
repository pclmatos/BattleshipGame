package battleship.battleship_common.ships;

public abstract class Ship {
    private int size;
    private String name;

    public Ship(String name, int size) {
        this.size = size;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Ship [name=" + getName() + ", size=" + getSize() + "]";
    }

}
