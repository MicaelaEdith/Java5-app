package org.app.java5.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import db.AdministradorDeConexiones;

public class CovidRepositoryImpl implements CovidRepository{

	@Override
	public Long getCases(LocalDate date) {	
		
		DateTimeFormatter nuevoFormato = DateTimeFormatter.ofPattern("yyyyMMdd");
		String sqlDate = date.format(nuevoFormato);
		
		String sql = "SELECT cases FROM covid where date = "+sqlDate;
	
		try (Connection connection = AdministradorDeConexiones.getConnection();) {
	
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
	
			if (resultSet.next()) {
							
				Long cases = resultSet.getLong("cases");
				return cases;
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void saveDataDay(LocalDate date, Long cases) throws SQLException {
		String sql = "INSERT INTO covid (date, cases) VALUES (?, ?);";
		java.sql.Date dateSql = java.sql.Date.valueOf(date);
		

		try (Connection connection = AdministradorDeConexiones.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			
			statement.setDate(1, dateSql);
			statement.setLong(2, cases);
			
			statement.executeUpdate();
			
		}
		
	}
	
}
