package org.app.java5.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.app.java5.domain.Pokemon;

import db.AdministradorDeConexiones;

public class PokeRepositoryImpl implements PokeRepository {

	@Override
	public List<Pokemon> findAll() {

		String sql = "SELECT * FROM pokemon";

		try (Connection connection = AdministradorDeConexiones.getConnection();) {

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			List<Pokemon> pokemonList = new ArrayList<>();

			while (resultSet.next()) {
				Pokemon pokemon = null;
				pokemon = (Pokemon)resultSet;

				pokemonList.add(pokemon);
			}
			return pokemonList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Pokemon findById(Long id) {
		String sql = "SELECT * FROM pokemon WHERE id = ?;";

		try (Connection connection = AdministradorDeConexiones.getConnection();) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				
				Long Id = resultSet.getLong("id");
				String name = resultSet.getString("nombre");
				String urlImg = resultSet.getString("url_imagen");
				
				Pokemon pokemon = new Pokemon(Id, name, urlImg);
				
				return pokemon;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Pokemon findByName(String nombre) {
		String sql = "SELECT * FROM pokemon WHERE nombre = ?;";

		try (Connection connection = AdministradorDeConexiones.getConnection();) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nombre);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				
				Pokemon pokemon = (Pokemon)resultSet;
				return pokemon;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void save(Pokemon pokemon) {
		String sql = "INSERT INTO pokemon (id, nombre, url_imagen) VALUES (?, ?, ?);";

		try (Connection connection = AdministradorDeConexiones.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setLong(1, pokemon.getId());
			statement.setString(2, pokemon.getName());
			statement.setString(3, pokemon.getUrlImg());

			statement.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
