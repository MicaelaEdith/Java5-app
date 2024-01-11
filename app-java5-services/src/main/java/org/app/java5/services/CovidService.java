package org.app.java5.services;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import org.app.java5.repository.CovidRepositoryImpl;

public class CovidService {
	
	public Long getCases(LocalDate date) {
		
		CovidRepositoryImpl repository = new CovidRepositoryImpl();
		Long found = repository.getCases(date);
		
		System.out.println(found);
		return found;
	}
	
	public void saveDataDay(LocalDate date, Long cases) throws SQLException {
		CovidRepositoryImpl repository = new CovidRepositoryImpl();
		repository.saveDataDay(date, cases);
		
		System.out.println("enviado a DB");
		
	}

}
