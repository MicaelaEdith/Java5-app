package org.app.java5.services;

import org.app.java5.domain.Pokemon;
import org.app.java5.repository.PokeRepositoryImpl;

public class PokeService {
	
	public void findAll() {
		
		PokeRepositoryImpl repository = new PokeRepositoryImpl();
		
		System.out.println(repository.findAll());
		
		
	}
	
	public void findById(int id) {
		PokeRepositoryImpl repository = new PokeRepositoryImpl();
		Pokemon found = repository.findById(id);
		System.out.println(found);
	}

	public void findByName(String name) {
		PokeRepositoryImpl repository = new PokeRepositoryImpl();
		Pokemon found = repository.findByName(name);
		System.out.println(found);
		
	}
	

}
