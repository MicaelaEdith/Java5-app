package controllers;

import jakarta.servlet.http.HttpServletRequest;
//value object
public class PokemonRequestVO {
	private Long id;
	private String name;
	private String urlImg;
	
	public PokemonRequestVO(HttpServletRequest req) {
		if(req.getParameter("id") == null) {
			throw new IllegalArgumentException("Id nulo");
		}
		this.id = Long.parseLong(req.getParameter("id"));//fk
		

		if(req.getParameter("name") == null) {
			throw new IllegalArgumentException("Nombre nulo");
		}
		this.name = req.getParameter("name");
		
		
		if(req.getParameter("urlImg") == null) {
			throw new IllegalArgumentException("Url nula");
		}
		this.urlImg = req.getParameter("urlImg");
	}


}