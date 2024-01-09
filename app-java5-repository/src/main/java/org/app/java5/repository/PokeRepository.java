package org.app.java5.repository;

import java.util.Collection;
import org.app.java5.domain.Pokemon;

public interface PokeRepository {
	
	public void save (Pokemon poke);
	public void delete (Long id);
	public void update (Pokemon poke);
	public Long getById (Long id);
	public String getByName (String name);
	public Collection<Pokemon> findAll();


}
