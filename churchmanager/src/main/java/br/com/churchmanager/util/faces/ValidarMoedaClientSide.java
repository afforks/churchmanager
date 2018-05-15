package br.com.churchmanager.util.faces;

import java.util.Map;
import javax.faces.convert.BigDecimalConverter;
import org.primefaces.convert.ClientConverter;

public class ValidarMoedaClientSide extends BigDecimalConverter implements ClientConverter {
	public Map<String, Object> getMetadata() {
		return null;
	}

	public String getConverterId() {
		return "javax.faces.BigDecimal";
	}
}