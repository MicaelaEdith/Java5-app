package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

import org.app.domain.Articulo;
import org.app.services.ArticuloService;
import org.app.services.ServiceLocator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/articulo")
public class ArticuloController extends HttpServlet {

	private static final long serialVersionUID = 4253338444424901969L;
	private ArticuloService service = (ArticuloService) ServiceLocator.getService(ArticuloService.class);
	
	@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// /api/articulo/id
		//permito acceder a todos los dominios
		//resp.addHeader("Access-Control-Allow-Origin","http://127.0.0.1:5500");
		
		if(req.getParameter("id") == null || req.getParameter("id").isEmpty()) {
			Collection<Articulo> articulos = service.buscarTodos();
			resp.getWriter().print(articulos);
		}else {
			Long id = Long.parseLong(req.getParameter("id"));		
			Articulo articulo = service.buscarPorId(id);		
			resp.getWriter().print(articulo);
		}		
	}
	
	
	//Alta artículo
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ArticuloRequestVO input = new ArticuloRequestVO(req);
		LocalDate fechaPublicacion = LocalDate.now();

	

		Articulo articulo = new Articulo(input.getEditorial(), input.getIsbn(), input.getNroPaginas(), input.getIdioma(), fechaPublicacion);
		service.guardar(articulo);
		
		resp.getWriter().print("Articulo id : "+ articulo.getId()+" alta OK.");
	}

}
