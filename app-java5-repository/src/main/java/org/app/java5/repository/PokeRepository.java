package org.app.java5.repository;

import java.util.List;

import org.app.java5.domain.Pokemon;

public interface PokeRepository {
	
	public void save (Pokemon poke);
	public void delete (Long id);
	public Pokemon findById (Integer id);
	public Pokemon findByName (String name);
	public List<Pokemon> findAll();


}
