package br.com.churchmanager.util;

import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.util.AES;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class BuscaObjeto {
	public static <T> T comParametroGET(Class<T> clazz, String paramId, Buscador<T> dao) {
		T t = null;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if (request.getParameter(paramId) != null) {
			StringBuilder stringId = new StringBuilder(request.getParameter(paramId));
			AES.decrypt(stringId.toString().replace("@", "/").concat("=="));
			stringId = new StringBuilder(AES.getDecryptedString());
			if (stringId != null && stringId.toString().matches("\\d+")) {
				Long id = Long.valueOf(Long.parseLong(stringId.toString()));
				t = dao.buscarPorId(id);
			}

			dao = null;
		}

		return t;
	}
}