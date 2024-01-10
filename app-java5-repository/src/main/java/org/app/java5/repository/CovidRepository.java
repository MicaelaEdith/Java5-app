package org.app.java5.repository;

import java.time.LocalDate;

public interface CovidRepository {
	
	public Long getCases(LocalDate date);

}
