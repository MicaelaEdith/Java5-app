package org.app.java5.domain;

public class Pokemon {
	
	private int id;
	private String name;
	
	
	public Pokemon(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + "]";
	}
	
	
	
	

}
