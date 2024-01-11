package org.app.java5.domain;

public class Pokemon {
	
	private Long id;
	private String name;
	private String urlImg;
	
	public Pokemon(Long id, String name, String urlImg) {
		super();
		this.id = id;
		this.name = name;
		this.urlImg=urlImg;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getUrlImg() {
		return urlImg;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + ", urlImg=" + urlImg + "]";
	}
	
	

}
