package parsers;

import java.io.InputStream;
import java.util.Collection;

import org.app.domain.Articulo;

public interface IParser {

	public Collection<Articulo> parse(InputStream is);
}
