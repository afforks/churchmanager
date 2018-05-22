package br.com.churchmanager.util.faces;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.convert.ClientConverter;

@FacesConverter(value = "conversor.clientside.moeda")
public class ValidarMoedaClientSide implements Converter, ClientConverter {
	
	public Map<String, Object> getMetadata() {
		return null;
	}

	public String getConverterId() {
		return "conversor.clientside.moeda";
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componente, String key) {
		if (key == null || key.isEmpty())
			return null;
		return componente.getAttributes().get(key);
	}

	@Override
	public String getAsString(FacesContext arg0, 	UIComponent componente, Object value) {
		Float floatValue = (Float) value;
		if (floatValue == null || floatValue.toString().isEmpty())
			return null;
		componente.getAttributes().put(String.valueOf(floatValue), floatValue);
		return floatValue.toString();
	}
}