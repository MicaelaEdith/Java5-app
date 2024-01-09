package exporters;

import java.util.Collection;

import org.app.domain.Articulo;

public interface IExport {

	public void export(Collection<Articulo> list);
	
}
