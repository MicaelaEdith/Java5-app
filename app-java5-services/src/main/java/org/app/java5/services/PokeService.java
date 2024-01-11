package org.app.java5.services;

import org.app.java5.domain.Pokemon;
import org.app.java5.repository.PokeRepositoryImpl;

public class PokeService {
	
		
	public void findAll() {
		
		PokeRepositoryImpl repository = new PokeRepositoryImpl();
		System.out.println(repository.findAll());
		
	}
	
	public Pokemon findById(Long id) {
		PokeRepositoryImpl repository = new PokeRepositoryImpl();
		Pokemon found = repository.findById(id);
		return found;
	}

	public Pokemon findByName(String name) {
		PokeRepositoryImpl repository = new PokeRepositoryImpl();
		Pokemon found = repository.findByName(name);
		System.out.println(found);
		return found;
		
	}
	
	public void save(Pokemon pokemon) {
		PokeRepositoryImpl repository = new PokeRepositoryImpl();
		repository.save(pokemon);
		
	}

	

}
