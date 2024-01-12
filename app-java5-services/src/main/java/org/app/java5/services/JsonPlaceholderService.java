package org.app.java5.services;

import org.app.java5.repository.JsonPostRepository;
import org.app.java5.repository.JsonPostRepositoryImpl;

public class JsonPlaceholderService {
	
	public String findById(Long id) {
		
		JsonPostRepository repository = new JsonPostRepositoryImpl();
		String found = repository.findById(id);
		return found;		
		
	}
	public void Save(Long id, String json) {
		JsonPostRepository repository = new JsonPostRepositoryImpl();
		repository.Save(id, json);
		
	}
}
