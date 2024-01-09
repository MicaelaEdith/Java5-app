package controllers.jersey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import connectors.RestClientConnector;
import connectors.meli.MeliCategoriaService;
import connectors.meli.MeliCategoriaServiceImpl;
import connectors.meli.dto.Categoria;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/meli/categoria")
public class MeliController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		MeliCategoriaService serviceRest = new 		MeliCategoriaServiceImpl("https://api.mercadolibre.com/sites/MLA/categories"); 

		
		List <Categoria> categorias = serviceRest.findCategorias();
		//Categoria categoria = serviceRest.getCategoria("abc");
	
		//convertir a objeto json usando jackson2
		ObjectMapper mapper =new ObjectMapper();
		
		String jsonList = mapper.writeValueAsString(categorias);
		//String json = mapper.writeValueAsString(categoria);
		
		//System.out.println(json);
		System.out.println(jsonList);
				
		resp.getWriter().print(jsonList);

	}
}


