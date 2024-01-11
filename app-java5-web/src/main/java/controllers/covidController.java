package controllers;

import java.io.IOException;
import java.time.LocalDate;
import org.app.java5.services.CovidService;
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

@WebServlet("/api/covid")
public class CovidController extends HttpServlet {

	private static final long serialVersionUID = -4753107102076460167L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("doGet() Covid");

		LocalDate date = LocalDate.parse(req.getParameter("date"));
		
    	CovidService service = new CovidService();
    	
    	Long cases = service.getCases(date);
    	
    	
    	if(cases == null) {
				
			Client cliente = ClientBuilder.newClient();
			
			WebTarget target = cliente.target("https://api.covidtracking.com/v1/us/"+req.getParameter("date"));  
			Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
			
			Response response = invocation.get();
			
			if (response.getStatus() == Response.Status.OK.getStatusCode()) {
				
			    try {
			        ObjectMapper objectMapper = new ObjectMapper();
			        JsonNode jsonResponse = objectMapper.readTree(response.readEntity(String.class));

			        cases = jsonResponse.get("cases").asLong();
			        service.saveDataDay(date, 6546854L);
			    
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
		}
    	
	}
			
}