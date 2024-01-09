package org.app.java5.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.AdministradorDeConexiones;

public class ServicesDB {
	
	public void pruebaConectividad(){
		
		String sql = "SELECT * FROM pokemon;;";
	
	try(Connection connection = AdministradorDeConexiones.getConnection();) {
		
		System.out.print("connection ok - service DB");
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		System.out.print("encontrado: "+resultSet.toString());
		
	
	} catch (SQLException e) {
		e.printStackTrace();
	}	
	}
}
