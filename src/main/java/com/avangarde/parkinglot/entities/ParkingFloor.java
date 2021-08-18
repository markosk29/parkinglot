package com.avangarde.parkinglot.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "parking", name = "parking_floors")
public class ParkingFloor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "parking_floor_number", nullable = false)
    private int level;

    @ManyToOne
    @JoinColumn(name="parking_lot_id", referencedColumnName = "id", nullable = false)
    private ParkingLot lot;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    private Set<ParkingSpot> spots;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ParkingLot getLot() {
        return lot;
    }

    public void setLot(ParkingLot lot) {
        this.lot = lot;
    }

    public Set<ParkingSpot> getSpots() {
        return spots;
    }

    public void setSpots(Set<ParkingSpot> spots) {
        this.spots = spots;
    }
}
