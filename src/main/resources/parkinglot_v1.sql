CREATE SCHEMA parking;

CREATE TABLE parking.parking_spots (
	id SERIAL PRIMARY KEY,
	parking_floor_id INT NOT NULL, -- fk
	vehicle_id INT, -- fk
	is_occupied BOOLEAN NOT NULL,
	spot_type VARCHAR(15) NOT NULL
);

CREATE TABLE parking.parking_floors (
	id SERIAL PRIMARY KEY,
	parking_lot_id INT NOT NULL, -- fk
	parking_floor_number INT NOT NULL
);

CREATE TABLE parking.parking_lots (
	id SERIAL PRIMARY KEY
);

CREATE TABLE parking.vehicles (
	id SERIAL PRIMARY KEY,
	vehicle_type VARCHAR(15) NOT NULL,
	plate VARCHAR(7)
);

ALTER TABLE parking.parking_spots
ADD CONSTRAINT fk_parking_floor FOREIGN KEY (parking_floor_id) REFERENCES parking.parking_floors(id);

ALTER TABLE parking.parking_spots
ADD CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES parking.vehicles(id);

ALTER TABLE parking.parking_floors
ADD CONSTRAINT fk_parking_lot FOREIGN KEY (parking_lot_id) REFERENCES parking.parking_lots(id);