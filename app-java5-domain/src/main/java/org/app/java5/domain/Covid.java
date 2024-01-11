package org.app.java5.domain;

import java.time.LocalDate;

public class Covid {
	
	private LocalDate fecha;
	private Long cases;
	
	public Covid(LocalDate fecha, Long cases) {
		super();
		this.fecha = fecha;
		this.cases = cases;
	}


	public Long getCases() {
		return cases;
	}


	@Override
	public String toString() {
		return "Covid [fecha=" + fecha + ", cases=" + cases + "]";
	}
	
}
