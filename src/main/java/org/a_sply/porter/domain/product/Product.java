package org.a_sply.porter.domain.product;

import org.a_sply.porter.domain.User;

public class Product {
	
	private long id;
	
	private User owner;
	
	private CarInfo carInfo;
	private PartType partType;
	
	private String name;
	private double price;
	private int amount;
	private ImageUrls imageUrls;
	private String state;
	
	private Product(Builder b) {
		this.id = b.id;
		this.owner = b.owner;
		this.carInfo = b.carInfo;
		this.partType = b.partType;
		this.name = b.name;
		this.price = b.price;
		this.amount = b.amount;
		this.imageUrls = b.imageUrls;
		this.state = b.state;
	}
	public long getId() {
		return id;
	}

	public User getOwner() {
		return owner;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public PartType getPartType() {
		return partType;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getAmount() {
		return amount;
	}

	public ImageUrls getImageUrls() {
		return imageUrls;
	}

	public String getState() {
		return state;
	}

	public static class Builder {
		
		private long id;
		
		private User owner;
		
		private CarInfo carInfo;
		private PartType partType;
		
		private String name;
		private double price;
		private int amount;
		private ImageUrls imageUrls;
		private String state;

		public Builder(){
			this.id = -1;
		}
		
		public Builder id(long id){
			this.id = id;
			return this;
		}
		
		public Builder owner(User owner){
			this.owner = owner;
			return this;
		}
		
		public Builder carInfo(CarInfo carModel){
			this.carInfo = carModel;
			return this;
		}
		
		public Builder partType(PartType partType){
			this.partType = partType;
			return this;
		}
		
		public Builder name(String name){
			this.name = name;
			return this;
		}
		
		public Builder price(double price){
			this.price = price;
			return this;
		}
		public Builder amount(int amount){
			this.amount = amount;
			return this;
		}
		public Builder images(ImageUrls imageUrls){
			this.imageUrls = imageUrls;
			return this;
		}
		public Builder state(String state){
			this.state = state;
			return this;
		}

        public Product build() {
            return new Product(this);
        }
    }
}

