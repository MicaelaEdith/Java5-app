package controllers.jersey;

import java.io.IOException;

import controllers.jersey.dto.ReqResUser;
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

@WebServlet("/users")
public class ResourceController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//url del servicio
		String url ="https://reqres.in/api";
		String Id = req.getParameter("id");
		String endPoint="/users/" + Id;
		
		//crear cliente jersey para hacer peticion http
		//convertir los datos 
		
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target(url).path(endPoint);
		
		Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
		
		//metodo get/post/etc
		Response response = invocation.get();
		
		ReqResUser user = response.readEntity(ReqResUser.class);
		
		resp.getWriter().print(user);
		
	}

}
