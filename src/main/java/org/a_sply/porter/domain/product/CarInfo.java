package org.a_sply.porter.domain.product;

public class CarInfo {
	
	private CarMaker carMaker;
	private CarType carType;
	private CarModel carModel;
	private int carYear;
	
	public CarInfo(int makerNo, int typeNo, int modelNo, int carYaer){
		this.carMaker = new CarMaker(makerNo);
		this.carType = new CarType(typeNo);
		this.carModel = new CarModel(modelNo);
		this.carYear = carYaer;
	}
	
	public CarMaker getCarMaker() {
		return carMaker;
	}
	public void setCarMaker(CarMaker carMaker) {
		this.carMaker = carMaker;
	}
	public CarModel getCarModel() {
		return carModel;
	}
	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
	}
	public CarType getCarType() {
		return carType;
	}
	public void setCarType(CarType carType) {
		this.carType = carType;
	}
	public int getCarYear() {
		return carYear;
	}
	public void setCarYear(int carYear) {
		this.carYear = carYear;
	}
}
