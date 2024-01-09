package org.app.java5.domain;

public class Pokemon {
	
	private int id;
	private String name;
	
	
	public Pokemon(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Pokemon() {
		// TODO Auto-generated constructor stub
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

	public void setId(int id) {
		this.id= id;
		
	}

	public void setName(String name) {
		this.name = name;
		
	}
	
	
	
	

}
