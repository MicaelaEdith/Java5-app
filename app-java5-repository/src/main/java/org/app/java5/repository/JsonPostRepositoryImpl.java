package org.app.java5.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.AdministradorDeConexiones;

public class JsonPostRepositoryImpl implements JsonPostRepository {

	@Override
	public String findById(Long id) {
		String sql = "SELECT * FROM json_post WHERE id ="+id;

		try (Connection connection = AdministradorDeConexiones.getConnection();) {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				
				String json = resultSet.getString("json");
				
				return json;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void Save(Long id, String json) {
		String sql = "INSERT INTO json_post (id, json) VALUES ("+id+",'"+json+"');";

		try (Connection connection = AdministradorDeConexiones.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
