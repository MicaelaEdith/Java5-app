package org.app.java5.services;

import java.util.Collection;

import org.app.java5.domain.Pokemon;

public interface PokemonService {

	public Collection<Pokemon> buscarTodos();
	public Long buscarPorId(Long id);
	public void eliminarPorId(Long id);
	public void guardar(Pokemon poke);
	public void actualizar(Pokemon poke);
}
