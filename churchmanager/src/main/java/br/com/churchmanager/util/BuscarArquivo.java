package br.com.churchmanager.util;

import javax.faces.context.FacesContext;

public class BuscarArquivo {
	public static String porCaminho(String path) {
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
	}
}