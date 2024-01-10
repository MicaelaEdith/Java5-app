package controllers;

import java.io.IOException;
import java.net.http.HttpClient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@WebServlet("/api/pokemon/")
public class PokemonController extends HttpServlet {

	private static final long serialVersionUID = 1296242401940549105L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String Id = "1";
		String url ="https://pokeapi.co/api/v2/pokemon/"+Id;
		
	
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target(url);
		Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
		
		Response response = invocation.get();
		
		String poke = (String)response.getEntity();
		System.out.println(poke);
		
		resp.getWriter().print(poke);
		
	}

    

}
