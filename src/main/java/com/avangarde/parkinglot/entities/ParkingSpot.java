package com.avangarde.parkinglot.entities;

import com.avangarde.parkinglot.auxs.intermeds.SpotType;

import javax.persistence.*;

@Entity
@Table(schema = "parking", name = "parking_spots")
public class ParkingSpot {

    public ParkingSpot() {
    }

    public ParkingSpot(SpotType type) {
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_occupied", nullable = false)
    private boolean isOccupied;

    @Enumerated(EnumType.STRING)
    @Column(name = "spot_type", nullable = false)
    private SpotType type;

    @ManyToOne
    @JoinColumn(name = "parking_floor_id", referencedColumnName = "id", nullable = false)
    private ParkingFloor floor;

    @OneToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle currentVehicle;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public SpotType getType() {
        return type;
    }

    public void setType(SpotType type) {
        this.type = type;
    }

    public ParkingFloor getFloor() {
        return floor;
    }

    public void setFloor(ParkingFloor floor) {
        this.floor = floor;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public void setCurrentVehicle(Vehicle currentVehicle) {
        this.currentVehicle = currentVehicle;
    }
}
