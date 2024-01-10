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
				Pokemon pokemon = new Pokemon();
				pokemon.setId(resultSet.getInt("id"));
				pokemon.setName(resultSet.getString("nombre"));

				pokemonList.add(pokemon);
			}

			//for (Pokemon pokemon : pokemonList) {
			//	System.out.println("Pokemon encontrado: " + pokemon);
			//}

			return pokemonList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Pokemon findById(Integer id) {
		String sql = "SELECT * FROM pokemon WHERE id = ?;";

		try (Connection connection = AdministradorDeConexiones.getConnection();) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Pokemon pokemon = new Pokemon();
				pokemon.setId(resultSet.getInt("id"));
				pokemon.setName(resultSet.getString("nombre"));
				System.out.println(
						"El pokemon con id: '" + id + "' fue encontrado. Su nombre es: " + pokemon.getName() + ".");
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
				Pokemon pokemon = new Pokemon();
				pokemon.setId(resultSet.getInt("id"));
				pokemon.setName(resultSet.getString("nombre"));
				//
				System.out
						.println("El pokemon con '" + nombre + "' fue encontrado. Su Id es: " + pokemon.getId() + ".");
				return pokemon;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Pokemon con nombre: " + nombre + " no fue encontrado.");
		return null;
	}

	@Override
	public void save(Pokemon pokemon) {
		String sql = "INSERT INTO pokemon (id, nombre) VALUES (?, ?);";

		try (Connection connection = AdministradorDeConexiones.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setLong(1, pokemon.getId());
			statement.setString(2, pokemon.getName());

			int saveOk = statement.executeUpdate();

			if (saveOk > 0) {
				System.out.println("Pokemon guardado exitosamente en la base de datos.");
			} else {
				System.out.println("No se pudo guardar el Pokémon en la base de datos.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE * from pokemon where id=" + id;
		;

		try (Connection connection = AdministradorDeConexiones.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			int deleted = statement.executeUpdate();

			if (deleted > 0) {
				System.out.println("Pokemon eliminado exitosamente de la base de datos.");
			} else {
				System.out.println("No se pudo eliminar el Pokémon en la base de datos.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
