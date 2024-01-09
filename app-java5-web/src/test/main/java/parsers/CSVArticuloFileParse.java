package parsers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLSyntaxErrorException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.app.domain.Articulo;

public class CSVArticuloFileParse implements IParser {

	public Collection<Articulo> parse(InputStream is) {
		
		var registros = new ArrayList<Articulo>();	
		//InputStream is = filePart.getInputStream();
		try(InputStreamReader isr = new InputStreamReader(is)) {
		
		BufferedReader br = new BufferedReader(isr); 
		
		//lectura
		
		String line = br.readLine();
		
		if(line == null || line.length() == 0) {
			throw new IllegalArgumentException("No hay lineas para leer");
		}
		
		while ((line = br.readLine()) != null) {
			registros.add(parseLine(line));
		}
		
		br.close();
		
		}catch (Exception e) {
			System.err.println(e);
		}
		return registros;
	}
	
	private Articulo parseLine(String line) {
		
		String[] values = line.split(";");
		if(values.length <5) {
			System.err.print("Los campos no coinciden");
		}
		Long editorial_id = Long.parseLong(values[0]);
		Long isbn = Long.parseLong(values[1]);
		Integer nro_paginas = Integer.parseInt(values[2]);
		String idioma = values[3];
		LocalDate fecha_publicacion = LocalDate.parse(values[4]);
		
		return new Articulo(editorial_id, isbn, nro_paginas, idioma, fecha_publicacion);
		
	}

}
