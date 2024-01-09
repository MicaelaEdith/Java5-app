package controllers;

import jakarta.servlet.http.HttpServletRequest;

//Value Object
public class ArticuloRequestVO {
	
	//recibir datos por request
	private Long editorial;
	private Long isbn;
	private Integer nroPaginas;
	private String idioma;
	
	public ArticuloRequestVO(HttpServletRequest req) {
		
		if(req.getParameter("editorial") == null) {
			throw new IllegalArgumentException("Editorial inválida");
			
		}
		if(req.getParameter("isbn") == null) {
			throw new IllegalArgumentException("ISBN inválido");
			
		}
		if(req.getParameter("paginas") == null) {
			throw new IllegalArgumentException("Número de páginas inválido");
			
		}
		if(req.getParameter("idioma") == null) {
			throw new IllegalArgumentException("Idioma inválido");
			
		}
		
		this.editorial = Long.parseLong(req.getParameter("editorial"));//fk
		this.isbn = Long.parseLong(req.getParameter("isbn"));
		this.nroPaginas = Integer.parseInt(req.getParameter("paginas"));
		this.idioma = req.getParameter("idioma");
		
	}

	public Long getEditorial() {
		return editorial;
	}

	public Long getIsbn() {
		return isbn;
	}

	public Integer getNroPaginas() {
		return nroPaginas;
	}

	public String getIdioma() {
		return idioma;
	}
	
	
	
}
