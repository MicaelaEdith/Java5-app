	package controllers.jersey;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import connectors.meli.dto.Categoria;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@WebServlet("/categorias")
public class ListResourceController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//url del servicio
		String url ="https://api.mercadolibre.com/sites/";
		String endPoint="MLA/categories";
		
		//crear cliente jersey para hacer peticion http
		//convertir los datos 
		
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target(url).path(endPoint);
		
		Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
		
		//metodo get/post/etc
		Response response = invocation.get();
		
		
		
		GenericType<List<Categoria>> listType = new GenericType<List<Categoria>>() {};
		List <Categoria> categoria = response.readEntity(listType);
		
		//convertir a objeto json usando jackson2
		ObjectMapper mapper =new ObjectMapper();
		
		String json = mapper.writeValueAsString(categoria);
		
		resp.getWriter().print(json);
		
	}

}
