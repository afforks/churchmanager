package br.com.churchmanager.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.churchmanager.dao.generic.Buscador;

public class BuscaObjeto {

	private BuscaObjeto() {
	}

	public static <T> T comParametroGET(String paramId, Buscador<T> buscador) {
		T t = null;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if (request.getParameter(paramId) != null) {
			StringBuilder stringId = new StringBuilder(request.getParameter(paramId));
			if (stringId.toString().matches("\\d+")) {
				Long id = Long.valueOf(Long.parseLong(stringId.toString()));
				t = buscador.buscarPorId(id);
			}
		}
		return t;
	}
}