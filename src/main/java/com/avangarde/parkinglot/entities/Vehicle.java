package com.avangarde.parkinglot.entities;

import com.avangarde.parkinglot.auxs.intermeds.VehicleType;

import javax.persistence.*;

@Entity
@Table(schema = "parking", name = "vehicles")
public class Vehicle {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column(name = "plate", length = 7, unique = true)
    private String plate;

    @OneToOne(mappedBy = "currentVehicle")
    private ParkingSpot spot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }
}
