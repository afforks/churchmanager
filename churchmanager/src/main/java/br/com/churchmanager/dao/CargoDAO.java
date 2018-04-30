package br.com.churchmanager.dao;

import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.Cargo;

import java.io.Serializable;

import javax.persistence.EntityManager;

public class CargoDAO extends DAO<Cargo> implements Serializable {
	private static final long serialVersionUID = 13254234522L;

	public CargoDAO() {
		super(Cargo.class);
	}

	public CargoDAO(EntityManager entityManager) {
		super(Cargo.class, entityManager);
	}
}