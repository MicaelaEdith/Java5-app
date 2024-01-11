package controllers;

import java.io.IOException;
import org.app.java5.domain.Pokemon;
import org.app.java5.services.PokeService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

@WebServlet("/api/pokemon")
public class PokemonController extends HttpServlet {

	private static final long serialVersionUID = 1296242401940549105L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pokemonIdOrName = req.getParameter("pokemon");
		PokeService serviceDB = new PokeService();
		Pokemon poke = null;
		
		if(pokemonIdOrName != null) {
			
		
	        try {
	        	Long id = Long.parseLong(pokemonIdOrName);
	            poke = serviceDB.findById(id);
	            
	        } catch (NumberFormatException e) {
	            
	        	poke = serviceDB.findByName(pokemonIdOrName);
	        }
			
			if(poke==null) {
				
				Client cliente = ClientBuilder.newClient();
				WebTarget target = cliente.target("https://pokeapi.co/api/v2/pokemon/"+pokemonIdOrName); 
				Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
				
				Response response = invocation.get();
				
				if (response.getStatus() == Response.Status.OK.getStatusCode()) {
					
				    try {
				        ObjectMapper objectMapper = new ObjectMapper();
				        JsonNode jsonResponse = objectMapper.readTree(response.readEntity(String.class));
	
				        long id = jsonResponse.get("id").asLong();
				        String name = jsonResponse.get("name").asText();
				        String urlImg = jsonResponse.get("sprites").get("front_default").asText();
	
				        Pokemon nuevoPokemon = new Pokemon(id, name, urlImg);
				        serviceDB.save(nuevoPokemon);
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
				}
			}
		}
	}

}
