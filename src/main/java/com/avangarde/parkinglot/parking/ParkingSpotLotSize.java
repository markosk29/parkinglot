package com.avangarde.parkinglot.parking;

/**
 * Pairs of spot types and number of spots
 * Useful for reading from input file
 */
public class ParkingSpotLotSize {
    private SpotType type;
    private int size;

    public SpotType getType() {
        return type;
    }

    public void setType(SpotType type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private ParkingSpotLotSize(SpotType type, int size) {
        this.type = type;
        this.size = size;
    }

    public static class ParkingSpotLotSizeBuilder {
        private SpotType type;
        private int size;

        public static ParkingSpotLotSizeBuilder builder() {
            return new ParkingSpotLotSizeBuilder();
        }

        public ParkingSpotLotSizeBuilder type(SpotType type) {
            this.type = type;
            return this;
        }

        public ParkingSpotLotSizeBuilder size(int size) {
            this.size = size;
            return this;
        }

        public ParkingSpotLotSize build() {
            return new ParkingSpotLotSize(this.type, this.size);
        }
    }
}
