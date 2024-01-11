package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	private static final long serialVersionUID = 6968185440554600935L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
	    resp.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD");
	    resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
		

        LocalDate date = LocalDate.parse(req.getParameter("date"));
        String dateForApi = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        CovidService service = new CovidService();
        Long cases = service.getCases(date);

        if (cases == null) {
            Client cliente = ClientBuilder.newClient();

            String targetStr = "https://api.covidtracking.com/v1/us/" + dateForApi + ".json";

            WebTarget target = cliente.target(targetStr);
            Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);

            Response response = invocation.get();

            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonResponse = objectMapper.readTree(response.readEntity(String.class));

                    JsonNode positiveNode = jsonResponse.get("positive");
                    if (positiveNode != null && positiveNode.isNumber()) {
                        cases = positiveNode.asLong();
                        service.saveDataDay(date, cases);

                        resp.getWriter().write("{\"success\": true, \"data\": " + cases + "}");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            resp.getWriter().write("{\"success\": false, \"data\": \"Error al obtener datos de la API.\"}");

        }else {
            resp.getWriter().write("{\"success\": true, \"data\": " + cases + "}");
        }
    }
}