package br.com.churchmanager.dao;

import br.com.churchmanager.generic.dao.DAO;
import br.com.churchmanager.model.Cargo;

import java.io.Serializable;

public class CargoDAO extends DAO<Cargo> implements Serializable {
	private static final long serialVersionUID = 13254234522L;

	public CargoDAO() {
		super(Cargo.class);
	}
}