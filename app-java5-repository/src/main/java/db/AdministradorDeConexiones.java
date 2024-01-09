package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdministradorDeConexiones {

public static Connection getConnection() {
		
		String username="root";
		String passwordDB="";
		String url="jdbc:mysql://127.0.0.1:3306/java_e5?serverTimeZone=UTC&useSSL=false";//mysql-postgress-oracle
		String driverClassName = "com.mysql.cj.jdbc.Driver"; 
		
		try {
			Class.forName(driverClassName);
			return DriverManager.getConnection(url,username,passwordDB);
			
		}catch(Exception ex){
			throw new IllegalArgumentException("Error al conectar a DB: "+driverClassName);
			
		}
		
	}

}
