package controllers;

import java.io.IOException;

import org.app.services.ArticuloService;
import org.app.services.ServiceLocator;
import exporters.IExport;
import exporters.PDFExporter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/articulo/export")
public class ExportController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ext = req.getParameter("EXPORT_TYPE");
		
		IExport export = null;
		if(ext.equals("PDF")) {
			
			export = new PDFExporter();
		}
		
		ArticuloService list = (ArticuloService)ServiceLocator.getService(ArticuloService.class);
		
		export.export(list.buscarTodos());
		
		
	}

}
