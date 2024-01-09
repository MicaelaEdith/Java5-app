package controllers;

import java.io.IOException;
import java.io.InputStream;

import org.app.domain.Articulo;
import org.app.services.ArticuloService;
import org.app.services.ServiceLocator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import parsers.CSVArticuloFileParse;
import parsers.IParser;

@SuppressWarnings("serial")
@WebServlet("/api/articulo/upload")
@MultipartConfig 
public class UploadController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Part filePart = req.getPart("file");
		
	/*	name && size
	 * 	
		String fileName = filePart.getSubmittedFileName();
		Long size = filePart.getSize();
	 */
		
		String fileName = filePart.getSubmittedFileName();
		InputStream is = filePart.getInputStream();
		
		IParser parser = ParserBuilder.builderParser(fileName);
		
		var nuevosRegistros = parser.parse(is);
		
		//System.out.println(nuevosRegistros);
		
		ArticuloService service = (ArticuloService) ServiceLocator.getService(ArticuloService.class);
		
		for (Articulo articulo : nuevosRegistros) {
			service.guardar(articulo);			
		}
		
	}

}
