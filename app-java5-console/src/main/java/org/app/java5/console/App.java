package org.app.java5.console;

import java.sql.SQLException;
import java.time.LocalDate;

import org.app.java5.services.CovidService;

public class App {
	public static void main(String[] args) throws SQLException {

		 CovidService service = new CovidService();
		 service.saveDataDay(LocalDate.now(), 6546854L);


	}
}
