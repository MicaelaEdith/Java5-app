package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import org.app.java5.services.JsonPlaceholderService;

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

@WebServlet("/api/jsonplaceholder")
public class PlaceHolderController extends HttpServlet {

    private static final long serialVersionUID = -1486391964433014909L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setContentType("application/json");

        Long id = Long.parseLong(req.getParameter("id"));
        if(id<1 || id>100) {
        	
        }
        JsonPlaceholderService service = new JsonPlaceholderService();
        String json = null;

        if (id != null) {
            json = service.findById(id);
        }

        if (json == null) {
            Client cliente = ClientBuilder.newClient();
            WebTarget target = cliente.target("https://jsonplaceholder.typicode.com/todos/" + id);
            Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);

            Response response = invocation.get();

            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonResponse = objectMapper.readTree(response.readEntity(String.class));

                    id = jsonResponse.get("id").asLong();
                    Long userId = null;
                    String title = jsonResponse.get("title").asText(); // Cambiado a asText()
                    json = "{\"id\":" + id + ", \"title\":\"" + title + "\"}"; // Agregado comillas a title
                    service.Save(id, json);

                    try (PrintWriter writer = resp.getWriter()) {
                        writer.println(json);
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try (PrintWriter writer = resp.getWriter()) {
                writer.println(json);
            }

        }

    }
}
