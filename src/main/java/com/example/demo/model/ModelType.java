package com.example.demo.model;

public class ModelType {
	private int id;
	private String name;
	private double rating;
	private int rating_count;
	private String address;
	private String verified;
	private String location;
	private double distance;
	
	public ModelType() {
		
	}
	
	public ModelType(int id,String name,double rating,int rating_count,String address,String verified,String location) {
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.rating_count = rating_count;
		this.address = address;
		this.verified = verified;
		this.location = location;
	}
	
	public ModelType(int id,String name,double rating,int rating_count,String address,String verified,String location,double distance) {
		
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.rating_count = rating_count;
		this.address = address;
		this.verified = verified;
		this.location = location;
		this.distance = distance;
		System.out.println("Here "+this.distance);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * @return the rating_count
	 */
	public int getRating_count() {
		return rating_count;
	}

	/**
	 * @param rating_count the rating_count to set
	 */
	public void setRating_count(int rating_count) {
		this.rating_count = rating_count;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the verified
	 */
	public String getVerified() {
		return verified;
	}

	/**
	 * @param verified the verified to set
	 */
	public void setVerified(String verified) {
		this.verified = verified;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

}
