package com.cognixia.jump;

public class Movie {
	
	
	
	private String title;
	private String description;
	private String rating;
	
	
	
	
	public Movie(String title, String description, String rating) {
		super();
		this.title = title;
		this.description = description;
		this.rating=rating;
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Movie [Title=" + title + ", Description=" + description + ", Rating=" + rating + "]";
	}
	
	
	
	
	
}
