package com.mpc.product;

public class Candy  {
	protected int id;
	protected String candyName;
	protected String candyBrand;
	protected String candyType;
	protected float candyPrice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCandyName() {
		return candyName;
	}
	public void setCandyName(String candyName) {
		this.candyName = candyName;
	}
	public String getCandyBrand() {
		return candyBrand;
	}
	public void setCandyBrand(String candyBrand) {
		this.candyBrand = candyBrand;
	}
	public String getCandyType() {
		return candyType;
	}
	public void setCandyType(String candyType) {
		this.candyType = candyType;
	}
	public float getCandyPrice() {
		return candyPrice;
	}
	public void setCandyPrice(float candyPrice) {
		this.candyPrice = candyPrice;
	}
	
	public static Candy generateCandy(){
		Candy candy = new Candy();
		candy.setCandyName("trufel");
		candy.setCandyType("chocolate");
		candy.setCandyBrand("Grand Candy");
		
		return candy;
	}
	
}
