package org.app.java5.repository;

import java.sql.SQLException;
import java.time.LocalDate;

public interface CovidRepository {
	
	public Long getCases(LocalDate date);
	void saveDataDay(LocalDate date, Long cases) throws SQLException;
	
}
