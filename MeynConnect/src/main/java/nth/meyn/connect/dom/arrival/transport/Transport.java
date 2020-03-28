package nth.meyn.connect.dom.arrival.transport;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nth.meyn.connect.dom.arrival.house.House;
import nth.meyn.connect.dom.arrival.location.StartLocation;
import nth.meyn.connect.dom.arrival.lot.Lot;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;

@Description(defaultEnglish = "Transportation of a complete or part of a flock with one transportation vehicle (truck)")
public class Transport {
	private String code;
	private House house;
	private Chauffeur chauffeur;
	private Truck truck;
	private double truckWeightWithoutBirds;
	private double truckWeightWithBirds;
	private int numberOfBirds;
	private LocalDateTime scheduledCatchDateTime;
	private LocalDateTime actualCatchDateTime;
	private LocalDateTime scheduledArrivalAtPlantDateTime;
	private LocalDateTime actualArrivalAtPlantDateTime;
	private StartLocation unloadLocation;
	private LocalDateTime unloadDateTime;
	private List<Lot> lots;

	public Transport() {
		lots = new ArrayList<>();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	
	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}

	public double getTruckWeightWithoutBirds() {
		return truckWeightWithoutBirds;
	}

	public void setTruckWeightWithoutBirds(double truckWeightWithoutBirds) {
		this.truckWeightWithoutBirds = truckWeightWithoutBirds;
	}

	public double getTruckWeightWithBirds() {
		return truckWeightWithBirds;
	}

	public void setTruckWeightWithBirds(double truckWeightWithBirds) {
		this.truckWeightWithBirds = truckWeightWithBirds;
	}

	public int getNumberOfBirds() {
		return numberOfBirds;
	}

	public void setNumberOfBirds(int numberOfBirds) {
		this.numberOfBirds = numberOfBirds;
	}

	public LocalDateTime getScheduledCatchDateTime() {
		return scheduledCatchDateTime;
	}

	public void setScheduledCatchDateTime(LocalDateTime scheduledCatchDateTime) {
		this.scheduledCatchDateTime = scheduledCatchDateTime;
	}

	public LocalDateTime getActualCatchDateTime() {
		return actualCatchDateTime;
	}

	public void setActualCatchDateTime(LocalDateTime actualCatchDateTime) {
		this.actualCatchDateTime = actualCatchDateTime;
	}

	public LocalDateTime getScheduledArrivalAtPlantDateTime() {
		return scheduledArrivalAtPlantDateTime;
	}

	public void setScheduledArrivalAtPlantDateTime(LocalDateTime scheduledArrivalAtPlantDateTime) {
		this.scheduledArrivalAtPlantDateTime = scheduledArrivalAtPlantDateTime;
	}

	public LocalDateTime getActualArrivalAtPlantDateTime() {
		return actualArrivalAtPlantDateTime;
	}

	public void setActualArrivalAtPlantDateTime(LocalDateTime actualArrivalAtPlantDateTime) {
		this.actualArrivalAtPlantDateTime = actualArrivalAtPlantDateTime;
	}

	
	
	public StartLocation getUnloadLocation() {
		return unloadLocation;
	}

	public void setUnloadLocation(StartLocation unloadLocation) {
		this.unloadLocation = unloadLocation;
	}

	public LocalDateTime getUnloadDateTime() {
		return unloadDateTime;
	}

	public void setUnloadDateTime(LocalDateTime unloadDateTime) {
		this.unloadDateTime = unloadDateTime;
	}

	public Duration getLairageTime() {
		if (unloadDateTime == null || actualArrivalAtPlantDateTime == null
				&& scheduledArrivalAtPlantDateTime == null) {
			return Duration.ofSeconds(0, 0);
		} else if (actualArrivalAtPlantDateTime == null
				&& scheduledArrivalAtPlantDateTime != null) {
			return Duration.between(scheduledArrivalAtPlantDateTime, unloadDateTime);
		} else {
			return Duration.between(actualArrivalAtPlantDateTime, unloadDateTime);
		}
	}

	public double totalBirdWeight() {
		if (truckWeightWithBirds == 0 || truckWeightWithoutBirds == 0
				|| truckWeightWithBirds <= truckWeightWithoutBirds) {
			return 0;
		} else {
			return truckWeightWithBirds - truckWeightWithoutBirds;
		}
	}

	public double getTotalBirdWeight() {
		if (truckWeightWithBirds == 0 || truckWeightWithoutBirds == 0
				|| truckWeightWithBirds <= truckWeightWithoutBirds) {
			return 0;
		} else {
			return (truckWeightWithBirds - truckWeightWithoutBirds);
		}
	}

	public double getAverageLiveWeight() {
		if (numberOfBirds <= 0) {
			return 0;
		} else {
			return getTotalBirdWeight() / numberOfBirds;
		}
	}

	public List<Lot> getLots() {
		return lots;
	}

	public void setLots(List<Lot> lots) {
		this.lots = lots;
	}

}
