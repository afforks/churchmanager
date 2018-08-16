package br.com.churchmanager.util;

import br.com.churchmanager.dao.generic.Buscador;
import br.com.churchmanager.util.AES;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class BuscaObjeto {

	private BuscaObjeto() {
	}

	public static <T> T comParametroGET(String paramId, Buscador<T> buscador) {
		T t = null;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if (request.getParameter(paramId) != null) {
			StringBuilder stringId = new StringBuilder(request.getParameter(paramId));
			String decryptedString = AES.decrypt(stringId.toString().replace("@", "/").concat("=="));
			stringId = new StringBuilder(decryptedString);
			if (stringId.toString().matches("\\d+")) {
				Long id = Long.valueOf(Long.parseLong(stringId.toString()));
				t = buscador.buscarPorId(id);
			}
		}
		return t;
	}
}