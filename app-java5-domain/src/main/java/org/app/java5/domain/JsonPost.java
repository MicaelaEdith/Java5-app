package org.app.java5.domain;

public class JsonPost {
	
	private int id;
	private String json;
	public JsonPost(int id, String json) {
		super();
		this.id = id;
		this.json=json;

	}
	@Override
	public String toString() {
		return "JsonPost [id=" + id + ", json=" + json + "]";
	}

	public int getId() {
		return id;
	}

	public String getJson() {
		return json;
	}
	
	
	
}
