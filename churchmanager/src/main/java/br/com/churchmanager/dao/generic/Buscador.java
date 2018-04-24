package br.com.churchmanager.dao.generic;

import java.io.Serializable;

public interface Buscador<T> {
	T buscarPorId(Serializable arg0);
}