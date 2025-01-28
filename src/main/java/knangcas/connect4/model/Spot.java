package knangcas.connect4.model;

public class Spot {

    private int isOccupied;
    private int spotId;

    public Spot(int spotId) {
        this.spotId = spotId;
        isOccupied = 0;
    }

    public int getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(int isOccupied) {
        this.isOccupied = isOccupied;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }
}
