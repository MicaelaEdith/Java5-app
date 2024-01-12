package org.app.java5.repository;

public interface JsonPostRepository {
	
	public String findById (Long id);

	public void Save(Long id, String json);

}
