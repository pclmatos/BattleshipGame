package battleship.battleship_common;

public class ShotResult {

    private boolean hit;

    public ShotResult() {

    }

    public ShotResult(boolean hit) {
        this.hit = hit;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

}
