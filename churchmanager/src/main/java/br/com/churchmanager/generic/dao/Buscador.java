package br.com.churchmanager.generic.dao;

import java.io.Serializable;

public interface Buscador<T> {
	T buscarPorId(Serializable arg0);
}