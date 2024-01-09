package exporters;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import org.apache.commons.collections4.map.HashedMap;
import org.app.domain.Articulo;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PDFExporter implements IExport {

	public void export(Collection<Articulo> list){
			
		//obtener el File (.jrxml)
		String path = "/reports/listado_articulos.jasper";
		
		try {
				String fileName = this.getClass().getResource(path).toURI().getPath();			
				File file = new File(fileName);
				FileInputStream fis = new FileInputStream(file);
				
				var params = new HashedMap<String,Object>();
				params.put("usuario", "carlos");
				
			//Ahora que tengo el file ↓
				
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
				JasperPrint jasperprint =  JasperFillManager.fillReport(fis, params, dataSource);
		
				System.out.println(jasperprint.getName());
				
		} catch (Exception e) {
			
		}
		
		
	}

}
