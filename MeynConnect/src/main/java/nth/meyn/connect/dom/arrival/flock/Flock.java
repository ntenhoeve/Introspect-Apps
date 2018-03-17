package nth.meyn.connect.dom.arrival.flock;

import java.time.Duration;
import java.time.LocalDate;

import nth.meyn.connect.dom.arrival.house.House;

public class Flock {
	private String code;
	private BirdType birdType;
	private House house;
	private LocalDate hatchDate;
	private LocalDate catchDate;
	private int numberOfBirdsGoingToPlant;
	private double averageLiveWeightAtHouse;
	private boolean isContaminated;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BirdType getBirdType() {
		return birdType;
	}

	public void setBirdType(BirdType birdType) {
		this.birdType = birdType;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public LocalDate getHatchDate() {
		return hatchDate;
	}

	public void setHatchDate(LocalDate hatchDate) {
		this.hatchDate = hatchDate;
	}

	public LocalDate getCatchDate() {
		return catchDate;
	}

	public void setCatchDate(LocalDate catchDate) {
		this.catchDate = catchDate;
	}

	public int getBirdAgeInDays() {
		if (hatchDate == null || hatchDate.isAfter(LocalDate.now())) {
			return 0;
		} else if (catchDate == null || catchDate.isAfter(LocalDate.now())) {
			return (int) Duration.between(hatchDate, LocalDate.now()).toDays();
		} else {
			return (int) Duration.between(hatchDate, catchDate).toDays();
		}
	}

	public int getNumberOfBirdsGoingToPlant() {
		return numberOfBirdsGoingToPlant;
	}

	public void setNumberOfBirdsGoingToPlant(int numberOfBirdsGoingToPlant) {
		this.numberOfBirdsGoingToPlant = numberOfBirdsGoingToPlant;
	}

	public double getAverageLiveWeightAtHouse() {
		return averageLiveWeightAtHouse;
	}

	public void setAverageLiveWeightAtHouse(double averageLiveWeightAtHouse) {
		this.averageLiveWeightAtHouse = averageLiveWeightAtHouse;
	}

	public boolean isContaminated() {
		return isContaminated;
	}

	public void setContaminated(boolean isContaminated) {
		this.isContaminated = isContaminated;
	}

	public double getTotalWeightAtHouse() {
		return numberOfBirdsGoingToPlant * averageLiveWeightAtHouse;
	}

}
