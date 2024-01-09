package org.app.java5.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.app.java5.domain.Pokemon;
import db.AdministradorDeConexiones;



public class ServicesDB {
	
	  public void findAllPoke() {
	    
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
	        
	        for (Pokemon pokemon : pokemonList) {
	            System.out.println("Pokemon encontrado: " + pokemon);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	  public Pokemon findById(int id) {
	        String sql = "SELECT * FROM pokemon WHERE id = ?;";
	
	    try (Connection connection = AdministradorDeConexiones.getConnection();) {
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setInt(1, id);
	
	        ResultSet resultSet = statement.executeQuery();
	
	        if (resultSet.next()) {
	            Pokemon pokemon = new Pokemon();
	            pokemon.setId(resultSet.getInt("id"));
	            pokemon.setName(resultSet.getString("nombre"));
	            System.out.println("El pokemon con id: '"+ id +"' fue encontrado. Su nombre es: "+pokemon.getName()+".");
	            return pokemon;
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	    return null;
	}

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
                System.out.println("El pokemon con '"+ nombre +"' fue encontrado. Su Id es: "+pokemon.getId()+".");
                return pokemon;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Pokemon con nombre: "+nombre+" no fue encontrado.");
        return null;
    }
	  
	  public void savePokemon(Pokemon pokemon) {
	        String sql = "INSERT INTO pokemon (id, nombre) VALUES (?, ?);";

	        try (Connection connection = AdministradorDeConexiones.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	        	statement.setLong(1, pokemon.getId());
	            statement.setString(2, pokemon.getName());
	         

	            int filasAfectadas = statement.executeUpdate();

	            if (filasAfectadas > 0) {
	                System.out.println("Pokemon guardado exitosamente en la base de datos.");
	            } else {
	                System.out.println("No se pudo guardar el Pokémon en la base de datos.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
